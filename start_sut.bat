docker-machine start default
docker-machine env
docker-compose exec mysql mysql -h 127.0.0.1 -P 3306 -u app -ppass -e "delete from card_transactions; delete from auth_codes; delete from cards; delete from users;" app
java -jar artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://192.168.99.100:3306/app -P:jdbc.user=app -P:jdbc.password=pass

