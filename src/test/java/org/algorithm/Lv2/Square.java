package org.algorithm.Lv2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.PriorityQueue;
import java.util.stream.Stream;

class Square {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of(8,12)
        );
    }

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/62048?language=java
     * 멀쩡한 사각형
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void square(int w, int h){
        System.out.println(solution(w, h));
    }

    public long solution(int w, int h) {
        int cnt = 0;
        for(long x = 1; x <= w; x++){
            double y = (-1 * h / (double)w) * x + h; // y = -(h/w)x + h 대각선으로 그어신 선
            System.out.println("(" + x + "," + y + ")");
            cnt += (int)y;
        }
        return cnt * 2;
    }
}