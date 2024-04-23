package starwars.api.starwarsapi.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import starwars.api.starwarsapi.domain.SwapiResponse;

@FeignClient(name = "${services.swapiapi.name}", url = "${services.swapiapi.url}")
public interface SwapiApiClient {
    @GetMapping(value = "/films/")
    ResponseEntity<SwapiResponse> getSwapiMovies();
}
