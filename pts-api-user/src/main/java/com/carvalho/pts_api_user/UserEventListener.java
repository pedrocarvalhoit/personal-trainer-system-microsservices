package com.carvalho.pts_api_user;

import com.carvalho.pts_api_user.dto.PersonalTrainerAndAthleteMappingDto;
import com.carvalho.pts_api_user.dto.UserAthleteCreatedEventDto;
import com.carvalho.pts_api_user.dto.UserCreatedEventDto;
import com.carvalho.pts_api_user.service.impl.UserAthleteEventPublisherImpl;
import com.carvalho.pts_api_user.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserEventListener {

    private final UserServiceImpl service;
    private final UserAthleteEventPublisherImpl publisher;

    public UserEventListener(UserServiceImpl service, UserAthleteEventPublisherImpl publisher) {
        this.service = service;
        this.publisher = publisher;
    }

    @Transactional
    @RabbitListener(queues = "user.registration", containerFactory = "rabbitListenerContainerFactory")
    public void handleUserCreated(UserCreatedEventDto user) throws JsonProcessingException {
        try{
            log.info("User created with Id: {}", user.getId());
            service.saveUserFromEvent(user);
            log.info("Successfully processed user event for user ID: {}", user.getId());

        }catch (Exception e){
            log.error("Error processing user event for athlete ID: {}. Error: {}", user.getId(), e.getMessage());
            throw e;
        }
    }
}
