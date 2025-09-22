package com.carvalho.pts_api_athlete_report.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQPerimetryConfig {

    public static final String QUEUE = "perimetry.assessment.report.queue";
    public static final String EXCHANGE = "perimetry.assessment.report.exchange";
    public static final String ROUTING_KEY = "perimetry.assessment.report.routingkey";

    @Bean
    public Queue perimetryReportQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public DirectExchange perimetryReportExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding perimetryReportBinding(Queue perimetryReportQueue,
                                             DirectExchange perimetryReportExchange) {
        return BindingBuilder.bind(perimetryReportQueue)
                .to(perimetryReportExchange)
                .with(ROUTING_KEY);
    }

}
