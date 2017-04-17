package wojtach.ewa.moviedb.user.domain;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


/**
 * Created by ewa on 15.04.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserIntegrationTest {

    public static final String API_USER = "/api/user";
    public static final String API_USERS = "/api/users";

    public static final String SAMPLE_AUTH = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5MzI5NDg0OH0.JRwfFvqiiYEvbxrtUwoPmqOKY963HL-sDWrowStOBqgC9DQJN1F1eTj-Lk9rlUANQIQNUCx5OMnQjMpxoXyhfQ";

    @Value("${local.server.port}")   private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
        public void canAddUserAccountAndFetchIt() {
            //  given().
            String userName = "ewa";
            String msg = prepareUserDto(userName,  "test");
            given()
                    .contentType("application/json")
                    .body(msg)
                    .when()
                    .put(API_USER)
                    .then()
                    .statusCode(HttpStatus.SC_CREATED);

            given().header("Authorization", SAMPLE_AUTH)
                    .when()
                    .get(API_USERS).
                    then().body(containsString(userName));

            given().header("Authorization", SAMPLE_AUTH)
                    .when()
                    .get(API_USERS).
                    then().
                    statusCode(HttpStatus.SC_OK).
                    contentType(ContentType.JSON).
                    body("list.size()", greaterThan(0));
        }

        @Test
        public void canRemoveUserAccount() {
            String userName = "lukasz";
            String msg = prepareUserDto(userName,  "test");
            given().contentType("application/json")
                    .body(msg)
                    .when()
                    .put(API_USER)
                    .then()
                    .statusCode(HttpStatus.SC_CREATED);
            given().header("Authorization", SAMPLE_AUTH)
                    .when()
                    .get(API_USERS).
                    then().body(containsString(userName));

            given().header("Authorization", SAMPLE_AUTH)
                    .when()
                    .delete(API_USER+"/"+userName)
                    .then().statusCode(HttpStatus.SC_OK);
            given().header("Authorization", SAMPLE_AUTH)
                    .when()
                    .get(API_USERS)
                    .then().body(not(containsString(userName)));
        }


        private String prepareUserDto(String userName, String userPassword){
            UserAccountDto user = UserAccountDto.builder().name(userName).password(userPassword).build();
            Gson gson = new Gson();
            return gson.toJson(user);
        }

}
