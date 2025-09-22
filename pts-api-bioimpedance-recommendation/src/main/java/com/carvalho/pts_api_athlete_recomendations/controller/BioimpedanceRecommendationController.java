package com.carvalho.pts_api_athlete_recomendations.controller;

import com.carvalho.pts_api_athlete_recomendations.dto.BioimpedanceRecommendationDto;
import com.carvalho.pts_api_athlete_recomendations.service.impl.BioimpedanceRecommendationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bioimpedance-recommendation")
@Validated
public class BioimpedanceRecommendationController {

    private final BioimpedanceRecommendationServiceImpl service;

    public BioimpedanceRecommendationController(BioimpedanceRecommendationServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/last-for-athlete/{athleteId}")
    public ResponseEntity<BioimpedanceRecommendationDto> listLastForAthlete (@PathVariable String athleteId) {
        BioimpedanceRecommendationDto recommendations = service.listLastByAthlete(athleteId);
        return ResponseEntity.ok(recommendations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BioimpedanceRecommendationDto> searchById(@PathVariable Long id){
        BioimpedanceRecommendationDto bioimpedanceRecommendationDto = service.searchById(id);
        return ResponseEntity.ok(bioimpedanceRecommendationDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BioimpedanceRecommendationDto> update(@PathVariable Long id, @RequestBody BioimpedanceRecommendationDto dto){
        BioimpedanceRecommendationDto updatedRecommendation = service.update(id, dto);
        return ResponseEntity.ok(updatedRecommendation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
