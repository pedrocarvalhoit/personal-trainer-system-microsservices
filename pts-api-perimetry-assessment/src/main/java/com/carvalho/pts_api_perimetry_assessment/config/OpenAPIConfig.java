package com.carvalho.pts_api_perimetry_assessment.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI bioimpedanceAssessmentServiceAPI(){
        return new OpenAPI()
                .info(new Info().title("Perimetry Assessment API")
                        .description("This is the REST API for Perimetry Assessment")
                        .version("v1")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Perimetry Assessmente Service Doc")
                        .url("http://perimetry-assessment/docs"));
    }

}
