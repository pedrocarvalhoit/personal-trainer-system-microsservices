package com.carvalho.pts_api_bioimpedance_assessment.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQRecommendationConfig {

    public static final String EXCHANGE = "bioimpedance.exchange";
    public static final String ROUTING_KEY = "bioimpedance.created";

    /**
     * Publisher for bioimpedance recommendation Config
     * */

    @Bean
    public DirectExchange recommendationExchange(){
        return new DirectExchange(EXCHANGE);
    }




}
