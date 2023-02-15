package org.algorithm.Lv1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

class TernaryReverse {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of(45)
        );
    }

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/68935
     * 3진법 뒤집기
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void ternaryReverse(int n){
        System.out.println(solution(n));
    }

    public int solution(int n) {
        String revers = _10to3(Integer.toString(n));
        return _3to10(revers);
    }

    public String _10to3(String n){
        if(Long.parseLong(n) < 3) return String.valueOf(Long.parseLong(n)%3);
        return String.valueOf(Long.parseLong(n)%3) + _10to3(String.valueOf(Long.parseLong(n)/3));
    }

    public int _3to10(String n){
        int a = 0;
        String[] arr = n.split("");
        for(int i = 0; i < arr.length; i++){
            a += Integer.parseInt(arr[arr.length - i - 1]) * Math.pow(3,i);
        }
        return a;
    }
}