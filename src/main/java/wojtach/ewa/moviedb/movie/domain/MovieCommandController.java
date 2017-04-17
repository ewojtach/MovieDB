package wojtach.ewa.moviedb.movie.domain;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wojtach.ewa.moviedb.user.domain.UserAccountDto;
import wojtach.ewa.moviedb.user.domain.UserAccountFacade;

/**
 * Created by ewa on 15.04.2017.
 */

@RestController
@AllArgsConstructor
class MovieCommandController {
    private MovieFacade movieFacade;

    @PutMapping("api/movie")
    @ResponseStatus(code = HttpStatus.CREATED)
    MovieDto addMovie(@RequestBody MovieDto movie){
        return movieFacade.addMovie(movie);
    }

    @PostMapping("api/movie")
    @ResponseStatus(code = HttpStatus.OK)
    MovieDto updateMovie(@RequestBody MovieDto movie){
        return movieFacade.updateMovie(movie);
    }

    @DeleteMapping("api/movie/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    void deleteMovie(@PathVariable("id") String id){
        movieFacade.deleteMovie(id);
    }

}
