package com.dev.sunny.userservice.mappers;

import com.dev.sunny.userservice.dtos.TokenDto;
import com.dev.sunny.userservice.dtos.UserResponseDto;
import com.dev.sunny.userservice.models.Tokens;
import org.springframework.stereotype.Service;

@Service
public class TokenMapper {
    public TokenDto tokenToTokenDto(Tokens token) {
        return TokenDto.builder()
                .tokenValue(token.getTokenValue())
                .users(UserResponseDto.builder()
                        .name(token.getUsers().getName())
                        .email(token.getUsers().getEmail())
                        .isEmailVerified(token.getUsers().isEmailVerified())
                        .roles(token.getUsers().getRoles())
                        .build())
                .expiryDate(token.getExpiryDate())
                .build();
    }
}
