package com.carvalho.pts_api_perimetry_assessment.service.impl;

import com.carvalho.pts_api_perimetry_assessment.config.RabbitMQReportConfig;
import com.carvalho.pts_api_perimetry_assessment.dto.PerimetryAssessmentToAthleteReportDto;
import com.carvalho.pts_api_perimetry_assessment.service.PerimetryAssessmentEventPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PerimetryAssessmentEventPublisherImpl implements PerimetryAssessmentEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public PerimetryAssessmentEventPublisherImpl(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publishToAthleteReport(PerimetryAssessmentToAthleteReportDto event) {
        try{
            rabbitTemplate.convertAndSend(
                    RabbitMQReportConfig.EXCHANGE,
                    RabbitMQReportConfig.ROUTING_KEY,
                    event,
                    message -> {message.getMessageProperties().setContentType("application/json");
                        return message;
                    });
            log.info("Event published successfully: {}", event);
        }catch (Exception e){
            log.error("Failed to publish event: {}", event, e);
            throw new RuntimeException("Error on publish bioimpedance event", e);
        }
    }
}
