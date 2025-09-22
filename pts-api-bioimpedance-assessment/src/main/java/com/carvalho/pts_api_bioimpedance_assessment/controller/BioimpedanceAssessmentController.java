package com.carvalho.pts_api_bioimpedance_assessment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import com.carvalho.pts_api_bioimpedance_assessment.dto.BioimpedanceAssessmentDto;
import com.carvalho.pts_api_bioimpedance_assessment.service.impl.BioimpedanceAssessmentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping()
@Validated
public class BioimpedanceAssessmentController {

    private final BioimpedanceAssessmentServiceImpl service;

    public BioimpedanceAssessmentController(BioimpedanceAssessmentServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create/{athleteId}")
    public ResponseEntity<BioimpedanceAssessmentDto> save(@PathVariable("athleteId") String athleteId,
                                                          @RequestBody @Valid BioimpedanceAssessmentDto bioimpedanceAssessment) throws JsonProcessingException {
        BioimpedanceAssessmentDto savedBioimpedanceAssessment = service.saveBioimpedanceAssessment(athleteId, bioimpedanceAssessment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBioimpedanceAssessment.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedBioimpedanceAssessment);
    }

    @GetMapping("/all-for-athlete/{athleteId}")
    public ResponseEntity<List<BioimpedanceAssessmentDto>> listAllForAthlete (@PathVariable String athleteId) {
        List<BioimpedanceAssessmentDto> assessments = service.listAllByAthleteId(athleteId);
        return ResponseEntity.ok(assessments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BioimpedanceAssessmentDto> update(@PathVariable Long id, @RequestBody BioimpedanceAssessmentDto dto){
        BioimpedanceAssessmentDto updatedAssessment = service.update(id, dto);
        return ResponseEntity.ok(updatedAssessment);
    }

    @GetMapping("/body-weight/{bioimpedanceId}")
    public Double getClientWeight(@PathVariable Long bioimpedanceId){
        return service.getBioimpendanceWeight(bioimpedanceId);
    }

    @GetMapping("/ideal-weight/{bioimpedanceId}")
    public Double getClientIdealWeight(@PathVariable Long bioimpedanceId){
        return service.getBioimpendanceIdealWeight(bioimpedanceId);
    }

    @GetMapping("/basal-metabolism/{bioimpedanceId}")
    public Double getClientBasalMetabolism(@PathVariable Long bioimpedanceId){
        return service.getBioimpendanceBasalMetabolism(bioimpedanceId);
    }

}
