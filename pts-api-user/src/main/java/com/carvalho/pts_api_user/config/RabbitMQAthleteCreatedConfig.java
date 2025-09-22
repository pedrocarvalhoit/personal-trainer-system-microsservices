package com.carvalho.pts_api_user.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQAthleteCreatedConfig {

    public static final String QUEUE = "user.registration";
    public static final String EXCHANGE = "user.exchange";
    public static final String ROUTING_KEY = "user.created";

    public static final String ATHLETE_EXCHANGE = "user.athlete.exchange";
    public static final String ATHLETE_ROUTING_KEY = "user.athlete.created";


    /**
     * User Listener Config
     * */

    @Bean
    public Queue userCreatedQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding userCreatedBinding(Queue userCreatedQueue, DirectExchange userExchange) {
        return BindingBuilder.bind(userCreatedQueue).to(userExchange).with(ROUTING_KEY);
    }

    /**
     * User Publisher for create Athlete
     * */

    @Bean
    public TopicExchange userAthleteExchange() {
        return new TopicExchange(ATHLETE_EXCHANGE);
    }

}
