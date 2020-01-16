-- Table: property
CREATE TABLE property
(
    id             BIGINT NOT NULL,
    property_key   VARCHAR(255) UNIQUE,
    property_value VARCHAR(255),
    PRIMARY KEY (
                 id
        )
);