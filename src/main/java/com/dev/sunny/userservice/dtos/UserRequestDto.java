package com.dev.sunny.userservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
}
