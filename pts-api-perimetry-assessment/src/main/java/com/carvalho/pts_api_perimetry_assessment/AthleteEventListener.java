package com.carvalho.pts_api_perimetry_assessment;

import com.carvalho.pts_api_perimetry_assessment.dto.UserAthleteCreatedEventDto;
import com.carvalho.pts_api_perimetry_assessment.service.impl.PerimetryAssessmentServiceImpl;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AthleteEventListener {

    private final PerimetryAssessmentServiceImpl service;

    public AthleteEventListener(PerimetryAssessmentServiceImpl service) {
        this.service = service;
    }

    @Transactional
    @RabbitListener(queues = "user.athlete.perimetry.queue", containerFactory = "rabbitListenerContainerFactory")
    public void handleAthleteCreated(UserAthleteCreatedEventDto event){
        try {
            log.info("Received athlete event for athlete ID: {}", event.getAthleteId());
            service.saveAthleteFromEvent(event);
            log.info("Successfully processed athlete event for athlete ID: {}", event.getAthleteId());
        } catch (Exception e) {
            log.error("Error processing athlete event for athlete ID: {}. Error: {}",
                    event.getAthleteId(), e.getMessage(), e);
            throw e; // Isso fará com que a mensagem seja enviada para DLQ se configurado
        }
    }

}

