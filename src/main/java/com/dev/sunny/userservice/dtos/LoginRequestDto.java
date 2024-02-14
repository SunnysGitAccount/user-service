package com.dev.sunny.userservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDto {
    private String email;
    private String password;
}
