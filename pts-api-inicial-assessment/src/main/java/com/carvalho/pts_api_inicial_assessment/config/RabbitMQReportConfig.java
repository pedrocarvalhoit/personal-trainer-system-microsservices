package com.carvalho.pts_api_inicial_assessment.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQReportConfig {

    public static final String EXCHANGE = "inicial.assessment.report.exchange";
    public static final String ROUTING_KEY = "inicial.assessment.report.routingkey";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE);
    }

}
