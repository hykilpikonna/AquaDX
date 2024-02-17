## Migrating MySQL to MariaDB

Since AquaDX 1.0.0, MySQL will no longer be supported.

If you were using Aqua / AquaDX <= 0.0.47 with a MySQL database before this upgrade, please follow the instructions below to migrate your data to MariaDB.

### 1. Run a MariaDB Server

To migrate, you first need to run a MariaDB server using your preferred method.
If you don't have a preference, we recommend running it using Docker Compose.

Below is an example `docker-compose.yml` configuration to run a MariaDB server.

```yaml
version: '3.1'

services:
  mariadb:
    image: mariadb
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: example
      MYSQL_DATABASE: aqua
      MYSQL_USER: aqua
      MYSQL_PASSWORD: aqua
    ports:
      - '127.0.0.1:3306:3306'
    volumes:
      - ./db:/var/lib/mysql
```

### 2. Export Data from MySQL

Run the following command to export your data from MySQL. Please fill in the fields in the curly braces with your MySQL database details.

To prevent charset encoding issues, please run the following command on Linux / macOS. 
For some reason Windows is still not using UTF-8 by default in 2024.

```bash
mysqldump --user={username} --password={password} --host={host} --port={port} {database} > aqua.sql
```

Now `aqua.sql` should be created. Please open `aqua.sql` and check if `utf8mb4_0900_ai_ci` exists. 
If it does, you're running a higher version of MySQL, please replace `utf8mb4_0900_ai_ci` with `utf8mb4_general_ci` in the `aqua.sql` file.

```bash
# A command to replace the strings as mentioned above (Linux / macOS only).
sed -i 's/utf8mb4_0900_ai_ci/utf8mb4_general_ci/g' aqua.sql
```

### 3. Import Data to MariaDB

Run the following command to import your data to MariaDB. Please fill in the fields in the curly braces with your MariaDB database details.

First, login to your MariaDB server.

```bash
mysql --user={username} --password={password} --host={host} --port={port} {database}
```

Then, import the data.

```sql
source aqua.sql;
```

Finally, we need to fix a flyway checksum. Aqua uses Flyway to manage database schema migrations. Most migrations were identical, but one migration used different case for MySQL and MariaDB, so we need to fix its checksum.

Run the following SQL query to fix the checksum.

```sql
UPDATE main.flyway_schema_history t
SET t.checksum = 357127209
WHERE t.installed_rank = 144;
```

### 5. Update AquaDX Configuration

Finally, update your AquaDX configuration `application.properties` to use MariaDB instead of MySQL.




