package com.carvalho.pts_api_athlete_report.entity.enums;

public enum BloodPressureStatus {

    NORMAL, ELEVATED, HYPERTENSION_STAGE_1, HYPERTENSION_STAGE_2, HYPERTENSIVE_CRISIS;

    public static BloodPressureStatus classify(Integer systolic, Integer diastolic) {
        if(systolic > 180 || diastolic > 120){
            return HYPERTENSIVE_CRISIS;
        }

        if (systolic >= 140 || diastolic >= 90) {
            return HYPERTENSION_STAGE_2;
        }

        if (systolic >= 130 || diastolic >= 80) {
            return HYPERTENSION_STAGE_1;
        }

        if (systolic >= 120 && diastolic < 80) {
            return ELEVATED;
        }

        return NORMAL;
    }

}
