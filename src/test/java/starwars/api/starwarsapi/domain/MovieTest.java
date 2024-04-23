package starwars.api.starwarsapi.domain;

import org.junit.jupiter.api.Test;
import starwars.api.starwarsapi.helper.MoviesGenerateHelper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
    @Test
    public void testMovie() {
        Movie movie = MoviesGenerateHelper.generateMovie();

        assertEquals("Movie 1", movie.getTitle());
        assertEquals(1, movie.getEpisode_id());
        assertEquals("Opening crawl 1", movie.getOpening_crawl());
        assertEquals("Director 1", movie.getDirector());
        assertEquals("Producer 1", movie.getProducer());
        assertEquals("2024-04-23", movie.getRelease_date());
        assertEquals(List.of("Character 1", "Character 2"), movie.getCharacters());
        assertEquals(List.of("Planet 1", "Planet 2"), movie.getPlanets());
        assertEquals(List.of("Starship 1", "Starship 2"), movie.getStarships());
        assertEquals(List.of("Vehicle 1", "Vehicle 2"), movie.getVehicles());
        assertEquals(List.of("Species 1", "Species 2"), movie.getSpecies());
        assertEquals("created1", movie.getCreated());
        assertEquals("edited1", movie.getEdited());
        assertEquals("url1", movie.getUrl());
        assertEquals(1, movie.getVersion());
    }
}