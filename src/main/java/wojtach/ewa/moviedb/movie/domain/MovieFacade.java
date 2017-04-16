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

        if (movieRepository.findByTitle(movie.getTitle() )!= null) throw new MovieAlreadyExistsException();

        return convertToDto(movieRepository.save(convertToEntity(movie)));
    }

    public MovieDto updateMovie(MovieDto movie){
        return convertToDto(movieRepository.save(convertToEntity(movie)));
    }

    public void deleteMovie(String movieId){

        Movie movie = movieRepository.findById(Long.parseLong(movieId));

        if (movie == null) throw new MovieNotFoundException();

        movieRepository.delete(movie.getId());
    }


    public List<MovieDto> getAllMovies(){
        return StreamSupport.stream(movieRepository.findAll().spliterator(), false)
                .map(entity -> convertToDto(entity)).collect(Collectors.toList());
    }

    public List<MovieDto> getWatchedMovies(){
        return StreamSupport.stream(movieRepository.findByWatched(true).spliterator(), false)
                .map(entity -> convertToDto(entity)).collect(Collectors.toList());
    }

    public List<MovieDto> getUnwatchedMovies(){
        return StreamSupport.stream(movieRepository.findByWatched(false).spliterator(), false)
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
