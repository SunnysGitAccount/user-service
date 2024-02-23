package com.dev.sunny.userservice.mappers;

import com.dev.sunny.userservice.dtos.RolesDto;
import com.dev.sunny.userservice.models.Roles;
import org.springframework.stereotype.Service;

@Service
public class RolesMapper {
    public RolesDto rolesToRolesDto(Roles roles) {
        return RolesDto.builder()
                .name(roles.getName())
                .build();
    }

    public Roles rolesDtoToRoles(RolesDto user) {
        Roles roles = new Roles();
        roles.setName(user.getName());
        return roles;
    }
}
