package com.dev.sunny.userservice.mappers;

import com.dev.sunny.userservice.dtos.UserDto;
import com.dev.sunny.userservice.models.Users;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public Users userDtoToUsers(UserDto userDto) {
        Users users = new Users();
        users.setName(userDto.getName());
        users.setEmail(userDto.getEmail());
        users.setHashedPassword(userDto.getPassword());
        return users;
    }

    public UserDto usersToUserDto(Users users) {
        return UserDto.builder()
                .name(users.getName())
                .email(users.getEmail())
                .password(users.getHashedPassword())
                .build();
    }
}
