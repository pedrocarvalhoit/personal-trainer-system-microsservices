package com.carvalho.pts_api_athlete_report.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQInicialConfig {

    public static final String QUEUE = "inicial.assessment.report.queue";
    public static final String EXCHANGE = "inicial.assessment.report.exchange";
    public static final String ROUTING_KEY = "inicial.assessment.report.routingkey";

    @Bean
    public Queue inicialReportQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public DirectExchange inicialReportExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding inicialReportBinding(Queue inicialReportQueue,
                                             DirectExchange inicialReportExchange) {
        return BindingBuilder.bind(inicialReportQueue)
                .to(inicialReportExchange)
                .with(ROUTING_KEY);
    }

}
