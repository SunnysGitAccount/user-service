package com.dev.sunny.userservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogoutRequestDto {
    private String token;
}
