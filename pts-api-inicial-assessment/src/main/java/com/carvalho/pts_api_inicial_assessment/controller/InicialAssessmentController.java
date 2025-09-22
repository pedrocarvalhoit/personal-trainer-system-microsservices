package com.carvalho.pts_api_inicial_assessment.controller;

import com.carvalho.pts_api_inicial_assessment.dto.InicialAssessmentDto;
import com.carvalho.pts_api_inicial_assessment.dto.InicialAssessmentResponse;
import com.carvalho.pts_api_inicial_assessment.service.impl.InicialAssessmentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/inicial-assessment")
@Validated
public class InicialAssessmentController {

    private final InicialAssessmentServiceImpl service;

    public InicialAssessmentController(InicialAssessmentServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create/{athleteId}")
    public ResponseEntity<InicialAssessmentDto> save(@PathVariable String athleteId,
                                                    @RequestBody @Valid InicialAssessmentDto inicialAssessment){
        InicialAssessmentDto savedInicialAssessment = service.saveInicialAssessment(athleteId, inicialAssessment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedInicialAssessment.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedInicialAssessment);
    }

    @GetMapping("/all-for-athlete/{athleteId}")
    public ResponseEntity<List<InicialAssessmentResponse>> listAllByAthleteId(@PathVariable String athleteId){
        List<InicialAssessmentResponse> assessments = service.listAllByAthleteId(athleteId);
        return ResponseEntity.ok(assessments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InicialAssessmentDto> update(@PathVariable Long id, @RequestBody InicialAssessmentDto dto){
        InicialAssessmentDto updatedAssessment = service.update(id, dto);
        return ResponseEntity.ok(updatedAssessment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
