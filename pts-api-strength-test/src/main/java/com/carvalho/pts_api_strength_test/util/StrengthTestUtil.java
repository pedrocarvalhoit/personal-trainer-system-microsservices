package com.carvalho.pts_api_strength_test.util;

public class StrengthTestUtil {

    public static Double calculateRm(Double maxLoad){
        return (0.033 * 10 * maxLoad) + maxLoad;
    }

}