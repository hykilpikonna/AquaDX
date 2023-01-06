-- This migration that recreate(alter) table which foreign key "rival_user_id" reference will be modified from "ongeki_user_data"("id") to "sega_card"("ext_id")
-- Before commit 687df8f57b59e1a06393f87f3dd734f5a9b3c732 , the type of UserRival.rivalUser is UserData and FK "rival_user_id" is right.
-- After this commit, UserRival.rivalUser is rename as UserRival.rivalUserId and FK "rival_user_id" must be modified.

CREATE TABLE "ongeki_user_rival_new" (
	"id"	INTEGER NOT NULL,
	"rival_user_ext_id"	BIGINT,
	"user_id"	BIGINT,
	FOREIGN KEY("user_id") REFERENCES "ongeki_user_data"("id") ON DELETE CASCADE,
	FOREIGN KEY("rival_user_ext_id") REFERENCES "sega_card"("ext_id") ON DELETE CASCADE,
	PRIMARY KEY("id" AUTOINCREMENT),
	CONSTRAINT "ongeki_user_rival_uq" UNIQUE("user_id","rival_user_ext_id") ON CONFLICT REPLACE
);

-- copy data from old to new.
INSERT INTO ongeki_user_rival_new(id,rival_user_ext_id,user_id) SELECT id,rival_user_id,user_id FROM ongeki_user_rival;

-- rename current to backup.
ALTER TABLE ongeki_user_rival RENAME TO ongeki_user_rival_old;
-- rename new as current.
ALTER TABLE ongeki_user_rival_new RENAME TO ongeki_user_rival;