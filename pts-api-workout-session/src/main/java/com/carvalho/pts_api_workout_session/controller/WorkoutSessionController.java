package com.carvalho.pts_api_workout_session.controller;

import com.carvalho.pts_api_workout_session.dto.*;
import com.carvalho.pts_api_workout_session.service.impl.WorkoutSessionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("workout-session")
public class WorkoutSessionController {

    private final WorkoutSessionServiceImpl service;

    //create to client
    @PostMapping("/create/{athleteId}")
    public ResponseEntity<WorkoutSessionDto> createSession(@RequestBody @Valid WorkoutSessionDto dto,
                                                 @PathVariable String athleteId){
        WorkoutSessionDto savedWorkoutSession = service.save(dto, athleteId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedWorkoutSession.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedWorkoutSession);
    }

    //delete Workout Session
    @DeleteMapping("/delete/{sessionId}")
    public ResponseEntity<Long> deleteSession(@PathVariable Long sessionId){
        service.delete(sessionId);
        return ResponseEntity.noContent().build();
    }

    //execute session
    @PatchMapping("/execute/{sessionId}")
    public ResponseEntity<Long> executeSession(@PathVariable @Valid Long sessionId){
        return ResponseEntity.ok(service.execute(sessionId));
    }

    //update Efforts
    @PatchMapping("/update/{sessionId}")
    public ResponseEntity<Long> update(
            @PathVariable @Valid Long sessionId,
            @RequestBody WorkoutSessioUpdateDataDto dto
    ){
        return ResponseEntity.ok(service.updateData(sessionId, dto));
    }

    //list by client
    @GetMapping("/all-for-athlete/{athleteId}")
    public ResponseEntity<Page<WorkoutSessionDto>> listAllByClient(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable @Valid String athleteId
    ){
        Page<WorkoutSessionDto> sessions = service.listAllByAthlete(athleteId, page, size);
        return ResponseEntity.ok(sessions);
    }

    //Training Session Stats For This Month
    @GetMapping("/get-workout-stats-actual-month/{athleteId}")
    public ResponseEntity<WorkoutSessionAthleteActualMonthSummaryResponseDto> getSessionsForAchtualMonthStat(@PathVariable String athleteId){
        return ResponseEntity.ok(service.getActualMonthSessionStats(athleteId));
    }

    //get client all time session stats
    @GetMapping("/get-workout-stats-all-time/{athleteId}")
    public ResponseEntity<WorkoutSessionAthleteAlltimeSummaryResponseDto> getSessionsForAllTimeStats(
            @PathVariable String athleteId
    ){
        return ResponseEntity.ok(service.getAllTimeSessionStats(athleteId));
    }

    //get session Data for quality chart on session stats
    @GetMapping("/get-workout-quality-stats-for-chart/{athleteId}")
    public ResponseEntity<List<WorkoutSessionQualityResponseDto>> getSessionsQualityAvarages(@PathVariable String athleteId){
        return ResponseEntity.ok(service.getSessionsQuality(athleteId));
    }

    //get total sessions and best 3 clients by session for home page
    @GetMapping("/get-workout-summary")
    public ResponseEntity<WorkoutSessionTotalSummaryResponseDto> getWorkoutSumary(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(service.getToalSesssionsSummary(token));
    }

}
