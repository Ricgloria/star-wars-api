package starwars.api.starwarsapi.external.gateway;

import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import starwars.api.starwarsapi.helper.MoviesGenerateHelper;
import starwars.api.starwarsapi.domain.SwapiResponse;
import starwars.api.starwarsapi.external.client.SwapiApiClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoviesGatewayImplTest {

    @InjectMocks
    private MoviesGatewayImpl moviesGateway;

    @Mock
    private SwapiApiClient swapiApiClient;

    private final SwapiResponse swapiResponse = MoviesGenerateHelper.createSwapi();

    @Test
    void getMovies() {
        when(swapiApiClient.getSwapiMovies()).thenReturn(ResponseEntity.ok(swapiResponse));
        SwapiResponse response = assertDoesNotThrow(() -> moviesGateway.getMovies());
        assertEquals(swapiResponse, response);
    }

    @Test
    void getMoviesError() {
        when(swapiApiClient.getSwapiMovies()).thenThrow(FeignException.class);
        assertThrows(FeignException.class, () -> moviesGateway.getMovies());
    }

}