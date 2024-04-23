package starwars.api.starwarsapi.external.gateway;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import starwars.api.starwarsapi.domain.SwapiResponse;
import starwars.api.starwarsapi.external.client.SwapiApiClient;

@Service
@RequiredArgsConstructor
public class MoviesGatewayImpl implements MoviesGateway {

    private final SwapiApiClient swapiApiClient;
    @Override
    public SwapiResponse getMovies() {
        try {
            return swapiApiClient.getSwapiMovies().getBody();
        } catch (FeignException e) {
            System.out.println("Erro ao buscar filmes");
            throw e;
        }
    }
}
