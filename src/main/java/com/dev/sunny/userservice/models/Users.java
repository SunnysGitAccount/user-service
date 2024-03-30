package com.dev.sunny.userservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "`user_data`")
public class Users extends BaseModel {
    private String name;
    private String email;
    private String hashedPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "`user_role_data`",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Roles> roles;
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Tokens> tokens;
    private boolean isEmailVerified;
}
