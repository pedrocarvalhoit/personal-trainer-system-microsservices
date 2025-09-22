package com.carvalho.pts_api_athlete_report.listener;

import com.carvalho.pts_api_athlete_report.dto.InicialAssessmentToReportDto;
import com.carvalho.pts_api_athlete_report.service.assessment.InicialAssessmentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InicialAssessmentEventListener {

    private final InicialAssessmentService service;

    public InicialAssessmentEventListener(InicialAssessmentService service) {
        this.service = service;
    }

    @Transactional
    @RabbitListener(queues = "inicial.assessment.report.queue", containerFactory = "rabbitListenerContainerFactory")
    public void handleAthleteCreated(InicialAssessmentToReportDto event){
        try {
            log.info("Received event for inicial assessment ID: {}", event.getInicialAssessmentId());
            service.saveInicialAssessmentFromEvent(event);
            log.info("Successfully processed inicial assessment event for inicial assessment ID: {}", event.getInicialAssessmentId());
        } catch (Exception e) {
            log.error("Error processing inicial assessment event for inicial assessment ID: {}. Error: {}",
                    event.getInicialAssessmentId(), e.getMessage(), e);
            throw e; // Isso fará com que a mensagem seja enviada para DLQ se configurado
        }
    }
}
