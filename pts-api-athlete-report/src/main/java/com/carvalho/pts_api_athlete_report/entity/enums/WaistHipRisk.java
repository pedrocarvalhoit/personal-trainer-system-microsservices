package com.carvalho.pts_api_athlete_report.entity.enums;

public enum WaistHipRisk {

    LOW_RISK,
    MODERATE_RISK,
    HIGH_RISK;

    public static WaistHipRisk classify(Double waist, Double hip, String gender) {
        Double wistHipRisk = waist / hip;

        if("MALE".equalsIgnoreCase(gender)){
            if (wistHipRisk <= 0.90) return LOW_RISK;
            if (wistHipRisk <= 0.99) return MODERATE_RISK;
            return HIGH_RISK;
        }else if ("FEMALE".equalsIgnoreCase(gender)) {
            if (wistHipRisk <= 0.80) return LOW_RISK;
            if (wistHipRisk <= 0.84) return MODERATE_RISK;
            return HIGH_RISK;
        }

        throw new IllegalArgumentException("Invalid gender: " + gender);
    }
}
