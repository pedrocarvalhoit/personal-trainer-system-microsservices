package com.carvalho.pts_api_athlete_report.listener;

import com.carvalho.pts_api_athlete_report.dto.BioimpedanceToAthleteReportDto;
import com.carvalho.pts_api_athlete_report.service.assessment.BioimpedanceAssessmentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BioimpedanceAssessmentEventListener {

    private final BioimpedanceAssessmentService service;

    public BioimpedanceAssessmentEventListener(BioimpedanceAssessmentService service) {
        this.service = service;
    }

    @Transactional
    @RabbitListener(queues = "bioimpedance.assessment.report.queue", containerFactory = "rabbitListenerContainerFactory")
    public void handleAthleteCreated(BioimpedanceToAthleteReportDto event){
        try {
            log.info("Received event for bioimpedance ID: {}", event.getBioimpedanceId());
            service.saveBioimpedanceAssessmentFromEvent(event);
            log.info("Successfully processed bioimpedance assessment event for bioimpedance assessment ID: {}", event.getBioimpedanceId());
        } catch (Exception e) {
            log.error("Error processing bioimpedance assessment event for bioimpedance assessment ID: {}. Error: {}",
                    event.getBioimpedanceId(), e.getMessage(), e);
            throw e; // Isso fará com que a mensagem seja enviada para DLQ se configurado
        }
    }
}
