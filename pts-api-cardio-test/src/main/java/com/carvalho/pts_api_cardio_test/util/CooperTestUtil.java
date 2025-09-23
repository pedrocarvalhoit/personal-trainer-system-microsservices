package com.carvalho.pts_api_cardio_test.util;

import com.carvalho.pts_api_cardio_test.enums.CooperTestClassification;

public class CooperTestUtil {

    public static Double calculateVo2Max(Double distance){
        return (0.0268 * distance) - 11.3;
    }

    public static CooperTestClassification getClassification(Integer age, String gender, Double result) {
        if (gender.equalsIgnoreCase("FEMALE")) {
            return classifyForFemale(age, result);
        } else if (gender.equalsIgnoreCase("MALE")) {
            return classifyForMale(age, result);
        }
        return CooperTestClassification.UNDEFINED;
    }

    private static CooperTestClassification classifyForFemale(Integer age, Double result) {
        if (age >= 15 && age <= 29) {
            if (result < 24) {
                return CooperTestClassification.VERY_WEAK;
            } else if (result <= 30) {
                return CooperTestClassification.WEAK;
            } else if (result <= 37) {
                return CooperTestClassification.REGULAR;
            } else if (result <= 48) {
                return CooperTestClassification.GOOD;
            } else {
                return CooperTestClassification.EXCELLENT;
            }
        } else if (age <= 39) {
            if (result <= 19) {
                return CooperTestClassification.VERY_WEAK;
            } else if (result <= 27) {
                return CooperTestClassification.WEAK;
            } else if (result <= 33) {
                return CooperTestClassification.REGULAR;
            } else if (result <= 44) {
                return CooperTestClassification.GOOD;
            } else {
                return CooperTestClassification.EXCELLENT;
            }
        }else if (age <= 49) {
            if (result <= 17) {
                return CooperTestClassification.VERY_WEAK;
            } else if (result <= 23) {
                return CooperTestClassification.WEAK;
            } else if (result <= 30) {
                return CooperTestClassification.REGULAR;
            } else if (result <= 41) {
                return CooperTestClassification.GOOD;
            } else {
                return CooperTestClassification.EXCELLENT;
            }
        }else if (age <= 59) {
            if (result <= 15) {
                return CooperTestClassification.VERY_WEAK;
            } else if (result <= 20) {
                return CooperTestClassification.WEAK;
            } else if (result <= 27) {
                return CooperTestClassification.REGULAR;
            } else if (result <= 37) {
                return CooperTestClassification.GOOD;
            } else {
                return CooperTestClassification.EXCELLENT;
            }
        }else if (age > 60) {
            if (result <= 13) {
                return CooperTestClassification.VERY_WEAK;
            } else if (result <= 17) {
                return CooperTestClassification.WEAK;
            } else if (result <= 23) {
                return CooperTestClassification.REGULAR;
            } else if (result <= 34) {
                return CooperTestClassification.GOOD;
            } else {
                return CooperTestClassification.EXCELLENT;
            }
        }

        return CooperTestClassification.UNDEFINED;
    }

    private static CooperTestClassification classifyForMale(Integer age, Double result) {
        if (age >= 15 && age <= 29) {
            if (result < 25) {
                return CooperTestClassification.VERY_WEAK;
            } else if (result <= 33) {
                return CooperTestClassification.WEAK;
            } else if (result <= 42) {
                return CooperTestClassification.REGULAR;
            } else if (result <= 52) {
                return CooperTestClassification.GOOD;
            } else {
                return CooperTestClassification.EXCELLENT;
            }
        }else if (age <= 39) {
            if (result <= 23) {
                return CooperTestClassification.VERY_WEAK;
            } else if (result <= 30) {
                return CooperTestClassification.WEAK;
            } else if (result <= 38) {
                return CooperTestClassification.REGULAR;
            } else if (result <= 48) {
                return CooperTestClassification.GOOD;
            } else {
                return CooperTestClassification.EXCELLENT;
            }
        }else if (age <= 49) {
            if (result <= 20) {
                return CooperTestClassification.VERY_WEAK;
            } else if (result <= 26) {
                return CooperTestClassification.WEAK;
            } else if (result <= 35) {
                return CooperTestClassification.REGULAR;
            } else if (result <= 44) {
                return CooperTestClassification.GOOD;
            } else {
                return CooperTestClassification.EXCELLENT;
            }
        }else if (age <= 59) {
            if (result <= 18) {
                return CooperTestClassification.VERY_WEAK;
            } else if (result <= 24) {
                return CooperTestClassification.WEAK;
            } else if (result <= 33) {
                return CooperTestClassification.REGULAR;
            } else if (result <= 42) {
                return CooperTestClassification.GOOD;
            } else {
                return CooperTestClassification.EXCELLENT;
            }
        }else if (age > 60) {
            if (result <= 16) {
                return CooperTestClassification.VERY_WEAK;
            } else if (result <= 22) {
                return CooperTestClassification.WEAK;
            } else if (result <= 30) {
                return CooperTestClassification.REGULAR;
            } else if (result <= 40) {
                return CooperTestClassification.GOOD;
            } else {
                return CooperTestClassification.EXCELLENT;
            }
        }

        return CooperTestClassification.UNDEFINED;
    }
}