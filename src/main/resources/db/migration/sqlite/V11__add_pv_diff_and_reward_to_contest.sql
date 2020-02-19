CREATE TABLE diva_contest_new
(
    id                    INTEGER,
    bronze_borders        INTEGER NOT NULL,
    description           VARCHAR(255),
    enable                BOOLEAN NOT NULL,
    end_time              DATETIME,
    gold_borders          INTEGER NOT NULL,
    league                VARCHAR(255),
    max_complexity        INTEGER NOT NULL,
    min_complexity        INTEGER NOT NULL,
    name                  VARCHAR(255),
    norma_type            VARCHAR(255),
    sliver_borders        INTEGER NOT NULL,
    stage_limit           VARCHAR(255),
    stages                INTEGER NOT NULL,
    stars                 INTEGER NOT NULL,
    start_time            DATETIME,
    pv_list               VARCHAR(255),
    pv_diff_list          VARCHAR(255),
    bronze_contest_reward VARCHAR(255),
    sliver_contest_reward VARCHAR(255),
    gold_contest_reward   VARCHAR(255),
    contest_entry_reward  VARCHAR(255),
    PRIMARY KEY (
                 id
        )
);

INSERT INTO diva_contest_new (id,
                              bronze_borders,
                              description,
                              enable,
                              end_time,
                              gold_borders,
                              league,
                              max_complexity,
                              min_complexity,
                              name,
                              norma_type,
                              sliver_borders,
                              stage_limit,
                              stages,
                              stars,
                              start_time)
SELECT id,
       bronze_borders,
       description,
       enable,
       end_time,
       gold_borders,
       league,
       max_complexity,
       min_complexity,
       name,
       norma_type,
       sliver_borders,
       '0',
       stages,
       stars,
       start_time
FROM diva_contest;

DROP TABLE diva_contest;
ALTER TABLE diva_contest_new RENAME TO diva_contest;

CREATE TABLE diva_player_inventory
(
    id    INTEGER,
    type  VARCHAR(255),
    value VARCHAR(255),
    pd_id BIGINT REFERENCES diva_player_profile (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT diva_player_inventory_uq UNIQUE (
                                              type,
                                              value,
                                              pd_id
        )
);