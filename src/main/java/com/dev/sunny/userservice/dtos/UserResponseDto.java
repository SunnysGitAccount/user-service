package com.dev.sunny.userservice.dtos;

import com.dev.sunny.userservice.models.Roles;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponseDto {
    private String name;
    private String email;
    private List<Roles> roles;
    private boolean isEmailVerified;
}
