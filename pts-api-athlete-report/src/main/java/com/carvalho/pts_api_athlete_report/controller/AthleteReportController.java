package com.carvalho.pts_api_athlete_report.controller;

import com.carvalho.pts_api_athlete_report.dto.AthleteReportDto;
import com.carvalho.pts_api_athlete_report.entity.AthleteReportEntity;
import com.carvalho.pts_api_athlete_report.service.impl.AthleteReportServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/athlete-report")
@Validated
public class AthleteReportController {

    private final AthleteReportServiceImpl service;

    public AthleteReportController(AthleteReportServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/generate-report/{athleteId}")
    public ResponseEntity<AthleteReportEntity> generateReport(@PathVariable String athleteId){
        AthleteReportEntity savedReport = service.generate(athleteId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedReport.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedReport);
    }

    @GetMapping("/last-for-athlete/{athleteId}")
    public ResponseEntity<AthleteReportDto> getLastForAthlete(@PathVariable String athleteId){
        AthleteReportDto dto = service.getLastByAthlete(athleteId);
        return ResponseEntity.ok(dto);
    }


}
