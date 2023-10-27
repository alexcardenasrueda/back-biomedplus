package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.UserDto;
import com.softdevelop.biomedplus.model.entity.UserEntity;
import com.softdevelop.biomedplus.repository.*;
import com.softdevelop.biomedplus.service.UserService;
import com.softdevelop.biomedplus.service.translator.UserTranslator;
import com.softdevelop.biomedplus.util.logs.LoggerEvent;
import java.util.ArrayList;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> allUsersEntity = new ArrayList<>();
        try {
            allUsersEntity = (List<UserEntity>) userRepository.findAll();
            if (allUsersEntity.isEmpty()) {
                throw new NotFoundException("Users not found");
            }
        } catch (RuntimeException e) {
            throw new GenericException(e.getMessage());
        }
        return modelMapper.map(allUsersEntity, new TypeToken<List<UserDto>>() {}.getType());
    }

    @Override
    public UserDto getuserById(Long id) {
        LoggerEvent.info()
            .forClass(EquipmentServiceImpl.class)
            .withField("Action: ", "getEquipments-init")
            .log();

        try {
            Optional<UserEntity> userEntity = userRepository.findById(id);
            if (userEntity.isEmpty()){
                throw new NotFoundException("User not found");
            }
            return userTranslator.userEntityToUserDto(userEntity.get());
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

}
