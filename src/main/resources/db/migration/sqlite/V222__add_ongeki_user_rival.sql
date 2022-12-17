CREATE TABLE "ongeki_user_rival" (
	"id"	INTEGER NOT NULL,
	"rival_user_id"	BIGINT,
	"user_id"	BIGINT,
	FOREIGN KEY("user_id") REFERENCES "ongeki_user_data"("id") ON DELETE CASCADE,
	FOREIGN KEY("rival_user_id") REFERENCES "ongeki_user_data"("id") ON DELETE CASCADE,
	PRIMARY KEY("id" AUTOINCREMENT),
	CONSTRAINT "ongeki_user_rival_uq" UNIQUE("user_id","rival_user_id") ON CONFLICT REPLACE
);