###URUCHAMIANIE PRZEZ TERMINAL:

Uruchomić przez terminal używając poniższych komend:

sudo docker run --name nbp_api -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=nbp_db -e MYSQL_USER=root -e MYSQL_PASSWORD=root -p 3306:3306 -d mysql:5.7

./mvnw clean install

./mvnw spring-boot:run

###ALTERNATYWA:

1. stworzyć połączenie przez MySQLWorkbench na porcie 3306 z użytkownikiem "root" oraz hasłem "root"
2. uruchomić terminal i wkleić poniższe komendy:

./mvnw clean install

./mvnw spring-boot:run




###PROGRAM:

Po uruchomieniu aplikacji należy wejść na stronę: "localhost:8080/computers".
