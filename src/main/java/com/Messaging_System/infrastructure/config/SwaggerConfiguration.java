package com.Messaging_System.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.webmvc.ui.SwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI config(){
        return new OpenAPI()
                .info(new Info()
                        .title("Messaging system with websocket")
                        .description("A messaging system (discord-like) made by Henrique Silveira Soares")
                        .version("1.0.0")

                ).addSecurityItem(new SecurityRequirement().addList("Bearer"))
                .components(new Components().addSecuritySchemes("Bearer",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Bearer token. You can get one when you register or log in.")
                ))
                .addServersItem(new Server()
                        .description("overral api requisitions")
                        .url("http://" + AllowedEndpoints.URL_FOR_SWAGGER)
                )
                .addServersItem(new Server()
                        .url("ws://" + AllowedEndpoints.URL_FOR_SWAGGER)
                        .description("Websocket server")
                )
                ;
    }

}
