package com.dev.sunny.userservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequestDto {
    private String email;
    private String password;
    private String name;
}
