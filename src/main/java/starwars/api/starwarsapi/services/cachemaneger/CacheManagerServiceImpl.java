package starwars.api.starwarsapi.services.cachemaneger;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import starwars.api.starwarsapi.domain.Movie;

import java.util.*;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class CacheManagerServiceImpl implements CacheManagerService {

    private final CacheManager cacheManager;

    @Override
    public void saveMovies(List<Movie> movies) {
        try {
            movies.forEach(movie -> requireNonNull(cacheManager.getCache(movie.getEpisode_id().toString())).put(movie.getEpisode_id().toString(), movie));
        } catch (Exception e) {
            System.out.println("Erro ao salvar filmes");
            throw e;
        }
    }

    @Override
    public List<Movie> getMovies() {
        try {
            List<Movie> movies = new ArrayList<>();

            for (String name : cacheManager.getCacheNames()) {
                Cache cache = cacheManager.getCache(name);
                if(nonNull(cache)) {
                    Movie movie = cache.get(name, Movie.class);
                    movies.add(movie);
                }
            }
            return movies;

        } catch (Exception e) {
            System.out.println("Erro ao listar filmes");
            throw e;
        }
    }

    @Override
    public Movie getMovie(String id) {
        return internalGetMovie(id);
    }

    @Override
    public void updateMovie(String id, String detail) {
        try {
            Movie movie = internalGetMovie(id);
            movie.setOpening_crawl(detail);
            movie.setVersion(movie.getVersion() + 1);
            requireNonNull(cacheManager.getCache(id)).put(id, movie);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar filmes");
            throw e;
        }
    }

    private Movie internalGetMovie(String id) {
        try {
            return requireNonNull(cacheManager.getCache(id)).get(id, Movie.class);
        } catch (Exception e) {
            System.out.println("Erro ao buscar filmes");
            throw e;
        }
    }
}
