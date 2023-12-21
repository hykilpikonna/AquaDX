-- Step 1: Create a new table with the desired changes
CREATE TABLE temp_chusan_user_music_detail (
                                               id                INTEGER,
                                               full_chain        INTEGER NOT NULL,
                                               is_all_justice    BOOLEAN NOT NULL,
                                               is_full_combo     BOOLEAN NOT NULL,
                                               is_lock           BOOLEAN NOT NULL,
                                               is_success        INTEGER NOT NULL, -- Changed to INTEGER
                                               level             INTEGER NOT NULL,
                                               max_chain         INTEGER NOT NULL,
                                               max_combo_count   INTEGER NOT NULL,
                                               miss_count        INTEGER NOT NULL,
                                               music_id          INTEGER NOT NULL,
                                               play_count        INTEGER NOT NULL,
                                               theory_count      INTEGER,
                                               ext1              INTEGER,
                                               score_max         INTEGER NOT NULL,
                                               score_rank        INTEGER NOT NULL,
                                               user_id           BIGINT  REFERENCES chusan_user_data (id) ON DELETE CASCADE,
                                               PRIMARY KEY (id),
                                               CONSTRAINT chusan_user_music_detail_uq UNIQUE (level, music_id, user_id)
);

-- Step 2: Copy the data from the original table to the new table
INSERT INTO temp_chusan_user_music_detail
SELECT * FROM chusan_user_music_detail;

-- Step 3: Delete the original table
DROP TABLE chusan_user_music_detail;

-- Step 4: Rename the new table to the original table's name
ALTER TABLE temp_chusan_user_music_detail RENAME TO chusan_user_music_detail;
