package com.carvalho.pts_api_bioimpedance_assessment.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQReportConfig {

    public static final String EXCHANGE = "bioimpedance.assessment.report.exchange";
    public static final String ROUTING_KEY = "bioimpedance.assessment.report.routingkey";

    /**
     * Publisher for Athlete Report
     * */

    @Bean
    public DirectExchange reportExchange() {
        return new DirectExchange(EXCHANGE);
    }

}
