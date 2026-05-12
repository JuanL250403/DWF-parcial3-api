package sv.edu.udb.parcial3.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configuracionSwagger() {
        return new OpenAPI()
                .info(new Info()
                        .title("API para el registro de eventos y reservas")
                        .version("1.0")
                        .description("Documentacion acerca de API para la gestion de eventos y el registro de reservas de los mismos")
                )
                .components(new Components()
                        .addSecuritySchemes("Auth",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addServersItem(new Server().url("http://localhost:8080").description("Server local"));
    }
}
