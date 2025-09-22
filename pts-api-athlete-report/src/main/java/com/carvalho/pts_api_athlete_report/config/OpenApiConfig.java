package com.carvalho.pts_api_athlete_report.config;

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
                .info(new Info().title("Athlete Report API")
                        .description("This is the REST API for Athlete Report API")
                        .version("v1")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Athlete Report Service Doc")
                        .url("http://athlete-report/docs"));
    }

}
