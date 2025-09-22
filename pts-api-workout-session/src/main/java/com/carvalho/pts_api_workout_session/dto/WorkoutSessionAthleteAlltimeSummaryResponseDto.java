package com.carvalho.pts_api_workout_session.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutSessionAthleteAlltimeSummaryResponseDto {

    private Integer totalSessionsPlanedAlltime;
    private Integer totalExecutedSessionsAlltime;
    private Integer totalNotExecutedSessionsAlltime;

    private double percentExecuted;
    private double percentNotExecuted;

}
