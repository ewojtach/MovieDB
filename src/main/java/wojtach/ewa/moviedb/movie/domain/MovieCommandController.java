package wojtach.ewa.moviedb.movie.domain;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wojtach.ewa.moviedb.user.domain.UserAccountDto;
import wojtach.ewa.moviedb.user.domain.UserAccountFacade;

import javax.xml.ws.Response;

/**
 * Created by ewa on 15.04.2017.
 */

@RestController
@AllArgsConstructor
class MovieCommandController {
    private MovieFacade movieFacade;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @PutMapping("api/movie")
    @ResponseStatus(code = HttpStatus.CREATED)
    ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movie){

        try {
            MovieDto response = movieFacade.addMovie(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (MovieAlreadyExistsException e) {
            log.error("movie already exist: "+movie.getTitle());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("api/movie")
    @ResponseStatus(code = HttpStatus.OK)
    MovieDto updateMovie(@RequestBody MovieDto movie){
        return movieFacade.updateMovie(movie);
    }

    @DeleteMapping("api/movie/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<MovieDto> deleteMovie(@PathVariable("id") String id){
        try {
            movieFacade.deleteMovie(id);
            return ResponseEntity.ok(null);
        } catch (MovieNotFoundException e) {
            log.error("movie does not exist: "+id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
