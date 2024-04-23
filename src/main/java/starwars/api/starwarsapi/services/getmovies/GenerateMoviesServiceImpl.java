package starwars.api.starwarsapi.services.getmovies;

import lombok.RequiredArgsConstructor;
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

    @Override
    public void getMoviesFromApiAndSaveInCache() {
        try {
            List<Movie> movies = moviesGateway.getMovies().getResults();
            cacheManagerService.saveMovies(movies);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            throw ex;
        }
    }
}
