package com.carvalho.pts_api_inicial_assessment.util;

public class InicialAssessmentUtil {

    public static Integer setMaxHeartRate(Integer age){
        int fcMax = (int) (208 - (0.7 * age));
        return fcMax;
    }

}
