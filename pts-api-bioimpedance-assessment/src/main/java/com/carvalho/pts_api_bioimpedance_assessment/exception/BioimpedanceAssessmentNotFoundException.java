package com.carvalho.pts_api_bioimpedance_assessment.exception;

public class BioimpedanceAssessmentNotFoundException extends RuntimeException{

    public BioimpedanceAssessmentNotFoundException(String message) {
        super(message);
    }

    public static BioimpedanceAssessmentNotFoundException forBioimpedanceAssessmentId(String id){
        return new BioimpedanceAssessmentNotFoundException("Bioimpedance Assessment Withe id: " + id + " not found");
    }


}
