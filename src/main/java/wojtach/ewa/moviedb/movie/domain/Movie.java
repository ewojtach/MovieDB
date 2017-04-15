package wojtach.ewa.moviedb.movie.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ewa on 15.04.2017.
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
class Movie {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter private long id;

    @Getter private String title;

    @Getter private String description;

    @Getter private boolean watched;
}
