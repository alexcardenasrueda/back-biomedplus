package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.UserDto;
import com.softdevelop.biomedplus.model.entity.UserEntity;
import com.softdevelop.biomedplus.repository.RolRepository;
import com.softdevelop.biomedplus.repository.UserRepository;
import com.softdevelop.biomedplus.service.UserService;
import com.softdevelop.biomedplus.service.translator.UserTranslator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        UserEntity userSaved;
        try{
            List<UserEntity> users = userRepository.findAllByEmail(userDto.getEmail());
            if (!users.isEmpty()) {
                throw new NotFoundException("User email already exist");
            }

            if (!rolRepository.existsById(userDto.getRol().getId())) {
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
        UserDto user;
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
