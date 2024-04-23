package starwars.api.starwarsapi;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import starwars.api.starwarsapi.services.generatemovies.GenerateMoviesService;


@EnableFeignClients()
@EnableCaching
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class StarWarsApiApplication {

	@Autowired
	private GenerateMoviesService generateMoviesService;

	public static void main(String[] args) {
		SpringApplication.run(StarWarsApiApplication.class, args);
	}

	@PostConstruct
	private void getMovies() {
		generateMoviesService.getMoviesFromApiAndSaveInCache();
	}
}
