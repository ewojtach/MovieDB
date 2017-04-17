package wojtach.ewa.moviedb.movie.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ewa on 15.04.2017.
 */

@Configuration
@ComponentScan({"wojtach.ewa.moviedb.movie.domain"})
class MovieConfiguration {

    @Bean
    public MovieFacade movieFacade() {
        return new MovieFacade();
    }
}
