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

INSERT INTO `property` (id, property_key, property_value)
VALUES (1, 'diva_news', 'Server Running                  No other news');
INSERT INTO `property` (id, property_key, property_value)
VALUES (2, 'diva_warning', 'Network Service Running');