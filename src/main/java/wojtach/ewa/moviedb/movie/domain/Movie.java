package wojtach.ewa.moviedb.movie.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.UUID;

/**
 * Created by ewa on 15.04.2017.
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
class Movie {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Getter private UUID id;

    @Getter private String title;

    @Getter private String description;

    @Getter private boolean watched;
}
