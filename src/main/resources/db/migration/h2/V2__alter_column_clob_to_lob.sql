ALTER TABLE "authorization"
    DROP COLUMN access_token_metadata;

ALTER TABLE "authorization"
    DROP COLUMN access_token_scopes;

ALTER TABLE "authorization"
    DROP COLUMN access_token_value;

ALTER TABLE "authorization"
    DROP COLUMN attributes;

ALTER TABLE "authorization"
    DROP COLUMN authorization_code_value;

ALTER TABLE "authorization"
    DROP COLUMN authorized_scopes;

ALTER TABLE "authorization"
    DROP COLUMN device_code_metadata;

ALTER TABLE "authorization"
    DROP COLUMN device_code_value;

ALTER TABLE "authorization"
    DROP COLUMN oidc_id_token_claims;

ALTER TABLE "authorization"
    DROP COLUMN oidc_id_token_metadata;

ALTER TABLE "authorization"
    DROP COLUMN oidc_id_token_value;

ALTER TABLE "authorization"
    DROP COLUMN refresh_token_metadata;

ALTER TABLE "authorization"
    DROP COLUMN refresh_token_value;

ALTER TABLE "authorization"
    DROP COLUMN user_code_metadata;

ALTER TABLE "authorization"
    DROP COLUMN user_code_value;

ALTER TABLE "authorization"
    ADD access_token_metadata CLOB;

ALTER TABLE "authorization"
    ADD access_token_scopes CLOB;

ALTER TABLE "authorization"
    ADD access_token_value CLOB;

ALTER TABLE "authorization"
    ADD attributes CLOB;

ALTER TABLE "authorization"
    ADD authorization_code_value CLOB;

ALTER TABLE "authorization"
    ADD authorized_scopes CLOB;

ALTER TABLE "authorization"
    ADD device_code_metadata CLOB;

ALTER TABLE "authorization"
    ADD device_code_value CLOB;

ALTER TABLE "authorization"
    ADD oidc_id_token_claims CLOB;

ALTER TABLE "authorization"
    ADD oidc_id_token_metadata CLOB;

ALTER TABLE "authorization"
    ADD oidc_id_token_value CLOB;

ALTER TABLE "authorization"
    ADD refresh_token_metadata CLOB;

ALTER TABLE "authorization"
    ADD refresh_token_value CLOB;

ALTER TABLE "authorization"
    ADD user_code_metadata CLOB;

ALTER TABLE "authorization"
    ADD user_code_value CLOB;