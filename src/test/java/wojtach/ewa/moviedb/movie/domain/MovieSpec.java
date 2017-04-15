package wojtach.ewa.moviedb.movie.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ewa on 15.04.2017.
 */
public class MovieSpec {

    MovieFacade facade = new MovieConfiguration().movieFacade();

    MovieDto testMovie = createMovieDto("film","opis", false);




    @Test
    public void shouldAddMovie() {
        MovieDto movie = facade.addMovie(testMovie);

        //then: system has this user
        assertNotNull(facade.getMovieByTitle(testMovie.getTitle()));

    }


    private MovieDto createMovieDto(String title, String description, boolean watched) {
        return MovieDto.builder().title(title).description(description).watched(watched).build();
    }

}