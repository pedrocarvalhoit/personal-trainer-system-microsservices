package com.carvalho.pts_api_user.service.impl;

import com.carvalho.pts_api_user.config.RabbitMQAthleteCreatedConfig;
import com.carvalho.pts_api_user.config.RabbitMQMappingPersonalAthleteConfig;
import com.carvalho.pts_api_user.dto.PersonalTrainerAndAthleteMappingDto;
import com.carvalho.pts_api_user.dto.UserAthleteCreatedEventDto;
import com.carvalho.pts_api_user.service.UserAthleteEventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserAthleteEventPublisherImpl implements UserAthleteEventPublisher {

    public final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;


    public UserAthleteEventPublisherImpl(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    // Publish ID for assessments
    @Override
    public void publish(UserAthleteCreatedEventDto event) throws JsonProcessingException {
        try{
            rabbitTemplate.convertAndSend(
                    RabbitMQAthleteCreatedConfig.ATHLETE_EXCHANGE,
                    RabbitMQAthleteCreatedConfig.ATHLETE_ROUTING_KEY,
                    event, message -> {message.getMessageProperties().setContentType("application/json");
                    return message;
                    });
            log.info("Event published successfully: {}", event);
        }catch (Exception e){
            log.error("Failed to publish event: {}", event, e);
            throw new RuntimeException("Error on publish athlete event", e);
        }
    }

    // Publish Mapping for Workout Session
    @Override
    public void publishToPersonalAthleteMapping(PersonalTrainerAndAthleteMappingDto event) throws JsonProcessingException {
        try{
            log.info("Publishing mapping event: {}", event);
            rabbitTemplate.convertAndSend(
                    RabbitMQMappingPersonalAthleteConfig.MAPPING_EXCHANGE,
                    RabbitMQMappingPersonalAthleteConfig.MAPPING_ROUTING_KEY,
                    event, message -> {message.getMessageProperties().setContentType("application/json");
                    return message;
                    });
            log.info("Mapping event published successfully");
        }catch (Exception e){
            log.error("Failed to publish event: {}", event, e);
            throw new RuntimeException("Error on publish athlete event", e);
        }
    }
}
