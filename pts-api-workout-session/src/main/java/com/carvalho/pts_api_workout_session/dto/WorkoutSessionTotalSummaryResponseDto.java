package com.carvalho.pts_api_workout_session.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutSessionTotalSummaryResponseDto {

    private Integer totalSessionsPerMonth;
    private Integer totalExecutedSessionsPerMonth;
    private Integer totalNotExecutedSessionsPerMonth;
    private List<String> bestThreeAthletesNames;
    private List<Integer> bestThreeAthletesNumOfSessions;

}
