package com.carvalho.pts_api_perimetry_assessment.exception;

public class PerimetryAssessmentNotFoundException extends RuntimeException{

    public PerimetryAssessmentNotFoundException(String message) {
        super(message);
    }

    public static PerimetryAssessmentNotFoundException forPerimetryAssessmentId(String id){
        return new PerimetryAssessmentNotFoundException("Perimetry Assessment with id: " + id + " not found.");
    }

}
