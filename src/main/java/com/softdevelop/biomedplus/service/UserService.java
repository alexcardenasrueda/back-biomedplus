package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.model.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByEmail(String email, String pass);
}
