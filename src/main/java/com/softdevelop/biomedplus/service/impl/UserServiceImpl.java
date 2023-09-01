package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.SpareDto;
import com.softdevelop.biomedplus.model.dto.TicketDto;
import com.softdevelop.biomedplus.model.dto.UserDto;
import com.softdevelop.biomedplus.model.entity.SpareEntity;
import com.softdevelop.biomedplus.model.entity.TicketEntity;
import com.softdevelop.biomedplus.model.entity.UserEntity;
import com.softdevelop.biomedplus.repository.*;
import com.softdevelop.biomedplus.service.TicketService;
import com.softdevelop.biomedplus.service.UserService;
import com.softdevelop.biomedplus.service.translator.TicketTranslator;
import com.softdevelop.biomedplus.service.translator.UserTranslator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final ModelMapper modelMapper;
    private final UserTranslator userTranslator;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userSaved = new UserEntity();
        try{
            List<UserEntity> users = userRepository.findAllByEmail(userDto.getEmail());
            if (!users.isEmpty()) {
                throw new NotFoundException("User email already exist");
            }

            Boolean exist = rolRepository.existsById(userDto.getRol().getId());
            if (!exist) {
                throw new NotFoundException("Rol not found");
            }

            UserEntity userEntity = new UserEntity();
            userSaved = userRepository.save(
                    userTranslator.setUserDtoToUserEntity(userEntity, userDto));
        }catch (RuntimeException e ){
            throw new GenericException(e.getMessage());
        }
        return modelMapper.map(userSaved, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email, String pass) {
        UserDto user = new UserDto();
        try {
            List<UserEntity> users = userRepository.findAllByEmailAndPass(email, pass);
            if (users == null || users.isEmpty()) {
                throw new NotFoundException("User or pass incorrect");
            }
            user = modelMapper.map(users.get(0), UserDto.class );
        } catch (RuntimeException e) {
            throw new GenericException(e.getMessage());
        }
        return user;
    }

}
