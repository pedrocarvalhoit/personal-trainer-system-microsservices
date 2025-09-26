package com.carvalho.pts_api_strength_test.controller;

import com.carvalho.pts_api_strength_test.dto.*;
import com.carvalho.pts_api_strength_test.service.impl.StrengthTestServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/strength-test")
@RequiredArgsConstructor
public class StrengthTestController {

    private final StrengthTestServiceImpl service;

    @PostMapping("/create-for-athlete/{athleteId}")
    public ResponseEntity<StrengthTestResponseDto> create(@PathVariable String athleteId,
                                                             @RequestBody @Valid StrengthTestRequestDto request) {
        StrengthTestResponseDto savedTest = service.save(athleteId, request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTest.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedTest);
    }

    @GetMapping("/last-result-for-athlete/{athleteId}")
    public ResponseEntity<StrengthTestResponseDto> getLastResult(@PathVariable String athleteId){
        return ResponseEntity.ok(service.getLastTest(athleteId));
    }

    @GetMapping("/description")
    public ResponseEntity<TestDescriptionResponseDto> getDescription(){
        return ResponseEntity.ok(service.getDescription());
    }

    @GetMapping("/exercises")
    public ResponseEntity<ExercisesResponseDto> getExercises(){
        return ResponseEntity.ok(service.getAllExercieses());
    }

    @GetMapping("/exercises-result-for-athlete/{athleteId}")
    public ResponseEntity<List<ExercisesResultsResponseDto>> getResults(@PathVariable String athleteId){
        return ResponseEntity.ok(service.getExerciseResults(athleteId));
    }

}
