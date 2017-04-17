package wojtach.ewa.moviedb.movie.domain;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
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



    public MovieDto addMovie(MovieDto movie) throws MovieAlreadyExistsException {

        if (movieRepository.findByTitle(movie.getTitle() )!= null)  {
            throw new MovieAlreadyExistsException();
        }
        return convertToDto(movieRepository.save(convertToEntity(movie)));
    }

    public MovieDto updateMovie(MovieDto movie){
        return convertToDto(movieRepository.save(convertToEntity(movie)));
    }

    public void deleteMovie(String movieId) throws MovieNotFoundException {

        Movie movie = movieRepository.findById(UUID.fromString(movieId));

        if (movie == null) {
            throw new MovieNotFoundException();
        }

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

        String id = null;
        if (movie.getId() != null ){
            id = movie.getId().toString();
        }

        return MovieDto.builder().title(movie.getTitle()).description(movie.getDescription())
                .id(id).watched(movie.isWatched()).build();
    }

    private Movie convertToEntity(MovieDto movieDto){
        UUID id = null;
        if (movieDto.getId() != null){
            id = UUID.fromString(movieDto.getId());
        }

        return Movie.builder()
                .title(movieDto.getTitle())
                .description(movieDto.getDescription())
                .id(id).watched(movieDto.isWatched()).build();
    }
}
