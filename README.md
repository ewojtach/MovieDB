## MovieDB REST API
> Sample JSON API service to store and manage simple movie database.
- Build on top of [Spring Boot](https://projects.spring.io/spring-boot/), using Spring Data and Spring Security.
- Uses in memory h2 database.
- API secured with JWT tokens.
- Built with maven.
- For integration testing, uses [RestAssured] (http://rest-assured.io)

### Local installation

  `git clone https://github.com/ewojtach/MovieDB.git`
  `mvn clean && mvn spring-boot:run`

### Automatic tests
Each domain contains:
- set of unit tests (described in *Spec file)
- integration tests (described in *IntegrationTest file).

To run integration tests:
  `mvn clean && mvn test`


### API description
Swagger documentation available at endpoint:
    `http://localhost:8080/swagger-ui.html#/`

