# twinodemo

Aby odpalić aplikację musisz mieć:
- maven (mam 3.5.3 ale nie wykluczone, że pójdzie na innych wersjach)
- JRE 11 (Java 11)
- docker

##Uruchomienie aplikacji

  1. w głównym katalogu uruchamiamy baze danych `docker-compose up`
  1. w tym samym katalogu odpalamy aplikację `mvn spring-boot:run`

##Używanie endpointów

Pod adresem localhost:8080/swagger-ui.html dostępny jest swagger.
