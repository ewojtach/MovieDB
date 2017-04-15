package wojtach.ewa.moviedb.movie.domain;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by ewa on 15.04.2017.
 */
public class MovieFacade {

    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private MovieRepository movieRepository;



    public MovieDto addMovie(MovieDto movie){
        return convertToDto(movieRepository.save(convertToEntity(movie)));
    }

    public List<MovieDto> getAllMovies(){
        return StreamSupport.stream(movieRepository.findAll().spliterator(), false)
                .map(entity -> convertToDto(entity)).collect(Collectors.toList());
    }

    public MovieDto getMovieByTitle(String title){
        return convertToDto(movieRepository.findByTitle(title));
    }


    private MovieDto convertToDto(Movie movie){
        return MovieDto.builder().title(movie.getTitle()).description(movie.getDescription())
                .id(movie.getId()).watched(movie.isWatched()).build();
    }

    private Movie convertToEntity(MovieDto movieDto){
        return Movie.builder()
                .title(movieDto.getTitle())
                .description(movieDto.getDescription())
                .id(movieDto.getId()).watched(movieDto.isWatched()).build();
    }
}
