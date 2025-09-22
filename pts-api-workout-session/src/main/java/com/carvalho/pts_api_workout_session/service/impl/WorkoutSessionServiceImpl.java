package com.carvalho.pts_api_workout_session.service.impl;

import com.carvalho.pts_api_workout_session.JwtParser;
import com.carvalho.pts_api_workout_session.adapter.Adapter;
import com.carvalho.pts_api_workout_session.dto.*;
import com.carvalho.pts_api_workout_session.entity.PersonalAthleteMappingEntity;
import com.carvalho.pts_api_workout_session.entity.WorkoutSessionEntity;
import com.carvalho.pts_api_workout_session.repository.AthleteRepository;
import com.carvalho.pts_api_workout_session.repository.PersonalAthleteMappingRepository;
import com.carvalho.pts_api_workout_session.repository.WorkoutSessionRepository;
import com.carvalho.pts_api_workout_session.service.WorkoutSessionSevice;
import com.carvalho.pts_api_workout_session.entity.AthleteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutSessionServiceImpl implements WorkoutSessionSevice {

    private final WorkoutSessionRepository repository;
    private final PersonalAthleteMappingRepository mappingRepository;
    private final AthleteRepository athleteRepository;
    private final JwtParser jwtParser;
    private final Adapter adapter;

    @Override
    public WorkoutSessionDto save(WorkoutSessionDto dto, String athleteId) {
        WorkoutSessionEntity entity = adapter.modelToEntity(dto);
        entity.setAthleteId(athleteId);
        return adapter.entityToModel(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Long execute(Long sessionId) {
        WorkoutSessionEntity entity = repository.findById(sessionId).orElse(null);
        entity.setExecuted(true);
        repository.save(entity);
        return sessionId;
    }

    @Override
    public Long updateData(Long sessionId, WorkoutSessioUpdateDataDto dto) {
        WorkoutSessionEntity entity = repository.findById(sessionId).orElseThrow(()->new RuntimeException("session not found"));

        adapter.udpateEntityFromDto(entity, dto);

        WorkoutSessionEntity updatedEntity = repository.save(entity);

        return sessionId;
    }

    @Override
    public Page<WorkoutSessionDto> listAllByAthlete(String athleteId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("sessionDate").descending().and(Sort.by("sessionTime").descending()));
        Page<WorkoutSessionEntity> sessionPage = repository.findAllByAthleteId(athleteId, pageable);

        List<WorkoutSessionDto> dtoList = sessionPage.getContent()
                .stream()
                .map(adapter::entityToModel)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, sessionPage.getTotalElements());
    }

    /*This is the method used on Actual Month
    Client page for ws stats on client-dashboard*/
    @Override
    public WorkoutSessionAthleteActualMonthSummaryResponseDto getActualMonthSessionStats(String athleteId) {
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = now.withDayOfMonth(now.lengthOfMonth());

        List<WorkoutSessionEntity> sessionsActualMonth = repository.findTotalSessionsByAthleteIdForPeriod(athleteId, startDate, endDate);

        List<WorkoutSessionEntity> executedSessionActualMonth = repository.findTotalSessionsExecutedByAthleteIdForPeriod(athleteId, startDate, endDate);

        List<WorkoutSessionEntity> notExecutedSessionActualMonth = repository.findTotalSessionsNotExecutedByAthleteIdForPeriod(athleteId, startDate, endDate);

        int totalSessions = sessionsActualMonth.size();
        int executedSessions = executedSessionActualMonth.size();
        int notExecutedSessions = notExecutedSessionActualMonth.size();

        double percentExecuted = totalSessions == 0 ? 0 : (double) executedSessions / totalSessions * 100;
        double percentNotExecuted = totalSessions == 0 ? 0 : (double) notExecutedSessions / totalSessions * 100;

        String formattedPExecuted = String.format(Locale.US, "%.2f", percentExecuted);
        String formattedPNotExecuted = String.format(Locale.US,"%.2f", percentNotExecuted);

        return WorkoutSessionAthleteActualMonthSummaryResponseDto.builder()
                .totalSessionsPlanedActualMonth(sessionsActualMonth.size())
                .totalExecutedSessionsActualMonth(executedSessionActualMonth.size())
                .totalNotExecutedSessionsActualMonth(notExecutedSessionActualMonth.size())
                .percentExecuted(Double.parseDouble(formattedPExecuted))
                .percentNotExecuted(Double.parseDouble(formattedPNotExecuted))
                .build();
    }

    /*This is the method used on Monthly Avarage
     Client page for ws stats on client-dashboard*/
    @Override
    public WorkoutSessionAthleteAlltimeSummaryResponseDto getAllTimeSessionStats(String athleteId) {
        List<WorkoutSessionEntity> sessionsList = repository.findAllByAthleteId(athleteId);
        List<WorkoutSessionEntity> executedSessionsList = repository.findAllByExecutedIsTrueAndAthleteId(athleteId);
        List<WorkoutSessionEntity> notExecutedSessionsList = repository.findAllByExecutedIsFalseAndAthleteId(athleteId);

        int sessionsAmount = sessionsList.size();
        int executedSessionAmount = executedSessionsList.size();
        int notExecutedSessionAmount = notExecutedSessionsList.size();

        double percentExecuted = sessionsAmount == 0 ? 0 : (double) executedSessionAmount / sessionsAmount * 100;
        double percentNotExecuted = sessionsAmount == 0 ? 0 : (double) notExecutedSessionAmount / sessionsAmount * 100;

        String formattedPExecuted = String.format(Locale.US, "%.2f", percentExecuted);
        String formattedPNotExecuted = String.format(Locale.US,"%.2f", percentNotExecuted);

        return WorkoutSessionAthleteAlltimeSummaryResponseDto.builder()
                .totalSessionsPlanedAlltime(sessionsAmount)
                .totalExecutedSessionsAlltime(executedSessionAmount)
                .totalNotExecutedSessionsAlltime(notExecutedSessionAmount)
                .percentExecuted(Double.parseDouble(formattedPExecuted))
                .percentNotExecuted(Double.parseDouble(formattedPNotExecuted))
                .build();
    }

    /*This is the method used on
     Sessions quality stats (6 months) on client-dashboard
     CHATGP helped me on this
     Here i am creating a map with month and avarage of sbjective efforts and ptQuality
     Grouping by the months, create a map for each mont
     Than return the creation of DTO with first map key and value, and second map value */
    public List<WorkoutSessionQualityResponseDto> getSessionsQuality(String athleteId) {
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6).with(TemporalAdjusters.firstDayOfMonth());

        List<WorkoutSessionEntity> sessions = repository.findTotalSessionsByAthleteIdForPeriod(athleteId, sixMonthsAgo, LocalDate.now());

        //Group the sessions by month and calculates subjetive avarage
        Map<String, Double> subjectiveEffortStats = sessions.stream()
                .filter(WorkoutSessionEntity::isExecuted)//step1: Filter executed sessions
                .collect(Collectors.groupingBy(
                        session -> session.getSessionDate().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),//step2: Group by month
                        Collectors.averagingDouble(WorkoutSessionEntity::getClientSubjectiveEffort)//step3: Calculates avarage
                ));

        Map<String, Double> ptEffortQualityStats = sessions.stream()
                .filter(WorkoutSessionEntity::isExecuted)
                .collect(Collectors.groupingBy(
                        session -> session.getSessionDate().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                        Collectors.averagingDouble(WorkoutSessionEntity::getPtQualityEffortIndicative)
                ));

        //Comparator to sort in correct month order
        Comparator<String> monthComparator = Comparator.comparingInt(month -> Month.valueOf(month.toUpperCase()).getValue());

        return subjectiveEffortStats.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(monthComparator))
                .map(entry -> WorkoutSessionQualityResponseDto.builder()
                        .month(entry.getKey())
                        .clientSubjectEffortAvarage(entry.getValue())
                        .ptQualityEffortAvarage(ptEffortQualityStats.get(entry.getKey()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public WorkoutSessionTotalSummaryResponseDto getToalSesssionsSummary(String token) {
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = now.withDayOfMonth(now.lengthOfMonth());

        String loggedPersonalId = findLoggedUser(token);

        //Athletes for Logged Personal
        List<String> athletesForPersonalList = mappingRepository.findAllByPersonalId(loggedPersonalId)
                .stream()
                .map(PersonalAthleteMappingEntity::getAthleteId)
                .toList();

        //totalSessions
        List<WorkoutSessionEntity> totalSessionsPerMonthList = athletesForPersonalList.stream()
                .flatMap(athleteId -> repository.findTotalSessionsByAthleteIdForPeriod(athleteId, startDate, endDate).stream())
                .toList();
        Integer totalSessionPerMonth = totalSessionsPerMonthList.size();

        //totalExecutedSessions
        List<WorkoutSessionEntity> totalExecutedSessionsPerMonthList = athletesForPersonalList.stream()
                .flatMap(athleteId -> repository.findTotalSessionsByAthleteIdForPeriod(athleteId, startDate, endDate).stream())
                .filter(WorkoutSessionEntity::isExecuted)
                .toList();
        Integer monthTotalExecutedSessions = totalExecutedSessionsPerMonthList.size();

        //totalNotExecutedSessions
        List<WorkoutSessionEntity> totalNotExecutedSessionsPerMonthList = athletesForPersonalList.stream()
                .flatMap(athleteId -> repository.findTotalSessionsByAthleteIdForPeriod(athleteId, startDate, endDate).stream())
                .filter(session -> !session.isExecuted())
                .toList();
        Integer monthTotalNotExecutedSessions = totalNotExecutedSessionsPerMonthList.size();

        //get top clients names by workoutsessions
        Pageable pageable = PageRequest.of(0, 3);
        List<Object[]> athletesSessionsRank =  repository.findTopAthletesBySessionCount(athletesForPersonalList, startDate, endDate, pageable);
        List<String> bestThreeAthletesNames  = athletesSessionsRank.stream()
                .map(r -> (String) r[0])
                .toList();

        List<Integer> bestThreeAthletesNumOfSessions = athletesSessionsRank.stream()
                .map(r -> ((Long) r[1]).intValue())
                .toList();

        return WorkoutSessionTotalSummaryResponseDto.builder()
                .totalSessionsPerMonth(totalSessionPerMonth)
                .totalExecutedSessionsPerMonth(monthTotalExecutedSessions)
                .totalNotExecutedSessionsPerMonth(monthTotalNotExecutedSessions)
                .bestThreeAthletesNames(bestThreeAthletesNames)
                .bestThreeAthletesNumOfSessions(bestThreeAthletesNumOfSessions)
                .build();
    }

    private String findLoggedUser(String authHeader){
        return jwtParser.extractUserId(authHeader);
    }

    @Override
    public void saveAthleteFromEvent(UserAthleteCreatedEventDto event) {
        AthleteEntity entity = AthleteEntity.builder()
                .id(event.getAthleteId())
                .fullName(event.getFullName())
                .build();

        athleteRepository.save(entity);
    }

}
