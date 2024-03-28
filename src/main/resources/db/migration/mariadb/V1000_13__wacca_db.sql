ALTER TABLE wacca_user_playlog
    ADD all_marv BIT(1) NOT NULL;

ALTER TABLE wacca_user_playlog
    ADD full_combo BIT(1) NOT NULL;

ALTER TABLE wacca_user_playlog
    ADD give_up BIT(1) NOT NULL;

ALTER TABLE wacca_user_playlog
    ADD judgements VARCHAR(255) NOT NULL;

ALTER TABLE wacca_user_playlog
    ADD level DOUBLE NOT NULL;

ALTER TABLE wacca_user_playlog
    ADD missless BIT(1) NOT NULL;

ALTER TABLE wacca_user_playlog
    ADD new_record BIT(1) NOT NULL;

ALTER TABLE wacca_user_playlog
    ADD skill_pt INT NOT NULL;

ALTER TABLE wacca_user_playlog
    MODIFY all_marv BIT(1) NOT NULL;

ALTER TABLE wacca_user_score
    ADD clears VARCHAR(255) NOT NULL;

ALTER TABLE wacca_user_score
    ADD grades VARCHAR(255) NOT NULL;

ALTER TABLE wacca_user_score
    DROP COLUMN allmarv_ct;

ALTER TABLE wacca_user_score RENAME COLUMN chart_id TO difficulty;

ALTER TABLE wacca_user_score
    DROP COLUMN clear_ct;

ALTER TABLE wacca_user_score
    DROP COLUMN fullcombo_ct;

ALTER TABLE wacca_user_score
    DROP COLUMN grade_master_ct;

ALTER TABLE wacca_user_score
    DROP COLUMN grade_sp_ct;

ALTER TABLE wacca_user_score
    DROP COLUMN grade_ssp_ct;

ALTER TABLE wacca_user_score
    DROP COLUMN grade_sssp_ct;

ALTER TABLE wacca_user_score
    DROP COLUMN gradeaaact;

ALTER TABLE wacca_user_score
    DROP COLUMN gradeaact;

ALTER TABLE wacca_user_score
    DROP COLUMN gradeact;

ALTER TABLE wacca_user_score
    DROP COLUMN gradebct;

ALTER TABLE wacca_user_score
    DROP COLUMN gradecct;

ALTER TABLE wacca_user_score
    DROP COLUMN gradedct;

ALTER TABLE wacca_user_score
    DROP COLUMN gradesct;

ALTER TABLE wacca_user_score
    DROP COLUMN gradessct;

ALTER TABLE wacca_user_score
    DROP COLUMN gradesssct;

ALTER TABLE wacca_user_score
    DROP COLUMN missless_ct;

ALTER TABLE wacca_user_score
    DROP COLUMN play_ct;

ALTER TABLE wacca_user_playlog RENAME COLUMN chart_id TO difficulty;

ALTER TABLE wacca_user_playlog
    DROP COLUMN good_ct;

ALTER TABLE wacca_user_playlog
    DROP COLUMN great_ct;

ALTER TABLE wacca_user_playlog
    DROP COLUMN marv_ct;

ALTER TABLE wacca_user_playlog
    DROP COLUMN miss_ct;

ALTER TABLE wacca_user_playlog
    DROP COLUMN season;

ALTER TABLE wacca_user_playlog
    DROP COLUMN clear;

ALTER TABLE wacca_user_playlog
    DROP COLUMN date_scored;

ALTER TABLE wacca_user_item
    MODIFY acquired_date datetime NOT NULL;

ALTER TABLE wacca_user_playlog
    ADD clear BIT(1) NOT NULL;

ALTER TABLE wacca_user_playlog
    ADD date_scored datetime NOT NULL;