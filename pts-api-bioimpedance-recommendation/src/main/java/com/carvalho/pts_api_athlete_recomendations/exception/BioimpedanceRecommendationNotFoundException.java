package com.carvalho.pts_api_athlete_recomendations.exception;

public class BioimpedanceRecommendationNotFoundException extends RuntimeException{

    public BioimpedanceRecommendationNotFoundException(String message) {
        super(message);
    }

    public static BioimpedanceRecommendationNotFoundException forBioimepdanceRecommendationId(String id){
        return new BioimpedanceRecommendationNotFoundException("Bioimpedance Recommendation with id: " + id + " not found.");
    }

}
