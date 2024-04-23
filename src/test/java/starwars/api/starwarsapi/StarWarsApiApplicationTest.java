package starwars.api.starwarsapi;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import starwars.api.starwarsapi.services.generatemovies.GenerateMoviesService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.Mockito.verify;

@SpringBootTest
class StarWarsApiApplicationTest {
    @Mock
    private GenerateMoviesService generateMoviesService;

    @InjectMocks
    private StarWarsApiApplication starWarsApiApplication;

    @Test
    public void testGetMoviesOnStartUp() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = StarWarsApiApplication.class.getDeclaredMethod("getMovies");
        method.setAccessible(true);
        method.invoke(starWarsApiApplication);

        verify(generateMoviesService).getMoviesFromApiAndSaveInCache();
    }
}