package org.algorithm.Lv1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class Ponketmon {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of(new int[]{3,3,3,2,2,2})
        );
    }

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/1845
     * 폰켓몬
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void ponketmon(int[] nums){
        System.out.println(solution(nums));
    }

    public int solution(int[] nums) {
        return Math.min((int) Arrays.stream(nums).distinct().count(), nums.length/2);
    }
}