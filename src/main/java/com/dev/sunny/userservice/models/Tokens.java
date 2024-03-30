package com.dev.sunny.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "`token_data`")
public class Tokens extends BaseModel {
    private String tokenValue;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    private Date expiryDate;
}
