package starwars.api.starwarsapi.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import starwars.api.starwarsapi.domain.Movie;
import starwars.api.starwarsapi.services.managemovies.ManageMoviesService;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/star-wars")
public class StarWarsApiResource {

    private final ManageMoviesService manageMoviesService;

    @GetMapping(value = "/all-movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(manageMoviesService.getAllMovies());
    }

    @GetMapping(value = "/movie/{episode_id}")
    public ResponseEntity<Movie> getMovie(@PathVariable String episode_id) {
        Movie movie = manageMoviesService.getMovieById(episode_id);
        return nonNull(movie) ? ResponseEntity.ok(movie) : ResponseEntity.notFound().build();
    }

    @PatchMapping( value = "/movie/{episode_id}")
    public ResponseEntity<Void> updateMovieDetail(@PathVariable String episode_id, @RequestBody String detail) {
        manageMoviesService.updateDetailsById(episode_id, detail);
        return ResponseEntity.noContent().build();
    }
}
