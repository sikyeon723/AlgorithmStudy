package org.algorithm.Lv1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PersonalityType {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of(new String[]{"AN", "CF", "MJ", "RT", "NA"}, new int[]{5, 3, 2, 7, 5})
        );
    }

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/118666
     * 성격 유형 검사하기
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void personalityType(String[] survey, int[] choices){
        System.out.println(solution(survey, choices));
    }

    public String solution(String[] survey, int[] choices) {
        Map<String, Integer> scoreMap = new HashMap<>();

        int score = 0;
        String target = "";
        for(int i = 0; i < survey.length; i++) {
            String[] surArr = survey[i].split("");
            if(choices[i] - 4 < 0) {
                target = surArr[0];
            } else {
                target = surArr[1];
            }
            score = Math.abs(choices[i] - 4);
            if(score > 0) scoreMap.put(target, scoreMap.getOrDefault(target, 0) + score);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(scoreMap.getOrDefault("R",0) < scoreMap.getOrDefault("T",0) ? "T" : "R");
        sb.append(scoreMap.getOrDefault("C",0) < scoreMap.getOrDefault("F",0) ? "F" : "C");
        sb.append(scoreMap.getOrDefault("J",0) < scoreMap.getOrDefault("M",0) ? "M" : "J");
        sb.append(scoreMap.getOrDefault("A",0) < scoreMap.getOrDefault("N",0) ? "N" : "A");
        return sb.toString();
    }
}