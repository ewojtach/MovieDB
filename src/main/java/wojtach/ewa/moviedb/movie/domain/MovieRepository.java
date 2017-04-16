package wojtach.ewa.moviedb.movie.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ewa on 15.04.2017.
 */
@org.springframework.stereotype.Repository ("movieRepository")
interface MovieRepository extends CrudRepository<Movie, Long> {

    Movie findByTitle(String title);

    Movie findById(long id);

    List<Movie> findByWatched(boolean watched);

}
