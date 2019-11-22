docker-compose exec mysql mysql -h 127.0.0.1 -P 3306 -u app -ppass -e "ALTER TABLE users ADD COLUMN col VARCHAR(255) NOT NULL AFTER status; update users set col='qwerty123' where login='vasya'; update users set col='123qwerty' where login='petya';" app
gradlew test
