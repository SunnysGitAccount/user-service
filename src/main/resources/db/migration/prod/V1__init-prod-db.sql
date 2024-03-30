CREATE TABLE authorization
(
    id                            VARCHAR(255) NOT NULL,
    registered_client_id          VARCHAR(255) NULL,
    principal_name                VARCHAR(255) NULL,
    authorization_grant_type      VARCHAR(255) NULL,
    authorized_scopes             BLOB         NULL,
    attributes                    BLOB         NULL,
    state                         VARCHAR(500) NULL,
    authorization_code_value      BLOB         NULL,
    authorization_code_issued_at  datetime     NULL,
    authorization_code_expires_at datetime     NULL,
    authorization_code_metadata   VARCHAR(255) NULL,
    access_token_value            BLOB         NULL,
    access_token_issued_at        datetime     NULL,
    access_token_expires_at       datetime     NULL,
    access_token_metadata         BLOB         NULL,
    access_token_type             VARCHAR(255) NULL,
    access_token_scopes           BLOB         NULL,
    refresh_token_value           BLOB         NULL,
    refresh_token_issued_at       datetime     NULL,
    refresh_token_expires_at      datetime     NULL,
    refresh_token_metadata        BLOB         NULL,
    oidc_id_token_value           BLOB         NULL,
    oidc_id_token_issued_at       datetime     NULL,
    oidc_id_token_expires_at      datetime     NULL,
    oidc_id_token_metadata        BLOB         NULL,
    oidc_id_token_claims          BLOB         NULL,
    user_code_value               BLOB         NULL,
    user_code_issued_at           datetime     NULL,
    user_code_expires_at          datetime     NULL,
    user_code_metadata            BLOB         NULL,
    device_code_value             BLOB         NULL,
    device_code_issued_at         datetime     NULL,
    device_code_expires_at        datetime     NULL,
    device_code_metadata          BLOB         NULL,
    CONSTRAINT pk_authorization PRIMARY KEY (id)
);

CREATE TABLE authorization_consent
(
    registered_client_id VARCHAR(255)  NOT NULL,
    principal_name       VARCHAR(255)  NOT NULL,
    authorities          VARCHAR(1000) NULL,
    CONSTRAINT pk_authorization_consent PRIMARY KEY (registered_client_id, principal_name)
);

CREATE TABLE client
(
    id                            VARCHAR(255)  NOT NULL,
    client_id                     VARCHAR(255)  NULL,
    client_id_issued_at           datetime      NULL,
    client_secret                 VARCHAR(255)  NULL,
    client_secret_expires_at      datetime      NULL,
    client_name                   VARCHAR(255)  NULL,
    client_authentication_methods VARCHAR(1000) NULL,
    authorization_grant_types     VARCHAR(1000) NULL,
    redirect_uris                 VARCHAR(1000) NULL,
    post_logout_redirect_uris     VARCHAR(1000) NULL,
    scopes                        VARCHAR(1000) NULL,
    client_settings               VARCHAR(2000) NULL,
    token_settings                VARCHAR(2000) NULL,
    CONSTRAINT pk_client PRIMARY KEY (id)
);

CREATE TABLE role_data
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    deleted BIT(1)                NOT NULL,
    name    VARCHAR(255)          NULL,
    CONSTRAINT pk_role_data PRIMARY KEY (id)
);

CREATE TABLE token_data
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    deleted     BIT(1)                NOT NULL,
    token_value VARCHAR(255)          NULL,
    user_id     BIGINT                NULL,
    expiry_date datetime              NULL,
    CONSTRAINT pk_token_data PRIMARY KEY (id)
);

CREATE TABLE user_data
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    deleted           BIT(1)                NOT NULL,
    name              VARCHAR(255)          NULL,
    email             VARCHAR(255)          NULL,
    hashed_password   VARCHAR(255)          NULL,
    is_email_verified BIT(1)                NOT NULL,
    CONSTRAINT pk_user_data PRIMARY KEY (id)
);

CREATE TABLE user_role_data
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
);

ALTER TABLE token_data
    ADD CONSTRAINT FK_TOKEN_DATA_ON_USER FOREIGN KEY (user_id) REFERENCES user_data (id);

ALTER TABLE user_role_data
    ADD CONSTRAINT fk_useroldat_on_roles FOREIGN KEY (role_id) REFERENCES role_data (id);

ALTER TABLE user_role_data
    ADD CONSTRAINT fk_useroldat_on_users FOREIGN KEY (user_id) REFERENCES user_data (id);