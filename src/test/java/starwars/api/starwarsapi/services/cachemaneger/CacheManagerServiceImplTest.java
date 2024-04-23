package starwars.api.starwarsapi.services.cachemaneger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import starwars.api.starwarsapi.domain.Movie;
import starwars.api.starwarsapi.helper.MoviesGenerateHelper;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CacheManagerServiceImplTest {

    @InjectMocks
    private CacheManagerServiceImpl cacheManagerService;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private CacheManager cacheManager;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Cache cache;

    private final List<Movie> movies = MoviesGenerateHelper.createMovieList();

    @Test
    void saveMovies() {
        assertDoesNotThrow(() -> cacheManagerService.saveMovies(movies));
        movies.forEach(movie -> verify(cacheManager).getCache(movie.getEpisode_id().toString()));
    }

    @Test
    void saveMoviesError() {
        when(cacheManager.getCache(anyString())).thenThrow(MockitoException.class);
        assertThrows(MockitoException.class, () -> cacheManagerService.saveMovies(movies));
    }

    @Test
    void getMovies() {
        when(cacheManager.getCacheNames()).thenReturn(List.of("1"));
        when(cacheManager.getCache("1")).thenReturn(cache);
        when(cache.get("1", Movie.class)).thenReturn(MoviesGenerateHelper.generateMovie());
        List<Movie> movieList = assertDoesNotThrow(() -> cacheManagerService.getMovies());
        assertEquals(movieList.size(), 1);
        assertEquals(movieList.get(0).getEpisode_id(), MoviesGenerateHelper.generateMovie().getEpisode_id());
    }

    @Test
    void getMoviesNullCache() {
        when(cacheManager.getCacheNames()).thenReturn(List.of("1"));
        when(cacheManager.getCache("1")).thenReturn(null);
        List<Movie> movieList = assertDoesNotThrow(() -> cacheManagerService.getMovies());
        assertEquals(movieList.size(), 0);
    }

    @Test
    void getMoviesError() {
        when(cacheManager.getCacheNames()).thenThrow(MockitoException.class);
        assertThrows(MockitoException.class, () -> cacheManagerService.getMovies());
    }

    @Test
    void getMovie() {
        Movie movie = MoviesGenerateHelper.generateMovie();
        when(Objects.requireNonNull(cacheManager.getCache("1")).get("1", Movie.class)).thenReturn(movie);
        Movie movie2 = assertDoesNotThrow(() -> cacheManagerService.getMovie("1"));
        assertEquals(movie, movie2);
    }

    @Test
    void getMovieError() {
        when(Objects.requireNonNull(cacheManager.getCache("1")).get("1", Movie.class)).thenThrow(MockitoException.class);
        assertThrows(MockitoException.class, () -> cacheManagerService.getMovie("1"));
    }

    @Test
    void updateMovie() {
        Movie newMovie = MoviesGenerateHelper.generateMovie();
        newMovie.setOpening_crawl("Para Teste");
        Movie oldMovie = MoviesGenerateHelper.generateMovie();
        oldMovie.setCreated("XPTO");
        oldMovie.setUrl("ulr.com");
        oldMovie.setVersion(2);
        when(Objects.requireNonNull(cacheManager.getCache("1")).get("1", Movie.class)).thenReturn(oldMovie);
        assertDoesNotThrow(() -> cacheManagerService.updateMovie("1", newMovie));
        verify(cacheManager.getCache("1")).put(eq("1"), argThat(o -> {
            Movie param = (Movie) o;
            assertEquals(param.getOpening_crawl(), "Para Teste");
            assertEquals(param.getCreated(), oldMovie.getCreated());
            assertEquals(param.getUrl(), oldMovie.getUrl());
            assertEquals(param.getVersion(), 3);
            return true;
        }));
    }

    @Test
    void updateMovieError() {
        Movie newMovie = MoviesGenerateHelper.generateMovie();
        when(Objects.requireNonNull(cacheManager.getCache("1")).get("1", Movie.class)).thenThrow(MockitoException.class);
        assertThrows(MockitoException.class, () -> cacheManagerService.updateMovie("1", newMovie));
    }
}