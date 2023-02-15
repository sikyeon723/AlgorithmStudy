package org.algorithm.Lv1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class DartGame {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of("1D#2S*3S")
        );
    }

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/17682
     * [1차] 다트게임
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void dartGame(String dartResult){
        System.out.println(solution(dartResult));
    }

    public int solution(String dartResult) {
        String nRegEx = "[0-9]{1,2}";
        String sRegEx = "[SDT][*#]{0,1}";
        Pattern nPattern = Pattern.compile(nRegEx);
        Pattern sPattern2 = Pattern.compile(sRegEx);
        Matcher nMatcher = nPattern.matcher(dartResult);
        Matcher sMatcher = sPattern2.matcher(dartResult);

        double answer = 0;
        double prev = 0;
        while(nMatcher.find()) {
            sMatcher.find();
            int num = Integer.parseInt(nMatcher.group());
            String[] mat = sMatcher.group().split("");
            double a = Math.pow(num,"D".equals(mat[0]) ? 2 : "T".equals(mat[0]) ? 3 : 1);
            if (mat.length == 2) {
                if ("#".equals(mat[1])) {
                    answer -= a;
                    prev = -a;
                }
                if ("*".equals(mat[1])) {
                    answer += prev + a * 2;
                    prev = a * 2;
                }
            } else {
                answer += a;
                prev = a;
            }
        }

        return (int)answer;
    }
}