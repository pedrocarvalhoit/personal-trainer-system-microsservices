package com.carvalho.pts_api_cardio_test.controller;

import com.carvalho.pts_api_cardio_test.dto.CooperTestHistoryResponseDto;
import com.carvalho.pts_api_cardio_test.dto.CooperTestRequestDto;
import com.carvalho.pts_api_cardio_test.dto.CooperTestDto;
import com.carvalho.pts_api_cardio_test.dto.TestDescriptionResponseDto;
import com.carvalho.pts_api_cardio_test.service.impl.CooperTestServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cooper-test")
public class CooperTestController {

    private final CooperTestServiceImpl service;

    @PostMapping("/create-for-athlete/{athleteId}")
    public ResponseEntity<CooperTestDto> save(@PathVariable String athleteId,
                                              @RequestBody @Valid CooperTestRequestDto requestDto){
        CooperTestDto savedCooperTest = service.saveCooperTest(athleteId, requestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCooperTest.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedCooperTest);
    }

    @GetMapping("/last-result-for-athlete/{athleteId}")
    public ResponseEntity<CooperTestDto> getLastResult(@PathVariable String athleteId){
        return ResponseEntity.ok(service.getLastVo2Max(athleteId));
    }

    @GetMapping("/description")
    public ResponseEntity<TestDescriptionResponseDto> getDescription(){
        return ResponseEntity.ok(service.getDescription());
    }

    @GetMapping("/twelve-months-history/{athleteId}")
    public ResponseEntity<List<CooperTestHistoryResponseDto>> getHistoricResults(@PathVariable String athleteId){
        return ResponseEntity.ok(service.getHistoryResults(athleteId));
    }

    @DeleteMapping("/delete/{testId}")
    public ResponseEntity<Long> deleteTest(@PathVariable Long testId){
        return ResponseEntity.ok(service.delete(testId));
    }


}
