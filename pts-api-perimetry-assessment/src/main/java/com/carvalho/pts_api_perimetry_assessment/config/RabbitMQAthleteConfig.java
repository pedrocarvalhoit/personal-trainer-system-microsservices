package com.carvalho.pts_api_perimetry_assessment.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQAthleteConfig {

    public static final String PERIMETRY_QUEUE = "user.athlete.perimetry.queue";
    public static final String ATHLETE_EXCHANGE = "user.athlete.exchange";
    public static final String ATHLETE_ROUTING_KEY = "user.athlete.created";

    /**
     * Atlhete Listener Config
     * */
    @Bean
    public Queue userAthleteCreatedPerimetryQueue() {
        return new Queue(PERIMETRY_QUEUE, true);
    }

    @Bean
    public TopicExchange userAthletePerimetryExchange() {
        return new TopicExchange(ATHLETE_EXCHANGE);
    }

    @Bean
    public Binding userAthleteCreatedBinding(Queue userAthleteCreatedPerimetryQueue, TopicExchange userAthletePerimetryExchange) {
        return BindingBuilder.bind(userAthleteCreatedPerimetryQueue)
                .to(userAthletePerimetryExchange)
                .with(ATHLETE_ROUTING_KEY);
    }


}
