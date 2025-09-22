package com.carvalho.pts_api_athlete_recomendations;

import com.carvalho.pts_api_athlete_recomendations.dto.BioimpedanceAssessmentCreatedEventDto;
import com.carvalho.pts_api_athlete_recomendations.service.impl.BioimpedanceRecommendationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BioimpedanceAssessmentEventListener {

    private final BioimpedanceRecommendationServiceImpl recommendationService;

    public BioimpedanceAssessmentEventListener(BioimpedanceRecommendationServiceImpl recommendationService) {
        this.recommendationService = recommendationService;
    }

    @Transactional
    @RabbitListener(queues = "bioimpedance.assessment.created", containerFactory = "rabbitListenerContainerFactory")
    public void handleBioimpedanceEvent(BioimpedanceAssessmentCreatedEventDto event) {
        try {
            log.info("Received bioimpedance event for assessment ID: {}", event.getBioimpedanceId());
            recommendationService.saveFromEvent(event);
            log.info("Successfully processed bioimpedance event for assessment ID: {}", event.getBioimpedanceId());
        } catch (Exception e) {
            log.error("Error processing bioimpedance event for assessment ID: {}. Error: {}",
                    event.getBioimpedanceId(), e.getMessage(), e);
            throw e; // Isso fará com que a mensagem seja enviada para DLQ se configurado
        }
    }
}
