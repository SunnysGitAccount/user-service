package com.dev.sunny.userservice.services.impl;

import com.dev.sunny.userservice.dtos.TokenDto;
import com.dev.sunny.userservice.dtos.UserRequestDto;
import com.dev.sunny.userservice.dtos.UserResponseDto;
import com.dev.sunny.userservice.mappers.TokenMapper;
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
    private final TokenMapper tokenMapper;

    public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper, TokenMapper tokenMapper) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
        this.tokenMapper = tokenMapper;
    }

    @Override
    public UserResponseDto register(String name, String email, String password) {
        Users savedUser = userRepository.save(userMapper.userRequestDtoToUsers(UserRequestDto.builder()
                .name(name)
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .build()));
        return userMapper.usersToUserResponseDto(savedUser);
    }

    @Override
    public TokenDto login(String email, String password) {
        Optional<Users> usersOptional = userRepository.findByEmail(email);
        Users user;
        if (usersOptional.isPresent()) user = usersOptional.get();
        else throw new RuntimeException("User not found with given email: " + email);

        if (!bCryptPasswordEncoder.matches(password, user.getHashedPassword()))
            throw new RuntimeException("User password match failed!");

        Tokens tokens = createToken(user);

        return tokenMapper.tokenToTokenDto(tokenRepository.save(tokens));
    }

    private static Tokens createToken(Users user) {
        Date expiryDate = Date.from(LocalDate.now().plusDays(30)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());

        Tokens tokens = new Tokens();
        tokens.setUsers(user);
        tokens.setTokenValue(RandomStringUtils.randomAlphanumeric(128));
        tokens.setExpiryDate(expiryDate);
        return tokens;
    }

    @Override
    public boolean deleteToken(String token) {
        Tokens tokens;
        if (tokenRepository.existsTokensByTokenValueAndDeletedFalse(token)) {
            tokens = tokenRepository.findTokensByTokenValueAndDeletedFalse(token);
            Date now = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            tokens.setDeleted(true);
            tokens.setExpiryDate(now);
            tokenRepository.save(tokens);
            return true;
        }
        return false;
    }

    @Override
    public Users validateToken(String token) {
        Tokens savedToken = tokenRepository.findByTokenValueAndDeletedFalseAndExpiryDateAfter(token, new Date());
        return savedToken.getUsers();
    }
}
