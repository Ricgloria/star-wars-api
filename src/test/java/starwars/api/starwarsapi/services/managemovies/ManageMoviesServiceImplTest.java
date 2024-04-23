package starwars.api.starwarsapi.services.managemovies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import starwars.api.starwarsapi.domain.Movie;
import starwars.api.starwarsapi.helper.MoviesGenerateHelper;
import starwars.api.starwarsapi.services.cachemaneger.CacheManagerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManageMoviesServiceImplTest {

    @InjectMocks
    private ManageMoviesServiceImpl manageMoviesService;
    @Mock
    private CacheManagerService cacheManagerService;


    @Test
    void getAllMovies() {
        when(cacheManagerService.getMovies()).thenReturn(MoviesGenerateHelper.createMovieList());
        List<Movie> movies = assertDoesNotThrow(()-> manageMoviesService.getAllMovies());
        assertEquals(movies.size(), MoviesGenerateHelper.createMovieList().size());
        verify(cacheManagerService, times(1)).getMovies();
    }

    @Test
    void getMovieById() {
        Movie mockMovie = MoviesGenerateHelper.generateMovie();
        when(cacheManagerService.getMovie("1")).thenReturn(mockMovie);
        Movie movie = assertDoesNotThrow(()-> manageMoviesService.getMovieById("1"));
        verify(cacheManagerService, times(1)).getMovie("1");
        assertEquals(movie, mockMovie);
    }

    @Test
    void updateMovieDetails() {
        Movie mockMovie = MoviesGenerateHelper.generateMovie();
        assertDoesNotThrow(()-> manageMoviesService.updateMovieDetails(mockMovie));
        verify(cacheManagerService, times(1)).updateMovie("1", mockMovie);
    }
}