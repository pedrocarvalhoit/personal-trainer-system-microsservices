package com.carvalho.pts_api_workout_session.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQAthleteConfig {

    public static final String SESSION_QUEUE = "user.athlete.session.queue";
    public static final String ATHLETE_EXCHANGE = "user.athlete.exchange";
    public static final String ATHLETE_ROUTING_KEY = "user.athlete.created";

    /**
     * Atlhete Listener Config
     * */
    @Bean
    public Queue userAthleteCreatedSessionQueue() {
        return new Queue(SESSION_QUEUE, true);
    }

    @Bean
    public TopicExchange userAthleteExchange() {
        return new TopicExchange(ATHLETE_EXCHANGE);
    }

    @Bean
    public Binding athleteCreatedBinding(Queue userAthleteCreatedSessionQueue, TopicExchange userAthleteExchange) {
        return BindingBuilder
                .bind(userAthleteCreatedSessionQueue).to(userAthleteExchange).with(ATHLETE_ROUTING_KEY);
    }


}
