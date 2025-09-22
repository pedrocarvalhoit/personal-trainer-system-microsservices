package com.carvalho.pts_api_perimetry_assessment.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQReportConfig {

    public static final String EXCHANGE = "perimetry.assessment.report.exchange";
    public static final String ROUTING_KEY = "perimetry.assessment.report.routingkey";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE);
    }

}
