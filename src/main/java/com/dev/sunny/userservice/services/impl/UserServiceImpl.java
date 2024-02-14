package com.dev.sunny.userservice.services.impl;

import com.dev.sunny.userservice.dtos.UserDto;
import com.dev.sunny.userservice.mappers.UserMapper;
import com.dev.sunny.userservice.models.Tokens;
import com.dev.sunny.userservice.models.Users;
import com.dev.sunny.userservice.repositories.TokenRepository;
import com.dev.sunny.userservice.repositories.UserRepository;
import com.dev.sunny.userservice.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public Users register(String name, String email, String password) {
        return userRepository.save(userMapper.userDtoToUsers(UserDto.builder()
                .name(name)
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .build()));
    }

    @Override
    public Tokens login(String email, String password) {
        Optional<Users> usersOptional = userRepository.findByEmail(email);
        Users user;
        if (usersOptional.isPresent()) user = usersOptional.get();
        else throw new RuntimeException("User not found with given email: " + email);

        if (!bCryptPasswordEncoder.matches(password, user.getHashedPassword()))
            throw new RuntimeException("User password match failed!");

        Date expiryDate = Date.from(LocalDate.now().plusDays(30)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());

        Tokens tokens = new Tokens();
        tokens.setUsers(user);
        tokens.setTokenValue(RandomStringUtils.randomAlphanumeric(128));
        tokens.setExpiryDate(expiryDate);

        return tokenRepository.save(tokens);
    }

    @Override
    public boolean deleteToken(String token) {
        Tokens tokens;
        if (tokenRepository.existsTokensByTokenValueAndIsDeletedFalse(token)) {
            tokens = tokenRepository.findTokensByTokenValueAndIsDeletedFalse(token);
            Date now = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            tokens.setDeleted(true);
            tokens.setExpiryDate(now);
            tokenRepository.save(tokens);
            return true;
        }
        return false;
    }
}
