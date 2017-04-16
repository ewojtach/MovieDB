package wojtach.ewa.moviedb.movie.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by ewa on 15.04.2017.
 */
@org.springframework.stereotype.Repository ("movieRepository")
interface MovieRepository extends CrudRepository<Movie, UUID> {

    Movie findByTitle(String title);

    Movie findById(UUID id);

    List<Movie> findByWatched(boolean watched);

}
