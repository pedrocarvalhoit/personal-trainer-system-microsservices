package com.carvalho.pts_api_inicial_assessment.exception;

public class InicialAssessmentNotFoundException extends RuntimeException{

    public InicialAssessmentNotFoundException(String message) {
        super(message);
    }

    public static InicialAssessmentNotFoundException forInicialAssessmentId(String id){
        return new InicialAssessmentNotFoundException("Inicial Assessment Withe id: " + id + " not found");
    }
}
