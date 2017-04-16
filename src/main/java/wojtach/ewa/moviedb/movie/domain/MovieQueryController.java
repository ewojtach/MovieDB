package wojtach.ewa.moviedb.movie.domain;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import wojtach.ewa.moviedb.user.domain.UserAccountDto;
import wojtach.ewa.moviedb.user.domain.UserAccountFacade;

import java.util.List;

/**
 * Created by ewa on 15.04.2017.
 */

@RestController
@AllArgsConstructor
class MovieQueryController {

    private MovieFacade movieFacade;

    @GetMapping("movies")
    List<MovieDto> getAllMovies(){
        return movieFacade.getAllMovies();
    }

    @GetMapping("movies/watched")
    List<MovieDto> getWatchedMovies(){
        return movieFacade.getWatchedMovies();
    }


    @GetMapping("movies/unwatched")
    List<MovieDto> getUnwatchedMovies(){
        return movieFacade.getUnwatchedMovies();
    }


    @GetMapping("movie/{title}")
    MovieDto getMovieByTitle(@PathVariable String title){
        return movieFacade.getMovieByTitle(title);
    }
}
