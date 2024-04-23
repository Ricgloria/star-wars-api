package starwars.api.starwarsapi.resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import starwars.api.starwarsapi.domain.Movie;
import starwars.api.starwarsapi.helper.MoviesGenerateHelper;
import starwars.api.starwarsapi.resource.dto.MovieRequestDto;
import starwars.api.starwarsapi.services.managemovies.ManageMoviesService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StarWarsApiResourceTest {

    @InjectMocks
    private StarWarsApiResource starWarsApiResource;
    @Mock
    private ManageMoviesService manageMoviesService;

    @Test
    void getAllMovies() {
        when(manageMoviesService.getAllMovies()).thenReturn(MoviesGenerateHelper.createMovieList());
        assertDoesNotThrow(() -> starWarsApiResource.getAllMovies());
        verify(manageMoviesService, times(1)).getAllMovies();
        verify(manageMoviesService, times(0)).getMovieById(anyString());
        verify(manageMoviesService, times(0)).updateMovieDetails(any());
    }

    @Test
    void getMovie() {
        when(manageMoviesService.getMovieById("1")).thenReturn(MoviesGenerateHelper.generateMovie());
        assertDoesNotThrow(() -> starWarsApiResource.getMovie("1"));
        verify(manageMoviesService, times(1)).getMovieById("1");
        verify(manageMoviesService, times(0)).getAllMovies();
        verify(manageMoviesService, times(0)).updateMovieDetails(any());
    }

    @Test
    void getMovieNotFound() {
        when(manageMoviesService.getMovieById("1")).thenReturn(null);
        ResponseEntity<Movie> response = assertDoesNotThrow(() -> starWarsApiResource.getMovie("1"));
        assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(404));
        verify(manageMoviesService, times(1)).getMovieById("1");
        verify(manageMoviesService, times(0)).getAllMovies();
        verify(manageMoviesService, times(0)).updateMovieDetails(any());
    }

    @Test
    void updateMovieDetail() {
        MovieRequestDto movieRequestDto = MoviesGenerateHelper.generateMovieRequest();
        assertDoesNotThrow(() -> starWarsApiResource.updateMovieDetail(movieRequestDto));
        verify(manageMoviesService, times(1)).updateMovieDetails(any(Movie.class));
        verify(manageMoviesService, times(0)).getMovieById(anyString());
        verify(manageMoviesService, times(0)).getAllMovies();
    }
}