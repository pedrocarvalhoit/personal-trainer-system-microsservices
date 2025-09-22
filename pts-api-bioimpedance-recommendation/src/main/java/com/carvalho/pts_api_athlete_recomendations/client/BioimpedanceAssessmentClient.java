package com.carvalho.pts_api_athlete_recomendations.client;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

@Slf4j
public interface BioimpedanceAssessmentClient {

    Logger log = LoggerFactory.getLogger(BioimpedanceAssessmentClient.class);

    @GetExchange("/body-weight/{bioimpedanceId}")
    @CircuitBreaker(name = "bioimpedanceAssessment", fallbackMethod = "fallbackMethod")
    @Retry(name = "bioimpedanceAssessment")
    Double bodyWeight(@PathVariable("bioimpedanceId") Long id);

    @GetExchange("/ideal-weight/{bioimpedanceId}")
    @CircuitBreaker(name = "bioimpedanceAssessment", fallbackMethod = "fallbackMethod")
    @Retry(name = "bioimpedanceAssessment")
    Double idealWeight(@PathVariable("bioimpedanceId") Long id);

    @GetExchange("/basal-metabolism/{bioimpedanceId}")
    @CircuitBreaker(name = "bioimpedanceAssessment", fallbackMethod = "fallbackMethod")
    @Retry(name = "bioimpedanceAssessment")
    Double basalMetabolism(@PathVariable("bioimpedanceId") Long id);


    default Double fallbackMethod (Long id, Throwable throwable){
        log.info("Cannot get Bioimpedance Assessment with ID {}, failure reason: {}", id, throwable.getMessage());
        throw new RuntimeException("Assessment Service is not available");
    }
}
