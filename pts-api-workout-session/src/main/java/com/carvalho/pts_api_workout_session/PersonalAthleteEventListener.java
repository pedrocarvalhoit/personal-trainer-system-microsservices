package com.carvalho.pts_api_workout_session;

import com.carvalho.pts_api_workout_session.dto.PersonalTrainerAndAthleteMappingDto;
import com.carvalho.pts_api_workout_session.service.impl.PersonalAthleteMappingServiceImpl;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonalAthleteEventListener {

    private final PersonalAthleteMappingServiceImpl service;

    public PersonalAthleteEventListener(PersonalAthleteMappingServiceImpl service) {
        this.service = service;
    }

    @Transactional
    @RabbitListener(queues = "mapping.personal.athlete.queue", containerFactory = "rabbitListenerContainerFactory")
    public void handlePersonalAthleteMapping(PersonalTrainerAndAthleteMappingDto event){
        try{
            log.info("Received mapping event for personal ID: {} and athlete ID: {}", event.getPersonalId(), event.getAthleteId());
            service.saveMappingPersonalAthlete(event);
            log.info("Successfully processed mapping event for personal ID: {} and athlete ID: {}", event.getPersonalId(), event.getAthleteId());
        }catch(Exception e){
            log.error("Error processing mapping event for personal ID: {} and athlete ID: {}. Error: {}", event.getPersonalId(),
                    event.getAthleteId(), e.getMessage(), e);
            throw e; // Isso fará com que a mensagem seja enviada para DLQ se configurado
        }
    }
}
