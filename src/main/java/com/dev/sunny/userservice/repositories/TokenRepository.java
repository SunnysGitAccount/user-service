package com.dev.sunny.userservice.repositories;

import com.dev.sunny.userservice.models.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface TokenRepository extends JpaRepository<Tokens, Long> {
    boolean existsTokensByTokenValueAndDeletedFalse(String token);

    Tokens findTokensByTokenValueAndDeletedFalse(String token);

    Tokens findByTokenValueAndDeletedFalseAndExpiryDateAfter(String token, Date currDate);
}
