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

    @PutMapping("movie")
    @ResponseStatus(code = HttpStatus.CREATED)
    MovieDto addMovie(@RequestBody MovieDto movie){
        return movieFacade.addMovie(movie);
    }

}
