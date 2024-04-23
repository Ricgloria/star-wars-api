package starwars.api.starwarsapi.services.generatemovies;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import starwars.api.starwarsapi.domain.Movie;
import starwars.api.starwarsapi.external.gateway.MoviesGateway;
import starwars.api.starwarsapi.services.cachemaneger.CacheManagerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenerateMoviesServiceImpl implements GenerateMoviesService {

    private final MoviesGateway moviesGateway;
    private final CacheManagerService cacheManagerService;
    @Value("${internal.details.url}")
    private String url;

    @Override
    public void getMoviesFromApiAndSaveInCache() {
        try {
            cacheManagerService.saveMovies(getMovies());
        } catch (Exception ex) {
            System.out.println(ex.toString());
            throw ex;
        }
    }

    private List<Movie> getMovies() {
        List<Movie> movies = moviesGateway.getMovies().getResults();
        movies.forEach(movie -> movie.setUrl(url + movie.getEpisode_id()));
        return movies;
    }
}
