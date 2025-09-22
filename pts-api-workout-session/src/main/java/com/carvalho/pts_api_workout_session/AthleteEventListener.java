package com.carvalho.pts_api_workout_session;

import com.carvalho.pts_api_workout_session.dto.UserAthleteCreatedEventDto;
import com.carvalho.pts_api_workout_session.service.impl.WorkoutSessionServiceImpl;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AthleteEventListener {

    private final WorkoutSessionServiceImpl service;

    public AthleteEventListener(WorkoutSessionServiceImpl service) {
        this.service = service;
    }

    @Transactional
    @RabbitListener(queues = "user.athlete.session.queue", containerFactory = "rabbitListenerContainerFactory")
    public void handleAthleteCreated(UserAthleteCreatedEventDto event){
        try {
            log.info("Received athlete event for athlete Name: {}", event.getFullName());
            service.saveAthleteFromEvent(event);
            log.info("Successfully processed athlete event for athlete Name: {}", event.getFullName());
        } catch (Exception e) {
            log.error("Error processing athlete event for athlete Name: {}. Error: {}",
                    event.getFullName(), e.getMessage(), e);
            throw e; // Isso fará com que a mensagem seja enviada para DLQ se configurado
        }
    }
}
