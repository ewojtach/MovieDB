package wojtach.ewa.moviedb.user.domain;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


/**
 * Created by ewa on 15.04.2017.
 */



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserIntegrationTest {

        @Before
        public void setUp() {
            RestAssured.port = 8080;
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
                    .put("/user")
                    .then()
                    .statusCode(HttpStatus.SC_CREATED);
            when().
                    get("/users").
                    then().body(containsString(userName));
            when().
                    get("/users").
                    then().
                    statusCode(HttpStatus.SC_OK).
                    contentType(ContentType.JSON).
                    body("list.size()", greaterThan(0));
        }

        @Test
        public void canRemoveUserAccount() {
            String userName = "lukasz";
            String msg = prepareUserDto(userName,  "test");
            given()
                    .contentType("application/json")
                    .body(msg)
                    .when()
                    .put("/user")
                    .then()
                    .statusCode(HttpStatus.SC_CREATED);
            when().
                    get("/users").
                    then().body(containsString(userName));
            when().
                    delete("/user/"+userName)
                    .then().statusCode(HttpStatus.SC_OK);
            when()
                    .get("users")
                    .then().body(not(containsString(userName)));
        }


        private String prepareUserDto(String userName, String userPassword){
            UserAccountDto user = UserAccountDto.builder().name(userName).password(userPassword).build();
            Gson gson = new Gson();
            return gson.toJson(user);
        }

}
