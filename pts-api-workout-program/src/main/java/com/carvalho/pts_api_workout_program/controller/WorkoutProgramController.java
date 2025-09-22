package com.carvalho.pts_api_workout_program.controller;

import com.carvalho.pts_api_workout_program.dto.WorkoutProgramDto;
import com.carvalho.pts_api_workout_program.dto.WorkoutProgramUpdateRequestDto;
import com.carvalho.pts_api_workout_program.service.impl.WorkoutProgramServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("workout-program")
@Tag(name = "WorkoutProgram")
@RequiredArgsConstructor
public class WorkoutProgramController {

    private final WorkoutProgramServiceImpl service;

    @PostMapping("/create-for-athlete/{athleteId}")
    public ResponseEntity<WorkoutProgramDto> createForAthlete(@PathVariable String athleteId,
                                                              @RequestBody @Valid WorkoutProgramDto requestBody){
        WorkoutProgramDto savedWorkoutProgram = service.save(athleteId, requestBody);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedWorkoutProgram.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedWorkoutProgram);
    }

    //List All for athlete
    @GetMapping("/all-for-athlete/{athleteId}")
    public ResponseEntity<List<WorkoutProgramDto>> getAllForAthlete(@PathVariable String athleteId){
        List<WorkoutProgramDto> programList = service.getAllForAthlete(athleteId);
        return ResponseEntity.ok(programList);
    }

    //update program data
    @PatchMapping("/update/{programId}")
    public ResponseEntity<WorkoutProgramDto> updateProgramDate(@PathVariable Long programId,
                                                     @RequestBody @Valid WorkoutProgramUpdateRequestDto request){

        return ResponseEntity.ok(service.update(programId, request));
    }

    //PDF dowload
    @GetMapping("export-pdf/{programId}")
    public void exporToPdf(HttpServletResponse response, @PathVariable Long programId) throws IOException {
        service.exportToPdf(response, programId);
    }

}
