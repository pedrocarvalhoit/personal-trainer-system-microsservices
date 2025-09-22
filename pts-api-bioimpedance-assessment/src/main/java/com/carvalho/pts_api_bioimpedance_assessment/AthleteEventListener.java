package com.carvalho.pts_api_bioimpedance_assessment;

import com.carvalho.pts_api_bioimpedance_assessment.dto.UserAthleteCreatedEventDto;
import com.carvalho.pts_api_bioimpedance_assessment.service.impl.BioimpedanceAssessmentServiceImpl;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AthleteEventListener {

    private final BioimpedanceAssessmentServiceImpl service;

    public AthleteEventListener(BioimpedanceAssessmentServiceImpl service) {
        this.service = service;
    }

    @Transactional
    @RabbitListener(queues = "user.athlete.bioimpedance.queue", containerFactory = "rabbitListenerContainerFactory")
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

