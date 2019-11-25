# Запуск системы и теста
1 . Для первого запуска SUT используйте команду: 
* java -jar artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://192.168.99.100:3306/app -P:jdbc.user=app -P:jdbc.password=pass

2 . Перед запуском теста используйте команду:
* docker-compose exec mysql mysql -h 127.0.0.1 -P 3306 -u app -ppass -e "ALTER TABLE users ADD COLUMN col VARCHAR(255) NOT NULL AFTER status; update users set col='qwerty123' where login='vasya'; update users set col='123qwerty' where login='petya';" app

3 . Для повторного запуска SUT используйте команды: 
* docker-compose exec mysql mysql -h 127.0.0.1 -P 3306 -u app -ppass -e "delete from card_transactions; delete from auth_codes; delete from cards; delete from users; ALTER TABLE users DROP col ;" app
* java -jar artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://192.168.99.100:3306/app -P:jdbc.user=app -P:jdbc.password=pass


