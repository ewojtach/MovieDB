## MovieDB REST API
> Sample JSON API service to store and manage simple movie database.
- Build on top of [Spring Boot](https://projects.spring.io/spring-boot/), using Spring Data and Spring Security.
- Uses in memory h2 database.
- API secured with JWT tokens.
- Built with maven.
- For integration testing, uses [RestAssured] (http://rest-assured.io)
- API documentation generated with SpringFox (http://springfox.io)

### Local installation

  ```
  git clone https://github.com/ewojtach/MovieDB.git
  mvn clean && mvn spring-boot:run
  ```

### Automatic tests
Each domain contains:
- unit tests (described in *SpecTest file)
- integration tests (described in *IntegrationTest file).

To run tests:
  `mvn clean && mvn test`


### API description
Swagger documentation available at endpoint:
    `http://localhost:8080/swagger-ui.html#/`

### Login credentials
To obtain login credentials, register user and call login:
```
    POST /login HTTP/1.1
   Host: localhost:8080
   Content-Type: application/json
   Cache-Control: no-cache

   {"userName": "user","password":"password"}
   ```
