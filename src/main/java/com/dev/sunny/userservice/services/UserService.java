package com.dev.sunny.userservice.services;

import com.dev.sunny.userservice.models.Tokens;
import com.dev.sunny.userservice.models.Users;

public interface UserService {
    Users register(String name, String email, String password);

    Tokens login(String email, String password);
    boolean deleteToken(String token);
}
