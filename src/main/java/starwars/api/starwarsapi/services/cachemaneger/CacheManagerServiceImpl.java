package starwars.api.starwarsapi.services.cachemaneger;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import starwars.api.starwarsapi.domain.Movie;

import java.text.SimpleDateFormat;
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
            cacheManager.getCacheNames().forEach(name -> {
                Cache cache = cacheManager.getCache(name);
                if (nonNull(cache)) {
                    Movie movie = cache.get(name, Movie.class);
                    movies.add(movie);
                }
            });
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
    public void updateMovie(String id, Movie movie) {
        try {
            Movie editedMovie = generateEditedMovie(id, movie);
            requireNonNull(cacheManager.getCache(id)).put(id, editedMovie);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar filmes");
            throw e;
        }
    }

    private Movie generateEditedMovie(String id, Movie movie) {
        Movie oldMovie = internalGetMovie(id);
        movie.setCreated(oldMovie.getCreated());
        movie.setVersion(oldMovie.getVersion() + 1);
        movie.setUrl(oldMovie.getUrl());
        movie.setEdited(getDate());
        return movie;
    }

    private Movie internalGetMovie(String id) {
        try {
            return requireNonNull(cacheManager.getCache(id)).get(id, Movie.class);
        } catch (Exception e) {
            System.out.println("Erro ao buscar filmes");
            throw e;
        }
    }

    private String getDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        return dateFormat.format(date);
    }
}
