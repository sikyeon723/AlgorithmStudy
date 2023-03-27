package org.algorithm.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

class ReverseInteger {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of("1534236469")
        );
    }

    /**
     * https://leetcode.com/problems/reverse-integer/
     * 7. Reverse Integer
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void reverseInteger(int x){
        System.out.println(solution(x));
    }

    public int solution(int x) {
        if(x < -Math.pow(2,31)|| x > Math.pow(2,31) - 1) return 0;
        StringBuilder sb = new StringBuilder(Integer.toString(x));
        if(sb.indexOf("-") > -1){
            sb.delete(0,1);
            sb.append("-");
        }
        String answer = sb.reverse().toString();
        try {
            return Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
