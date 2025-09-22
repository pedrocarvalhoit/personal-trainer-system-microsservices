package com.carvalho.pts_api_inicial_assessment.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI inicialAssessmentServiceAPI(){
        return new OpenAPI()
                .info(new Info().title("Inicial Assessment API")
                        .description("This is the REST API for Inicial Assessment")
                        .version("v1")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Inicial Assessmente Service Doc")
                        .url("http://inicial-assessment/docs"));
    }

}
