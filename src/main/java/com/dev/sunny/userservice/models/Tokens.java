package com.dev.sunny.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "token_data")
public class Tokens extends BaseModel {
    private String tokenValue;
    @ManyToOne
    private Users users;
    private Date expiryDate;
}
