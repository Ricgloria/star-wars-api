package starwars.api.starwarsapi.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringDocConfig {
    private final SpringDocProperties springDocProperties;

    @Bean
    public OpenAPI springOpenAPI() {

        final License license = new License()
                .name(springDocProperties.getLicense())
                .url(springDocProperties.getLicenseUrl());

        final Info info = new Info()
                .title(springDocProperties.getTitle())
                .description(springDocProperties.getDescription())
                .version(springDocProperties.getVersion())
                .license(license);

        return new OpenAPI()
                .info(info)
                .components(new Components()
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
