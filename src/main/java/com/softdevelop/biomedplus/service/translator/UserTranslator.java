package com.softdevelop.biomedplus.service.translator;

import com.softdevelop.biomedplus.model.dto.RolDto;
import com.softdevelop.biomedplus.model.dto.TicketDto;
import com.softdevelop.biomedplus.model.dto.UserDto;
import com.softdevelop.biomedplus.model.entity.*;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTranslator {

  public UserEntity setUserDtoToUserEntity (UserEntity userEntity, UserDto userDto){
      userEntity.setName(userDto.getName().toUpperCase());
      userEntity.setEmail(userDto.getEmail().toUpperCase());
      userEntity.setPass(userDto.getPass());
      RolEntity rol = new RolEntity();
      rol.setId(userDto.getRol().getId());
      userEntity.setRol(rol);
      return userEntity;
  }

  public UserDto userEntityToUserDto(UserEntity userEntity) {
    return UserDto.builder()
        .id(userEntity.getId())
        .name(userEntity.getName())
        .email(userEntity.getEmail())
        .pass(userEntity.getPass())
        .rol(RolDto.builder()
            .id(userEntity.getRol().getId())
            .name(userEntity.getRol().getName())
            .build())
        .build();
  }
}
