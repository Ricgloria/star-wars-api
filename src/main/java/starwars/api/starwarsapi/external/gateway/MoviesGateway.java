package starwars.api.starwarsapi.external.gateway;

import starwars.api.starwarsapi.domain.SwapiResponse;

public interface MoviesGateway {
    SwapiResponse getMovies();
}
