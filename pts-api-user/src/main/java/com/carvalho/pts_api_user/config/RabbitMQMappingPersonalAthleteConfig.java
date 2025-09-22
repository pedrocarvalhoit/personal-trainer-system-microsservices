package com.carvalho.pts_api_user.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQMappingPersonalAthleteConfig {


    public static final String MAPPING_EXCHANGE = "mapping.personal.athlete.exchange";
    public static final String MAPPING_ROUTING_KEY = "mapping.personal.athlete.routingkey";

    @Bean
    public TopicExchange mappingExchange(){
        return new TopicExchange(MAPPING_EXCHANGE);
    }

}
