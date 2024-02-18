package com.dev.sunny.userservice.mappers;

import com.dev.sunny.userservice.dtos.UserRequestDto;
import com.dev.sunny.userservice.dtos.UserResponseDto;
import com.dev.sunny.userservice.models.Users;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserMapper {
    private final RolesMapper rolesMapper;

    public UserMapper(RolesMapper rolesMapper) {
        this.rolesMapper = rolesMapper;
    }

    public Users userRequestDtoToUsers(UserRequestDto userRequestDto) {
        Users users = new Users();
        users.setName(userRequestDto.getName());
        users.setEmail(userRequestDto.getEmail());
        users.setHashedPassword(userRequestDto.getPassword());
        return users;
    }

    public UserResponseDto usersToUserResponseDto(Users users) {
        return UserResponseDto.builder()
                .name(users.getName())
                .email(users.getEmail())
                .roles(users.getRoles()
                        .stream()
                        .map(rolesMapper::rolesToRolesDto)
                        .collect(Collectors.toList()))
                .isEmailVerified(users.isEmailVerified())
                .build();
    }
}
