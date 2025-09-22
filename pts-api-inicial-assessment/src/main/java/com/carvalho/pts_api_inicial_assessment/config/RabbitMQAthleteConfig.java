package com.carvalho.pts_api_inicial_assessment.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQAthleteConfig {

    public static final String INICIAL_QUEUE = "user.athlete.inicial.queue";
    public static final String ATHLETE_EXCHANGE = "user.athlete.exchange";
    public static final String ATHLETE_ROUTING_KEY = "user.athlete.created";

    /**
     * Atlhete Listener Config
     * */
    @Bean
    public Queue userAthleteCreatedInicialQueue() {
        return new Queue(INICIAL_QUEUE, true);
    }

    @Bean
    public TopicExchange userAthleteExchange() {
        return new TopicExchange(ATHLETE_EXCHANGE);
    }

    @Bean
    public Binding athleteCreatedBinding(Queue userAthleteCreatedInicialQueue, TopicExchange userAthleteExchange) {
        return BindingBuilder
                .bind(userAthleteCreatedInicialQueue).to(userAthleteExchange).with(ATHLETE_ROUTING_KEY);
    }


}
