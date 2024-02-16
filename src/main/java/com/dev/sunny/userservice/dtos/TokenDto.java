package com.dev.sunny.userservice.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TokenDto {
    private String tokenValue;
    private UserResponseDto users;
    private Date expiryDate;
}
