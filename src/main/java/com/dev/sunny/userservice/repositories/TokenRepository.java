package com.dev.sunny.userservice.repositories;

import com.dev.sunny.userservice.models.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Tokens, Long> {
    boolean existsTokensByTokenValueAndIsDeletedFalse(String token);

    Tokens findTokensByTokenValueAndIsDeletedFalse(String token);
}
