package wojtach.ewa.moviedb.movie.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

/**
 * Created by ewa on 15.04.2017.
 */
public class MovieSpec {

    @Mock
    MovieRepository movieRepository;

    MovieFacade facade = new MovieConfiguration().movieFacade();

    MovieDto testMovie = createMovieDto("film","opis", false);
    Movie testMovieValue = createMovieValue("film", "opis", false);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        facade.setMovieRepository(this.movieRepository);

        given(this.movieRepository.save(any(Movie.class)))
                .willReturn(testMovieValue);


    }

    @Test
    public void shouldAddMovie() {
        MovieDto movie = facade.addMovie(testMovie);

        given(this.movieRepository.findByTitle(testMovie.getTitle()))
                .willReturn(Movie.builder().id(UUID.randomUUID())
                        .title(testMovie.getTitle()).description("opis").build());


        //then: system has this user
        assertNotNull(facade.getMovieByTitle(testMovie.getTitle()));

    }

    @Test (expected=MovieAlreadyExistsException.class)
    public void shouldNotAllowToAddTwoMoviesWithSameTitle() {
        // when: user adds movie

        MovieDto movie = facade.addMovie(testMovie);

        given(this.movieRepository.findByTitle(testMovie.getTitle()))
                .willReturn(Movie.builder().id(UUID.randomUUID()).title(testMovie.getTitle()).description("opis").build());


        MovieDto sameMovie = facade.addMovie(testMovie);
    }

    @Test
    public void shouldRemoveMovie() {
        // when: user adds movie
        MovieDto movie = facade.addMovie(testMovie);
        UUID movieId = UUID.randomUUID();

        given(this.movieRepository.findById(movieId))
                .willReturn(Movie.builder().id(movieId).title(testMovie.getTitle()).description("opis").build());

        // when: user removes account
        facade.deleteMovie(movieId.toString());

    }

    @Test (expected=MovieNotFoundException.class)
    public void shouldNotAllowToRemoveUnexistingMovie() {
        // when: user removes not existing movie

        facade.deleteMovie(UUID.randomUUID().toString());
    }

    private MovieDto createMovieDto(String title, String description, boolean watched) {
        return MovieDto.builder().title(title).description(description).watched(watched).build();
    }

    private Movie createMovieValue(String title, String description, boolean watched) {
        return Movie.builder().title(title).description(description).watched(watched).build();
    }

}