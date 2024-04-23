package starwars.api.starwarsapi.domain;

import org.junit.jupiter.api.Test;
import starwars.api.starwarsapi.helper.MoviesGenerateHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SwapiResponseTest {

    @Test
    public void testGetterAndSetter() {
        SwapiResponse swapiResponse = new SwapiResponse();
        swapiResponse.setCount(10);
        swapiResponse.setNext("nextUrl");
        swapiResponse.setPrevious("previousUrl");
        swapiResponse.setResults(Collections.emptyList());

        assertEquals(10, swapiResponse.getCount());
        assertEquals("nextUrl", swapiResponse.getNext());
        assertEquals("previousUrl", swapiResponse.getPrevious());
        assertEquals(Collections.emptyList(), swapiResponse.getResults());
    }

    @Test
    public void testBuilder() {
        List<Movie> movies = Arrays.asList(
                MoviesGenerateHelper.generateMovie(),
                MoviesGenerateHelper.generateMovie()
        );

        SwapiResponse swapiResponse = SwapiResponse.builder()
                .count(2)
                .next("nextUrl")
                .previous("previousUrl")
                .results(movies)
                .build();

        assertEquals(2, swapiResponse.getCount());
        assertEquals("nextUrl", swapiResponse.getNext());
        assertEquals("previousUrl", swapiResponse.getPrevious());
        assertEquals(movies, swapiResponse.getResults());
    }
}