package org.algorithm.Lv1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

class IntegerDESC {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of(118372)
        );
    }

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12933
     * 정수 내림차순으로 배치하기
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void integerDESC(long n){
        System.out.println(solution(n));
    }

    public long solution(long n) {
        String[] str = String.valueOf(n).split("");
        return Long.parseLong(Arrays.stream(str).sorted(Comparator.reverseOrder()).reduce((a, b)->a+b).get());
    }

}