package wojtach.ewa.moviedb.movie.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by ewa on 15.04.2017.
 */
@Builder
public class MovieDto {

    @Getter private long id;
    @Getter private String title;
    @Getter private String description;
    @Getter private boolean watched;

}
