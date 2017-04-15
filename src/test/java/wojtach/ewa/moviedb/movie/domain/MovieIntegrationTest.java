package wojtach.ewa.moviedb.movie.domain;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by ewa on 16.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MovieIntegrationTest {

    @Before
    public void setUp() {
        RestAssured.port = 8080;
    }

    @Test
    public void canAddMovieAndFetchIt() {
        //  given().
        String title = "movie123";
        String msg = prepareMovieDto(title, "desc", true);
        given()
                .contentType("application/json")
                .body(msg)
                .when()
                .put("/movie")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        when().
                get("/movies").
                then().body(containsString(title));
        when().
                get("/movies").
                then().
                statusCode(HttpStatus.SC_OK).
                contentType(ContentType.JSON).
                body("list.size()", greaterThan(0));
    }

    private String prepareMovieDto(String title, String description, boolean watched){
        MovieDto movie = MovieDto.builder().title(title).description(description).watched(watched).build();
        Gson gson = new Gson();
        return gson.toJson(movie);
    }
}