package com.carvalho.pts_api_athlete_recomendations.config;

import com.carvalho.pts_api_athlete_recomendations.client.BioimpedanceAssessmentClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Value("${bioimpedance.assessment.url}")
    private String bioimpedanceAssessmentServiceUrl;

    @Bean
    public BioimpedanceAssessmentClient bioimpedanceAssessmentClient(){
        RestClient restClient = RestClient.builder()
                .baseUrl(bioimpedanceAssessmentServiceUrl)
                .requestFactory(getClientRequestFactory())//CB timeout config
                .build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(BioimpedanceAssessmentClient.class);
    }

    //Config for Circuit Breaker
    private ClientHttpRequestFactory getClientRequestFactory(){
        ClientHttpRequestFactorySettings clientHttpRequestFactorySettings = ClientHttpRequestFactorySettings.DEFAULTS
                .withConnectTimeout(Duration.ofSeconds(3))
                .withReadTimeout(Duration.ofSeconds(3));
        return ClientHttpRequestFactories.get(clientHttpRequestFactorySettings);
    }

}
