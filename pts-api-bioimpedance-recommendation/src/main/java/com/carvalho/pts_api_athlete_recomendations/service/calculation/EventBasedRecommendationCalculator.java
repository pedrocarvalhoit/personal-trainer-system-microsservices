package com.carvalho.pts_api_athlete_recomendations.service.calculation;

import org.springframework.stereotype.Component;

@Component
public class EventBasedRecommendationCalculator {

    private static final double WATTER_INTAKE_PARAMETER = 35;
    private static final double CALORIC_DEFICIT_PER_KG = 7700;
    private static final double CALORIC_RATE_SLOW = 250;
    private static final double CALORIC_RATE_MODERATE = 500;
    private static final double CALORIC_RATE_FAST = 750;

    public Double calculateWaterIntake(double bodyWeight) {
        return bodyWeight * WATTER_INTAKE_PARAMETER;
    }

    public Double calculateCaloriesIntakeSlow(double bodyWeight, double idealWeight, double basalMetabolism) {
        return caloricAdjustment(bodyWeight, idealWeight, basalMetabolism, CALORIC_RATE_SLOW);
    }

    public Double calculateCaloriesIntakeModerate(double bodyWeight, double idealWeight, double basalMetabolism) {
        return caloricAdjustment(bodyWeight, idealWeight, basalMetabolism, CALORIC_RATE_MODERATE);
    }

    public Double calculateCaloriesIntakeFast(double bodyWeight, double idealWeight, double basalMetabolism) {
        return caloricAdjustment(bodyWeight, idealWeight, basalMetabolism, CALORIC_RATE_FAST);
    }

    private Double caloricAdjustment(double bodyWeight, double idealWeight, double basalMetabolism, double rate) {
        double difference = idealWeight - bodyWeight;
        if (difference < 0) {
            return basalMetabolism - rate;
        } else {
            return basalMetabolism + rate;
        }
    }

    public Double daysToAchieveIdealWeightSlow(double bodyWeight, double idealWeight) {
        return calculateDays(bodyWeight, idealWeight, CALORIC_RATE_SLOW);
    }

    public Double daysToAchieveIdealWeightModerate(double bodyWeight, double idealWeight) {
        return calculateDays(bodyWeight, idealWeight, CALORIC_RATE_MODERATE);
    }

    public Double daysToAchieveIdealWeightFast(double bodyWeight, double idealWeight) {
        return calculateDays(bodyWeight, idealWeight, CALORIC_RATE_FAST);
    }

    private Double calculateDays(double bodyWeight, double idealWeight, double rate) {
        double diff = Math.abs(idealWeight - bodyWeight);
        double totalCalories = diff * CALORIC_DEFICIT_PER_KG;
        return totalCalories / rate;
    }
}
