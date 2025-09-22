package com.carvalho.pts_api_athlete_report.entity.enums;

public enum BMIStatus {

    SEVERE_THINNESS,
    MODERATE_THINNESS,
    MILD_THINNESS,
    NORMAL,
    OVERWEIGHT,
    OBESITY_CLASS_I,
    OBESITY_CLASS_II,
    OBESITY_CLASS_III;

    public static BMIStatus from(double bmi) {
        if (bmi < 16) {
            return SEVERE_THINNESS;
        } else if (bmi < 17) {
            return MODERATE_THINNESS;
        } else if (bmi < 18.5) {
            return MILD_THINNESS;
        } else if (bmi < 25) {
            return NORMAL;
        } else if (bmi < 30) {
            return OVERWEIGHT;
        } else if (bmi < 35) {
            return OBESITY_CLASS_I;
        } else if (bmi < 40) {
            return OBESITY_CLASS_II;
        } else {
            return OBESITY_CLASS_III;
        }
    }

}
