package wojtach.ewa.moviedb.movie.domain;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

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

        given().header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5MzI5NDg0OH0.JRwfFvqiiYEvbxrtUwoPmqOKY963HL-sDWrowStOBqgC9DQJN1F1eTj-Lk9rlUANQIQNUCx5OMnQjMpxoXyhfQ")
                .when()
                .contentType("application/json")
                .body(msg)
                .when()
                .put("/movie")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        given().header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5MzI5NDg0OH0.JRwfFvqiiYEvbxrtUwoPmqOKY963HL-sDWrowStOBqgC9DQJN1F1eTj-Lk9rlUANQIQNUCx5OMnQjMpxoXyhfQ")
                .when()
                .get("/movies").
                then().body(containsString(title));
        given().header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5MzI5NDg0OH0.JRwfFvqiiYEvbxrtUwoPmqOKY963HL-sDWrowStOBqgC9DQJN1F1eTj-Lk9rlUANQIQNUCx5OMnQjMpxoXyhfQ")
                .when()
                .get("/movies").
                then().
                statusCode(HttpStatus.SC_OK).
                contentType(ContentType.JSON).
                body("list.size()", greaterThan(0));
    }

    @Test
    public void canDeleteMovie() {
        String movieTitle = "12 gniewnych ludzi";
        String msg = prepareMovieDto(movieTitle,  "test", false);
        Response response =
                given().header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5MzI5NDg0OH0.JRwfFvqiiYEvbxrtUwoPmqOKY963HL-sDWrowStOBqgC9DQJN1F1eTj-Lk9rlUANQIQNUCx5OMnQjMpxoXyhfQ")
                .when()
                .contentType("application/json")
                .body(msg)
                .put("/movie");

        String id = response.body().jsonPath().getString("id");

        given().header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5MzI5NDg0OH0.JRwfFvqiiYEvbxrtUwoPmqOKY963HL-sDWrowStOBqgC9DQJN1F1eTj-Lk9rlUANQIQNUCx5OMnQjMpxoXyhfQ")
                .when()
                .get("/movies").
                then().body(containsString(movieTitle));



        given().header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5MzI5NDg0OH0.JRwfFvqiiYEvbxrtUwoPmqOKY963HL-sDWrowStOBqgC9DQJN1F1eTj-Lk9rlUANQIQNUCx5OMnQjMpxoXyhfQ")
                .when()
                .delete("/movie/"+id)
                .then().statusCode(HttpStatus.SC_OK);
        given().header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5MzI5NDg0OH0.JRwfFvqiiYEvbxrtUwoPmqOKY963HL-sDWrowStOBqgC9DQJN1F1eTj-Lk9rlUANQIQNUCx5OMnQjMpxoXyhfQ")
                .when()
                .get("movies")
                .then().body(not(containsString(movieTitle)));
    }

    @Test
    public void canListUnwatchedMovies() {
        String movieTitle = "12 gniewnych ludzi";
        String msg = prepareMovieDto(movieTitle,  "test", false);
        Response response =
            given().header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5MzI5NDg0OH0.JRwfFvqiiYEvbxrtUwoPmqOKY963HL-sDWrowStOBqgC9DQJN1F1eTj-Lk9rlUANQIQNUCx5OMnQjMpxoXyhfQ")
                .when()
                .contentType("application/json")
                .body(msg)
                .put("/movie");

        movieTitle = "Jablka Adama";
        msg = prepareMovieDto(movieTitle,  "test", false);
        response = given().header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5MzI5NDg0OH0.JRwfFvqiiYEvbxrtUwoPmqOKY963HL-sDWrowStOBqgC9DQJN1F1eTj-Lk9rlUANQIQNUCx5OMnQjMpxoXyhfQ")
                .when()
                .contentType("application/json")
                .body(msg)
                .put("/movie");

        movieTitle = "Polowanie na czerwony pazdziernik";
        msg = prepareMovieDto(movieTitle,  "test", true);
        response = given().header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5MzI5NDg0OH0.JRwfFvqiiYEvbxrtUwoPmqOKY963HL-sDWrowStOBqgC9DQJN1F1eTj-Lk9rlUANQIQNUCx5OMnQjMpxoXyhfQ")
                .when()
                .contentType("application/json")
                .body(msg)
                .put("/movie");


        given().header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5MzI5NDg0OH0.JRwfFvqiiYEvbxrtUwoPmqOKY963HL-sDWrowStOBqgC9DQJN1F1eTj-Lk9rlUANQIQNUCx5OMnQjMpxoXyhfQ")
                .when()
                .get("/movies/unwatched").
                then().body("list.size()", greaterThanOrEqualTo(2));

        given().header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5MzI5NDg0OH0.JRwfFvqiiYEvbxrtUwoPmqOKY963HL-sDWrowStOBqgC9DQJN1F1eTj-Lk9rlUANQIQNUCx5OMnQjMpxoXyhfQ")
                .when()
                .get("/movies/watched").
                then().body("list.size()", greaterThanOrEqualTo(1));



    }

    private String prepareMovieDto(String title, String description, boolean watched){
        MovieDto movie = MovieDto.builder().title(title).description(description).watched(watched).build();
        Gson gson = new Gson();
        return gson.toJson(movie);
    }
}
