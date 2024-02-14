package com.dev.sunny.userservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "role_data")
public class Roles extends BaseModel {
    private String name;
}
