package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main2 {

    public static void main(String[] args) {

    String S = "14354";

    solution(S);

    }

    private static String solution(String s) {

        List<Integer> numbersIn = s.chars()
                .map(c -> c - '0')
                .boxed()
                .collect(Collectors.toList());

        List<Integer> numbersOut = numbersIn;


        for (int i = 0; i < numbersIn.size() - 1; i++) {  // Alteração no limite do for
            while (i < numbersIn.size() - 1 && numbersIn.get(i) + numbersIn.get(i + 1) < 9) {
                int number = numbersIn.get(i) + numbersIn.get(i + 1);  // A soma de dois números
                if (number <= 9) {
                    numbersOut.add(number);
                    i++;  // Incrementa i para avançar para o próximo número
                } else {
                    numbersOut.add(numbersIn.get(i));
                    i++;  // Incrementa i para avançar para o próximo número
                    break; // Sai do loop while
                }
            }
            // Se o while não rodar, adiciona o número atual na lista de saída
            if (i < numbersIn.size()) {
                numbersOut.add(numbersIn.get(i));
            }
        }

        for (Integer n : numbersOut){
            System.out.print(n);
        }

        return null;
    }


}