package com.carvalho.pts_api_workout_session.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQMappingPersonalAthleteConfig {

    public static final String MAPPING_QUEUE = "mapping.personal.athlete.queue";
    public static final String MAPPING_EXCHANGE = "mapping.personal.athlete.exchange";
    public static final String MAPPING_ROUTING_KEY = "mapping.personal.athlete.routingkey";

    @Bean
    public Queue mappingPersonalAthleteQueue() {
        return new Queue(MAPPING_QUEUE, true);
    }

    @Bean
    public TopicExchange mappingExchange(){
        return new TopicExchange(MAPPING_EXCHANGE);
    }

    @Bean
    public Binding mappingPersonalAthleteBinding(Queue mappingPersonalAthleteQueue, TopicExchange mappingExchange){
        return BindingBuilder.bind(mappingPersonalAthleteQueue).to(mappingExchange).with(MAPPING_ROUTING_KEY);
    }

}
