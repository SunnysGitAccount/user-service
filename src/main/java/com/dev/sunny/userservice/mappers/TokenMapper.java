package com.dev.sunny.userservice.mappers;

import com.dev.sunny.userservice.dtos.TokenDto;
import com.dev.sunny.userservice.dtos.UserResponseDto;
import com.dev.sunny.userservice.models.Tokens;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TokenMapper {
    private final RolesMapper rolesMapper;

    public TokenMapper(RolesMapper rolesMapper) {
        this.rolesMapper = rolesMapper;
    }

    public TokenDto tokenToTokenDto(Tokens token) {
        return TokenDto.builder()
                .tokenValue(token.getTokenValue())
                .users(UserResponseDto.builder()
                        .name(token.getUsers().getName())
                        .email(token.getUsers().getEmail())
                        .isEmailVerified(token.getUsers().isEmailVerified())
                        .roles(token.getUsers()
                                .getRoles()
                                .stream()
                                .map(rolesMapper::rolesToRolesDto)
                                .collect(Collectors.toList()))
                        .build())
                .expiryDate(token.getExpiryDate())
                .build();
    }
}
