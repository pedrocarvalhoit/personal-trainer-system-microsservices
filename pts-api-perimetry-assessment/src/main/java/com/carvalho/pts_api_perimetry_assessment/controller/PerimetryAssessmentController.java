package com.carvalho.pts_api_perimetry_assessment.controller;

import jakarta.validation.Valid;

import com.carvalho.pts_api_perimetry_assessment.dto.PerimetryAssessmentDto;
import com.carvalho.pts_api_perimetry_assessment.service.impl.PerimetryAssessmentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping()
@Validated
public class PerimetryAssessmentController {

    private final PerimetryAssessmentServiceImpl service;


    public PerimetryAssessmentController(PerimetryAssessmentServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create/{athleteId}")
    public ResponseEntity<PerimetryAssessmentDto> save(@PathVariable String athleteId,
                                                       @RequestBody @Valid PerimetryAssessmentDto perimetryAssessment){
        PerimetryAssessmentDto savedPerimetryAssessment = service.savePerimetryAssessment(athleteId, perimetryAssessment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPerimetryAssessment.getId())
                .toUri();
        return ResponseEntity.created(location).body(perimetryAssessment);
    }

    @GetMapping("/all-for-athlete/{athleteId}")
    public ResponseEntity<List<PerimetryAssessmentDto>> findAllByAthleteId (@PathVariable String athleteId){
        List<PerimetryAssessmentDto> assessments = service.listAllByAthleteId(athleteId);
        return ResponseEntity.ok(assessments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerimetryAssessmentDto> searchById(@PathVariable Long id){
        PerimetryAssessmentDto inicialAssessmentDto = service.searchById(id);
        return ResponseEntity.ok(inicialAssessmentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerimetryAssessmentDto> update(@PathVariable Long id, @RequestBody PerimetryAssessmentDto dto){
        PerimetryAssessmentDto updatedAssessment = service.update(id, dto);
        return ResponseEntity.ok(updatedAssessment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
