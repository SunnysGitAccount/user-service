CREATE
SEQUENCE role_data_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE token_data_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE user_data_seq START
WITH 1 INCREMENT BY 50;

CREATE TABLE role_data
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_role_data PRIMARY KEY (id)
);

CREATE TABLE token_data
(
    id          BIGINT NOT NULL,
    token_value VARCHAR(255),
    users_id    BIGINT,
    expiry_date TIMESTAMP,
    CONSTRAINT pk_token_data PRIMARY KEY (id)
);

CREATE TABLE user_data
(
    id                BIGINT  NOT NULL,
    name              VARCHAR(255),
    email             VARCHAR(255),
    hashed_password   VARCHAR(255),
    is_email_verified BOOLEAN NOT NULL,
    CONSTRAINT pk_user_data PRIMARY KEY (id)
);

CREATE TABLE user_data_roles
(
    roles_id     BIGINT NOT NULL,
    user_data_id BIGINT NOT NULL
);

ALTER TABLE token_data
    ADD CONSTRAINT FK_TOKEN_DATA_ON_USERS FOREIGN KEY (users_id) REFERENCES user_data (id);

ALTER TABLE user_data_roles
    ADD CONSTRAINT fk_usedatrol_on_roles FOREIGN KEY (roles_id) REFERENCES role_data (id);

ALTER TABLE user_data_roles
    ADD CONSTRAINT fk_usedatrol_on_users FOREIGN KEY (user_data_id) REFERENCES user_data (id);