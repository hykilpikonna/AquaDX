CREATE TABLE maimai2_user_general_data (
    id             INTEGER,
    property_key   VARCHAR NOT NULL,
    property_value VARCHAR NOT NULL,
    user_id        BIGINT  REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT maimai2_user_general_data_uq UNIQUE (
        property_key,
        user_id
    )
    ON CONFLICT REPLACE
);