package org.algorithm.Lv2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TwoQueueSum {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of(new int[]{3,2,7,2}, new int[]{4,6,5,1})
        );
    }

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/118667
     * 두 큐 합 같게 만들기
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void twoQueueSum(int[] queue1, int[] queue2){
        System.out.println(solution(queue1, queue2));
    }

    public long solution(int[] queue1, int[] queue2) {
        long qSum1 = Arrays.stream(queue1).sum();
        long qSum2 = Arrays.stream(queue2).sum();

        Queue<Integer> queueOne = new LinkedList<>(Arrays.stream(queue1).boxed().collect(Collectors.toList()));
        Queue<Integer> queueTwo = new LinkedList<>(Arrays.stream(queue2).boxed().collect(Collectors.toList()));

        if((qSum1 + qSum2) % 2 == 1) return -1;
        long half = (qSum1 + qSum2) / 2;
        for(int i = 0; i < queue1.length * 3; i++){
            if(qSum1 > qSum2) {
                if(queueOne.peek() > half) return -1;
                qSum1 -= queueOne.peek();
                qSum2 += queueOne.peek();
                queueTwo.add(queueOne.poll());
            } else if(qSum1 < qSum2) {
                if(queueTwo.peek() > half) return -1;
                qSum1 += queueTwo.peek();
                qSum2 -= queueTwo.peek();
                queueOne.add(queueTwo.poll());
            } else if(qSum1 == qSum2) {
                return i;
            }
        }
        return -1;
    }
}