package starwars.api.starwarsapi.helper;

import starwars.api.starwarsapi.domain.Movie;
import starwars.api.starwarsapi.domain.SwapiResponse;
import starwars.api.starwarsapi.resource.dto.MovieRequestDto;

import java.util.ArrayList;
import java.util.List;

public abstract class MoviesGenerateHelper {

    public static SwapiResponse createSwapi() {
        return SwapiResponse
                .builder()
                .count(1)
                .next("next_url")
                .previous("previous_url")
                .results(createMovieList())
                .build();
    }

    public static List<Movie> createMovieList() {
        List<Movie> movies = new ArrayList<>();

        Movie movie1 = generateMovie();

        Movie movie2 = Movie.builder()
                .title("Movie 2")
                .episode_id(2)
                .opening_crawl("Opening crawl 2")
                .director("Director 2")
                .producer("Producer 2")
                .release_date("2024-04-24")
                .characters(List.of("Character 3", "Character 4"))
                .planets(List.of("Planet 3", "Planet 4"))
                .starships(List.of("Starship 3", "Starship 4"))
                .vehicles(List.of("Vehicle 3", "Vehicle 4"))
                .species(List.of("Species 3", "Species 4"))
                .created("created2")
                .edited("edited2")
                .url("url2")
                .version(1)
                .build();

        movies.add(movie1);
        movies.add(movie2);

        return movies;
    }

    public static Movie generateMovie() {
        return Movie.builder()
                .title("Movie 1")
                .episode_id(1)
                .opening_crawl("Opening crawl 1")
                .director("Director 1")
                .producer("Producer 1")
                .release_date("2024-04-23")
                .characters(List.of("Character 1", "Character 2"))
                .planets(List.of("Planet 1", "Planet 2"))
                .starships(List.of("Starship 1", "Starship 2"))
                .vehicles(List.of("Vehicle 1", "Vehicle 2"))
                .species(List.of("Species 1", "Species 2"))
                .created("created1")
                .edited("edited1")
                .url("url1")
                .version(1)
                .build();
    }

    public static MovieRequestDto generateMovieRequest() {
        return MovieRequestDto.builder()
                .title("Movie 1")
                .episode_id(1)
                .opening_crawl("Opening crawl 1")
                .director("Director 1")
                .producer("Producer 1")
                .release_date("2024-04-23")
                .characters(List.of("Character 1", "Character 2"))
                .planets(List.of("Planet 1", "Planet 2"))
                .starships(List.of("Starship 1", "Starship 2"))
                .vehicles(List.of("Vehicle 1", "Vehicle 2"))
                .species(List.of("Species 1", "Species 2"))
                .build();
    }
}
