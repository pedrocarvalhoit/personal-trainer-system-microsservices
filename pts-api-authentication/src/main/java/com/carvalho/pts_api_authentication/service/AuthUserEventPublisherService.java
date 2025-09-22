package com.carvalho.pts_api_authentication.service;

import com.carvalho.pts_api_authentication.config.RabbitMQConfig;
import com.carvalho.pts_api_authentication.dto.UserCreatedEventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AuthUserEventPublisherService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public AuthUserEventPublisherService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void publish(UserCreatedEventDto event){
        try{
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE,
                    RabbitMQConfig.ROUTING_KEY,
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
