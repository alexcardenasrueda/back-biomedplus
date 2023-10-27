package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.model.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByEmail(String email, String pass);

  List<UserDto> getUsers();

  UserDto getuserById(Long id);
}
