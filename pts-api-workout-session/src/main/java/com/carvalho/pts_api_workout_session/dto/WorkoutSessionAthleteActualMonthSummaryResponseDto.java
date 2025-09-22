package com.carvalho.pts_api_workout_session.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutSessionAthleteActualMonthSummaryResponseDto {

    private Integer totalSessionsPlanedActualMonth;
    private Integer totalExecutedSessionsActualMonth;
    private Integer totalNotExecutedSessionsActualMonth;

    private double percentExecuted;
    private double percentNotExecuted;

}
