package com.carvalho.pts_api_bioimpedance_assessment.service.impl;

import com.carvalho.pts_api_bioimpedance_assessment.config.RabbitMQRecommendationConfig;
import com.carvalho.pts_api_bioimpedance_assessment.config.RabbitMQReportConfig;
import com.carvalho.pts_api_bioimpedance_assessment.dto.BioimpedanceAssessmentCreatedEventDto;
import com.carvalho.pts_api_bioimpedance_assessment.dto.BioimpedanceToAthleteReportDto;
import com.carvalho.pts_api_bioimpedance_assessment.service.BioimpedanceAssessmentEventPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class BioimpedanceAssessmentEventPublisherImpl implements BioimpedanceAssessmentEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public BioimpedanceAssessmentEventPublisherImpl(RabbitTemplate rabbitTemplate, ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = mapper;
    }

    @Override
    public void publish(BioimpedanceAssessmentCreatedEventDto event){
        try{
            rabbitTemplate.convertAndSend(
                    RabbitMQRecommendationConfig.EXCHANGE,
                    RabbitMQRecommendationConfig.ROUTING_KEY,
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

    @Override
    public void publishToAthleteReport(BioimpedanceToAthleteReportDto event) {
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
