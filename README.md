# DB Comparer
Compare two schemas of the same engine.
Currently working only for PostgreSQL

## Requirements
  * maven
  * Java SDK 1.8>

## Usage
### First run
run `./install-and-run.sh FILE1 FILE2`

### For update after changes in source
run `./compile-and-run.sh FILE1 FILE2`

### Just run
`./run.sh FILE1 FILE2`

where FILE1 and FILE2 are configuration files

## Configuration Files
Information and credentials for accessing the DB is readed from JSON files like this:
```json
{
  "DB_URL" : "jdbc:postgresql://localhost:5432/postgres",
  "JDBC_DRIVER" : "org.postgresql.Driver",
  "SCHEMA" : "name_of_the_schema",
  "USER" : "database_user",
  "PASS" : "user_password"
}
```
