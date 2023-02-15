package org.algorithm.Lv2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.PriorityQueue;
import java.util.stream.Stream;

class Scoville {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of(new Integer[]{1, 2, 3, 9, 10, 12}, 7)
        );
    }

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/42626
     * 더 맵게
     * [Heap sort 성질]
     * 1. 왼쪽 자식 노드 인덱스 = 부모 노드 인덱스 × 2 + 1
     * 2. 오른쪽 자식 노드 인덱스 = 부모 노드 인덱스 × 2 + 2
     * 3. 부모 노드 인덱스 = (자식 노드 인덱스 - 1) / 2
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void scoville(Integer[] input, int expected){
        System.out.println(solution(input, expected));
    }

    public int solution(Integer[] scoville, int K) {
        PriorityQueue<Integer> heap = new PriorityQueue();
        for(int s : scoville){
            heap.add(s);
        }
        int answer = 0;
        while(heap.size() > 1 && heap.peek() < K){
            int f = heap.poll();
            int c = f + (heap.poll()*2);
            heap.offer(c);
            answer++;
        }
        if(heap.size() < 2 && heap.poll() < K) return -1;

        return answer;
    }
}