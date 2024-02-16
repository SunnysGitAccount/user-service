package com.dev.sunny.userservice.services;

import com.dev.sunny.userservice.dtos.TokenDto;
import com.dev.sunny.userservice.dtos.UserResponseDto;
import com.dev.sunny.userservice.models.Users;

public interface UserService {
    UserResponseDto register(String name, String email, String password);
    TokenDto login(String email, String password);
    boolean deleteToken(String token);
    Users validateToken(String token);
}
