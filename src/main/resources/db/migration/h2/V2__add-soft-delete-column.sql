ALTER TABLE role_data
    ADD is_deleted BOOLEAN DEFAULT false;

ALTER TABLE role_data
    ALTER COLUMN is_deleted SET NOT NULL;

ALTER TABLE token_data
    ADD is_deleted BOOLEAN DEFAULT false;

ALTER TABLE token_data
    ALTER COLUMN is_deleted SET NOT NULL;

ALTER TABLE user_data
    ADD is_deleted BOOLEAN DEFAULT false;

ALTER TABLE user_data
    ALTER COLUMN is_deleted SET NOT NULL;