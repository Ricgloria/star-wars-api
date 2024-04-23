package starwars.api.starwarsapi.services.managemovies;

import starwars.api.starwarsapi.domain.Movie;

import java.util.List;

public interface ManageMoviesService {
    List<Movie> getAllMovies();
    Movie getMovieById(String id);
    void updateMovieDetails(Movie movie);
}
