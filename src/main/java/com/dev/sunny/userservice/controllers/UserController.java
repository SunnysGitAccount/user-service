package com.dev.sunny.userservice.controllers;

import com.dev.sunny.userservice.dtos.LoginRequestDto;
import com.dev.sunny.userservice.dtos.SignUpRequestDto;
import com.dev.sunny.userservice.models.Tokens;
import com.dev.sunny.userservice.models.Users;
import com.dev.sunny.userservice.services.UserService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService usersService;

    public UserController(UserService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public Tokens login(@RequestBody LoginRequestDto loginRequest) {
        return usersService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }


    /**
     * Sign up user, no need for hashing password for now,
     * store as is in DB.
     * No need to have email verified.
     * @return Users token with details
     */
    @PostMapping("/signup")
    public Users signUp(@RequestBody SignUpRequestDto signUpRequest) {
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();
        String name = signUpRequest.getName();

        return usersService.register(name, email, password);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(@RequestHeader String token) {
        if(!usersService.deleteToken(token)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Token either don't exist or already deleted!"));
        }
        return new ResponseEntity<>(Map.of("message", "Token Successfully Deleted."), HttpStatus.OK);
    }

    @PostMapping("/validate")
    public Users validateToken(@NonNull @RequestHeader String token) {
        return usersService.validateToken(token);
    }
}
