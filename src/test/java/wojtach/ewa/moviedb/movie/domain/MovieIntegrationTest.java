package wojtach.ewa.moviedb.movie.domain;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

/**
 * Created by ewa on 16.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieIntegrationTest {

    public static final String API_MOVIE = "/api/movie";
    public static final String API_MOVIES = "/api/movies";
    public static final String API_MOVIES_UNWATCHED = "/api/movies/unwatched";
    public static final String API_MOVIES_WATCHED = "/api/movies/watched";

    public static final String SAMPLE_AUTH = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5MzI5NDg0OH0.JRwfFvqiiYEvbxrtUwoPmqOKY963HL-sDWrowStOBqgC9DQJN1F1eTj-Lk9rlUANQIQNUCx5OMnQjMpxoXyhfQ";

    @Value("${local.server.port}")
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void canAddMovieAndFetchIt() {
        //  given().
        String title = "movie123";
        String msg = prepareMovieDto(title, "desc", true);

        given().header("Authorization", SAMPLE_AUTH)
                .when()
                .contentType("application/json")
                .body(msg)
                .when()
                .put(API_MOVIE)
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        given().header("Authorization", SAMPLE_AUTH)
                .when()
                .get(API_MOVIES).
                then().body(containsString(title));
        given().header("Authorization", SAMPLE_AUTH)
                .when()
                .get(API_MOVIES).
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
                given().header("Authorization", SAMPLE_AUTH)
                .when()
                .contentType("application/json")
                .body(msg)
                .put(API_MOVIE);

        String id = response.body().jsonPath().getString("id");

        given().header("Authorization", SAMPLE_AUTH)
                .when()
                .get(API_MOVIES).
                then().body(containsString(movieTitle));



        given().header("Authorization", SAMPLE_AUTH)
                .when()
                .delete(API_MOVIE+"/"+id)
                .then().statusCode(HttpStatus.SC_OK);
        given().header("Authorization", SAMPLE_AUTH)
                .when()
                .get(API_MOVIES)
                .then().body(not(containsString(movieTitle)));
    }

    @Test
    public void canListUnwatchedMovies() {
        String movieTitle = "12 gniewnych ludzi";
        String msg = prepareMovieDto(movieTitle,  "test", false);
        Response response =
            given().header("Authorization", SAMPLE_AUTH)
                .when()
                .contentType("application/json")
                .body(msg)
                .put(API_MOVIE);

        movieTitle = "Jablka Adama";
        msg = prepareMovieDto(movieTitle,  "test", false);
        response = given().header("Authorization", SAMPLE_AUTH)
                .when()
                .contentType("application/json")
                .body(msg)
                .put(API_MOVIE);

        movieTitle = "Polowanie na czerwony pazdziernik";
        msg = prepareMovieDto(movieTitle,  "test", true);
        response = given().header("Authorization", SAMPLE_AUTH)
                .when()
                .contentType("application/json")
                .body(msg)
                .put(API_MOVIE);


        given().header("Authorization", SAMPLE_AUTH)
                .when()
                .get(API_MOVIES_UNWATCHED).
                then().body("list.size()", greaterThanOrEqualTo(2));

        given().header("Authorization", SAMPLE_AUTH)
                .when()
                .get(API_MOVIES_WATCHED).
                then().body("list.size()", greaterThanOrEqualTo(1));



    }

    @Test
    public void cannotFetchMoviesWithoutAuthentication() {
        //  given().
        String title = "movie123";
        String msg = prepareMovieDto(title, "desc", true);

        given().when()
                .contentType("application/json")
                .body(msg)
                .when()
                .put(API_MOVIE)
                .then()
                .statusCode(403);
    }


    private String prepareMovieDto(final String title, final String description, final boolean watched){
        MovieDto movie = MovieDto.builder()
                .title(title)
                .description(description)
                .watched(watched)
                .build();
        Gson gson = new Gson();
        return gson.toJson(movie);
    }
}
