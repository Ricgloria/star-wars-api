package starwars.api.starwarsapi.services.managemovies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import starwars.api.starwarsapi.domain.Movie;
import starwars.api.starwarsapi.services.cachemaneger.CacheManagerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageMoviesServiceImpl implements ManageMoviesService {

    private final CacheManagerService cacheManagerService;

    @Override
    public List<Movie> getAllMovies() {
        return cacheManagerService.getMovies();
    }

    @Override
    public Movie getMovieById(String id) {
        return cacheManagerService.getMovie(id);
    }

    @Override
    public void updateDetailsById(String id, String detail) {
        cacheManagerService.updateMovie(id, detail);
    }
}
