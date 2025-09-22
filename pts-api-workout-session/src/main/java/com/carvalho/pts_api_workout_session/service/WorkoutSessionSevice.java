package com.carvalho.pts_api_workout_session.service;

import com.carvalho.pts_api_workout_session.dto.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WorkoutSessionSevice {

    WorkoutSessionDto save(WorkoutSessionDto dto, String athleteId);

    void delete(Long id);

    Long execute(@Valid Long sessionId);

    Long updateData(@Valid Long sessionId, WorkoutSessioUpdateDataDto request);

    Page<WorkoutSessionDto> listAllByAthlete(@Valid String athleteId, int page, int size);

    WorkoutSessionAthleteActualMonthSummaryResponseDto getActualMonthSessionStats(String athleteId);

    WorkoutSessionAthleteAlltimeSummaryResponseDto getAllTimeSessionStats(String clientId);

    List<WorkoutSessionQualityResponseDto> getSessionsQuality(String clientId);

    WorkoutSessionTotalSummaryResponseDto getToalSesssionsSummary(String token);

    void saveAthleteFromEvent(UserAthleteCreatedEventDto event);
}
