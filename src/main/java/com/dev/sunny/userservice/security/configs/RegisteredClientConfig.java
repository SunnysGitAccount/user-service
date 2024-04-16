package com.dev.sunny.userservice.security.configs;

import com.dev.sunny.userservice.security.repositories.impl.JpaRegisteredClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.time.Instant;

@Configuration
public class RegisteredClientConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegisteredClientConfig(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public CommandLineRunner registeredClientRunner(JpaRegisteredClientRepository clientRepository) {
        String encodedSecret = bCryptPasswordEncoder.encode("secret");
        return args -> {
            RegisteredClient registeredClient = RegisteredClient.withId("user-service")
                    .clientId("rest-api-client")
                    .clientSecret(encodedSecret)
                    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                    .redirectUri("http://localhost:8081/login/oauth2/code/user-service")
                    .redirectUri("https://oauth.pstmn.io/v1/callback")
                    .postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
                    .scope("read")
                    .scope("write")
                    .scope(OidcScopes.OPENID)
                    .scope(OidcScopes.PROFILE)
                    .scope("USER")
                    .clientSettings(ClientSettings.builder()
                            .requireAuthorizationConsent(true)
                            .build())
                    .clientIdIssuedAt(Instant.now())
                    .clientSecretExpiresAt(Instant.now().plusSeconds(3600 * 24))
                    .tokenSettings(TokenSettings.builder()
                            .accessTokenTimeToLive(Duration.ofHours(1))
                            .build())
                    .build();

            clientRepository.save(registeredClient);
        };
    }
}
