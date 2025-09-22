package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String[] visits = new String[]{"Sat", "Mon", "Mon"};

        int result = solution(visits);

        System.out.println(result);

    }

    public static int solution (String[] visits){
        List<String> weekDays = new ArrayList<String>();
        weekDays.add("Mon");
        weekDays.add("Tue");
        weekDays.add("Wed");
        weekDays.add("Thu");
        weekDays.add("Fri");
        weekDays.add("Sat");
        weekDays.add("Sun");

        int cards = 1;

        for (int i = 1; i < visits.length; i++){
            if (weekDays.indexOf(visits[i-1]) >= weekDays.indexOf(visits[i])){
                cards++;
            }
        }

        return cards;
    }

}