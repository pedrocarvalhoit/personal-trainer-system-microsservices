package com.carvalho.pts_api_athlete_recomendations.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bioimpedanceAssessmentServiceAPI(){
        return new OpenAPI()
                .info(new Info().title("Bioimpedance Recommendation API")
                        .description("This is the REST API for Bioimpedance Recommendation")
                        .version("v1")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Bioimpedance Recommendation Service Doc")
                        .url("http://bioimpedance-recommendation/docs"));
    }

}
