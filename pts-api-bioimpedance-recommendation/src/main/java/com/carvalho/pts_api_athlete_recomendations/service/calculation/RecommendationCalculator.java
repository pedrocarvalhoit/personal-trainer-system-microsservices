package com.carvalho.pts_api_athlete_recomendations.service.calculation;

import com.carvalho.pts_api_athlete_recomendations.client.BioimpedanceAssessmentClient;
import org.springframework.stereotype.Component;

@Component
public class RecommendationCalculator {

    private static final double WATTER_INTAKE_PARAMETER = 35;
    private static final double CALORIC_DEFICIT_PER_KG = 7700;
    private static final double CALORIC_RATE_SLOW = 250;
    private static final double CALORIC_RATE_MODERATE = 500;
    private static final double CALORIC_RATE_FAST = 750;

    private final BioimpedanceAssessmentClient bioimpedanceAssessmentClient;

    public RecommendationCalculator(BioimpedanceAssessmentClient bioimpedanceAssessmentClient) {
        this.bioimpedanceAssessmentClient = bioimpedanceAssessmentClient;
    }

    /**
     * This method calculates how much it is the water intake in ml.
     * The actual formula is 35 ml per kg.
     * @param id the bioimpedance ID
     * @return water intake recommendation
     */
    public Double calculateWaterIntake(Long id) {
        double bodyWeight = bioimpedanceAssessmentClient.bodyWeight(id);
        return bodyWeight * WATTER_INTAKE_PARAMETER;
    }




    /**
     * Calculates the daily caloric intake required to achieve the ideal weight slowly (moderate caloric deficit).
     * The caloric intake is calculated by subtracting a fixed value (CALORIC_RATE_SLOW) from the athlete's basal metabolism.
     *
     * Formula:
     * - Caloric intake to achieve ideal weight = Basal metabolism - Slow caloric deficit
     *
     * @param id The athlete's ID, used to fetch basal metabolism information.
     * @return The number of calories the athlete should consume to achieve the ideal weight slowly.
     */
    public Double calculateCaloriesIntakeToAchieveIdealWeightSlow(Long id) {
        double differenceBetweenWeightAndIdealWeight = bioimpedanceAssessmentClient.idealWeight(id) - bioimpedanceAssessmentClient.bodyWeight(id);

        if (differenceBetweenWeightAndIdealWeight < 0){
            return bioimpedanceAssessmentClient.basalMetabolism(id) - CALORIC_RATE_SLOW;
        }

        return bioimpedanceAssessmentClient.basalMetabolism(id) + CALORIC_RATE_SLOW;
    }

    public Double calculateCaloriesIntakeToAchieveIdealWeightModerate(Long id) {
        double differenceBetweenWeightAndIdealWeight = bioimpedanceAssessmentClient.idealWeight(id) - bioimpedanceAssessmentClient.bodyWeight(id);

        if (differenceBetweenWeightAndIdealWeight < 0){
            return bioimpedanceAssessmentClient.basalMetabolism(id) - CALORIC_RATE_MODERATE;
        }

        return bioimpedanceAssessmentClient.basalMetabolism(id) + CALORIC_RATE_MODERATE;
    }

    public Double calculateCaloriesIntakeToAchieveIdealWeightFast(Long id) {
        double differenceBetweenWeightAndIdealWeight = bioimpedanceAssessmentClient.idealWeight(id) - bioimpedanceAssessmentClient.bodyWeight(id);

        if (differenceBetweenWeightAndIdealWeight < 0){
            return bioimpedanceAssessmentClient.basalMetabolism(id) - CALORIC_RATE_FAST;
        }

        return bioimpedanceAssessmentClient.basalMetabolism(id) + CALORIC_RATE_FAST;
    }

    /**
     * Calculates the number of days required to achieve the ideal weight, based on a slow caloric deficit.
     * The calculation is based on the difference between the current weight and the ideal weight, and a caloric deficit per kg.
     *
     * Formula:
     * - Total calories needed = (Current weight - Ideal weight) * Caloric deficit per kg
     * - Days to achieve ideal weight = Total calories / Daily caloric burn rate
     *
     * @param id The athlete's ID, used to fetch current weight and ideal weight information.
     * @return The estimated number of days required to achieve the ideal weight with a slow caloric deficit.
     */
    public Double daysToAchieveIdealWeightSlow(Long id) {
        double differenceBetweenWeightAndIdealWeight = bioimpedanceAssessmentClient.idealWeight(id) - bioimpedanceAssessmentClient.bodyWeight(id);

        differenceBetweenWeightAndIdealWeight = Math.abs(differenceBetweenWeightAndIdealWeight);

        var totalCaloriesToAchieveGoal = differenceBetweenWeightAndIdealWeight * CALORIC_DEFICIT_PER_KG;

        return totalCaloriesToAchieveGoal / CALORIC_RATE_SLOW;
    }

    public Double daysToAchieveIdealWeightModerate(Long id) {
        double differenceBetweenWeightAndIdealWeight = bioimpedanceAssessmentClient.idealWeight(id) - bioimpedanceAssessmentClient.bodyWeight(id);

        differenceBetweenWeightAndIdealWeight = Math.abs(differenceBetweenWeightAndIdealWeight);

        var totalCaloriesToAchieveGoal = differenceBetweenWeightAndIdealWeight * CALORIC_DEFICIT_PER_KG;

        return totalCaloriesToAchieveGoal / CALORIC_RATE_MODERATE;
    }

    public Double daysToAchieveIdealWeightFast(Long id) {
        double differenceBetweenWeightAndIdealWeight = bioimpedanceAssessmentClient.idealWeight(id) - bioimpedanceAssessmentClient.bodyWeight(id);

        differenceBetweenWeightAndIdealWeight = Math.abs(differenceBetweenWeightAndIdealWeight);

        var totalCaloriesToAchieveGoal = differenceBetweenWeightAndIdealWeight * CALORIC_DEFICIT_PER_KG;

        return totalCaloriesToAchieveGoal / CALORIC_RATE_FAST;
    }

}
