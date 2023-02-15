package org.algorithm.Lv2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

class ChangeInteger {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of(1,600002,3)
        );
    }

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/154538
     * 숫자 변환하기
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void changeInteger(int x, int y, int n){
        System.out.println(solution(x, y, n));
    }

    public int solution(int x, int y, int n) {
        Set<Integer> bfs = new HashSet<>();
        bfs.add(x);
        int lenCnt = 0;
        while(!bfs.contains(y)) {
            Set<Integer> tempBfs = new HashSet<>();
            Iterator<Integer> iter = bfs.iterator();
            while(iter.hasNext()) {
                x = iter.next();
                if(!(x * 2 > y)) tempBfs.add(x * 2);
                if(!(x * 3 > y)) tempBfs.add(x * 3);
                if(!(x + n > y)) tempBfs.add(x + n);
            }
            bfs = tempBfs;
            bfs.removeIf(integer -> integer > y);
            if(bfs.isEmpty())  return -1;
            lenCnt++;
        }

        return lenCnt;
    }

}