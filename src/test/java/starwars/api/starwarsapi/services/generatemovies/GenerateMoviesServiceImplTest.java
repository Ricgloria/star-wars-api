package starwars.api.starwarsapi.services.generatemovies;

import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import starwars.api.starwarsapi.domain.Movie;
import starwars.api.starwarsapi.domain.SwapiResponse;
import starwars.api.starwarsapi.external.gateway.MoviesGateway;
import starwars.api.starwarsapi.helper.MoviesGenerateHelper;
import starwars.api.starwarsapi.services.cachemaneger.CacheManagerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenerateMoviesServiceImplTest {

    @InjectMocks
    private GenerateMoviesServiceImpl generateMoviesService;
    @Mock
    private MoviesGateway moviesGateway;
    @Mock
    private CacheManagerService cacheManagerService;
    private final SwapiResponse swapiResponse = MoviesGenerateHelper.createSwapi();

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(generateMoviesService, "url", "http://localhost:8080/v1/star-wars/movie/");
    }

    @Test
    void getMoviesFromApiAndSaveInCache() {
        List<Movie> testList = MoviesGenerateHelper.createMovieList();
        testList.forEach(movie -> movie.setUrl("http://localhost:8080/v1/star-wars/movie/" + movie.getEpisode_id()));
        when(moviesGateway.getMovies()).thenReturn(swapiResponse);
        assertDoesNotThrow(() -> generateMoviesService.getMoviesFromApiAndSaveInCache());
        InOrder inOrder = inOrder(moviesGateway);
        inOrder.verify(moviesGateway, times(1)).getMovies();
        verify(cacheManagerService, times(1))
                .saveMovies(argThat(list -> {
                    for (int i = 0; i < 1; i++) {
                        assertEquals(list.get(i).getUrl(), testList.get(i).getUrl());
                    }
                    return true;
                }));
    }

    @Test
    void getMoviesFromApiAndSaveInCacheError() {
        when(moviesGateway.getMovies()).thenThrow(FeignException.class);
        assertThrows(FeignException.class, () -> generateMoviesService.getMoviesFromApiAndSaveInCache());
    }
}