package com.carvalho.pts_api_athlete_report.listener;

import com.carvalho.pts_api_athlete_report.dto.PerimetryAssessmentToReportDto;
import com.carvalho.pts_api_athlete_report.service.assessment.PerimetryAssessmentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PerimetryAssessmentEventListener {

    private final PerimetryAssessmentService service;

    public PerimetryAssessmentEventListener(PerimetryAssessmentService service) {
        this.service = service;
    }

    @Transactional
    @RabbitListener(queues = "perimetry.assessment.report.queue", containerFactory = "rabbitListenerContainerFactory")
    public void handleAthleteCreated(PerimetryAssessmentToReportDto event){
        try {
            log.info("Received event for perimetry assessment ID: {}", event.getPerimetryAssessmentId());
            service.savePerimetryAssessmentFromEvent(event);
            log.info("Successfully processed perimetry assessment event for perimetry assessment ID: {}", event.getPerimetryAssessmentId());
        } catch (Exception e) {
            log.error("Error processing perimetry assessment event for perimetry assessment ID: {}. Error: {}",
                    event.getPerimetryAssessmentId(), e.getMessage(), e);
            throw e; // Isso fará com que a mensagem seja enviada para DLQ se configurado
        }
    }
}
