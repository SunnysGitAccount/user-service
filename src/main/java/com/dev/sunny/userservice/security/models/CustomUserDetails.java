package com.dev.sunny.userservice.security.models;

import com.dev.sunny.userservice.models.Roles;
import com.dev.sunny.userservice.models.Users;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
public class CustomUserDetails implements UserDetails {
    private final List<CustomGrantedAuthority> grantedAuthorities;
    private final String password;
    private final String username;

    public CustomUserDetails(Users users) {
        List<CustomGrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Roles role : users.getRoles()) {
            grantedAuthorities.add(new CustomGrantedAuthority(role));
        }
        this.grantedAuthorities = grantedAuthorities;
        this.password = users.getHashedPassword();
        this.username = users.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
