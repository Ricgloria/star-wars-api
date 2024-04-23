package starwars.api.starwarsapi.config.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "swagger")
@Getter
@Setter
public class SpringDocProperties {
    private String version;
    private String title;
    private String description;
    private String license;
    private String licenseUrl;
}
