package com.carvalho.pts_api_workout_program.service.impl;

import com.carvalho.pts_api_workout_program.adapter.Adapter;
import com.carvalho.pts_api_workout_program.dto.WorkoutProgramDto;
import com.carvalho.pts_api_workout_program.dto.WorkoutProgramUpdateRequestDto;
import com.carvalho.pts_api_workout_program.entity.WorkoutProgramEntity;
import com.carvalho.pts_api_workout_program.exporter.WorkoutProgramPDFExporter;
import com.carvalho.pts_api_workout_program.repository.WorkoutProgramRepository;
import com.carvalho.pts_api_workout_program.service.WorkoutProgramService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class WorkoutProgramServiceImpl implements WorkoutProgramService {

    private final WorkoutProgramRepository repository;
    private final Adapter adapter;

    public WorkoutProgramServiceImpl(WorkoutProgramRepository repository, Adapter adapter) {
        this.repository = repository;
        this.adapter = adapter;
    }

    @Override
    public WorkoutProgramDto save(String athleteId, WorkoutProgramDto requestBody) {
        WorkoutProgramEntity entity = adapter.workoutProgramModelToEntity(requestBody);
        entity.setAthleteId(athleteId);

        return adapter.workoutProgramEntityToModel(repository.save(entity));
    }

    @Override
    public List<WorkoutProgramDto> getAllForAthlete(String athleteId) {
        List<WorkoutProgramEntity> entityList = repository.findAllByAthleteId(athleteId);

        return entityList.stream()
                .map(adapter::workoutProgramEntityToModel)
                .toList();
    }

    @Override
    public WorkoutProgramDto update(Long programId, WorkoutProgramUpdateRequestDto requestDto) {
        WorkoutProgramEntity entity = repository.findById(programId).orElseThrow(() -> {
            return new EntityNotFoundException("Workout program with id " + programId + " not found");
        });

        adapter.updateEntityFromDto(entity, requestDto);

        WorkoutProgramEntity updatedEntity = repository.save(entity);

        return adapter.workoutProgramEntityToModel(updatedEntity);
    }

    //Schedulr Methods
    @Override
    public List<WorkoutProgramEntity> findProgramByEndDateBefore(LocalDate date) {
        return repository.findAllByEndDateBeforeAndEnabledTrue(date);
    }

    @Override
    public void saveForScheduler(WorkoutProgramEntity program) {
        repository.save(program);
    }

    @Override
    public void exportToPdf(HttpServletResponse response, Long programId) throws IOException {
        WorkoutProgramEntity entity = repository.findById(programId).get();
        WorkoutProgramPDFExporter exporter = new WorkoutProgramPDFExporter();
        exporter.export(entity, response, "workoutprogram");
    }

//    word exporter
//    public void exportToWord(HttpServletResponse response, Integer programId) throws IOException {
//        WorkoutProgram workoutProgram = workoutProgramRepository.findById(programId).get();
//        WorkoutProgramWordExporter exporter = new WorkoutProgramWordExporter();
//        exporter.export(workoutProgram, response, "workoutprogram");
//    }

}
