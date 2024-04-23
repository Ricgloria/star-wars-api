package starwars.api.starwarsapi.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import starwars.api.starwarsapi.domain.Movie;
import starwars.api.starwarsapi.resource.dto.MovieRequestDto;

import java.util.List;

@Tag(name = "Star Wars")
public interface StarWarsApiSwagger {
    @Operation(summary = "Lista todos os filmes da saga", description = "Retorna uma lista com todos os filmes da saga")
    @ApiResponse(responseCode = "200", description = "Os filmes foram retornados com sucesso")
    ResponseEntity<List<Movie>> getAllMovies();

    @Operation(summary = "Retorna um filme da saga", description = "Retorna um filme da saga com suas informações")
    @ApiResponse(responseCode = "200", description = "O filme foi encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "O filme não foi encontrado")
    ResponseEntity<Movie> getMovie(@PathVariable String episode_id);


    @Operation(summary = "Edita um filme da saga", description = "Edita um filme da saga com suas informações")
    @ApiResponse(responseCode = "204", description = "O filme foi editado com sucesso")
    ResponseEntity<Void> updateMovieDetail(@RequestBody @Valid MovieRequestDto requestDto);
}
