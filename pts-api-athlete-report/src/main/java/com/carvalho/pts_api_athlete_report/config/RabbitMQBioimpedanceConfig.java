package com.carvalho.pts_api_athlete_report.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQBioimpedanceConfig {

    public static final String QUEUE = "bioimpedance.assessment.report.queue";
    public static final String EXCHANGE = "bioimpedance.assessment.report.exchange";
    public static final String ROUTING_KEY = "bioimpedance.assessment.report.routingkey";

    @Bean
    public Queue bioimpedanceReportQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public DirectExchange bioimpedanceReportExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding bioimpedanceReportBinding(Queue bioimpedanceReportQueue,
                                             DirectExchange bioimpedanceReportExchange) {
        return BindingBuilder.bind(bioimpedanceReportQueue)
                .to(bioimpedanceReportExchange)
                .with(ROUTING_KEY);
    }

}
