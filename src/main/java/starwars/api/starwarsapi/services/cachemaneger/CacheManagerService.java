package starwars.api.starwarsapi.services.cachemaneger;

import starwars.api.starwarsapi.domain.Movie;

import java.util.List;

public interface CacheManagerService {

    void saveMovies(List<Movie> movies);
    List<Movie> getMovies();
    Movie getMovie(String id);
    void updateMovie(String id, Movie movie);
}
