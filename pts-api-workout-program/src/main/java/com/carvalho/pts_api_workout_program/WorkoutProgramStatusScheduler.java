package com.carvalho.pts_api_workout_program;

import com.carvalho.pts_api_workout_program.entity.WorkoutProgramEntity;
import com.carvalho.pts_api_workout_program.service.impl.WorkoutProgramServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkoutProgramStatusScheduler {

    private final WorkoutProgramServiceImpl service;

    @Scheduled(cron = "0 0 0 * * ?")//Everyday at midnigth or (fixedRate = 5000) for every 5 seconds
    public void updateProgramStatusByEndDate(){
        LocalDate currentDate = LocalDate.now();

        List<WorkoutProgramEntity> expiredPrograms = service.findProgramByEndDateBefore(currentDate);

        for (WorkoutProgramEntity program : expiredPrograms){
            program.setEnabled(false);
            service.saveForScheduler(program);
        }
    }

}
