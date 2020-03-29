CREATE TABLE chuni_user_general_data
(
    id             INTEGER,
    property_key   VARCHAR NOT NULL,
    property_value VARCHAR NOT NULL,
    user_id        BIGINT REFERENCES chuni_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT chuni_user_general_data_uq UNIQUE (
                                                   property_key,
                                                   user_id
        ) ON CONFLICT REPLACE
);