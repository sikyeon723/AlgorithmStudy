package org.algorithm.Lv1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FailRate {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of(3, new int[]{1,1,1})
        );
    }

    /**
     * 실패율은 다음과 같이 정의한다.
     * 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
     * 전체 스테이지의 개수 N, 게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열 stages가 매개변수로 주어질 때, 실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 return 하도록 solution 함수를 완성하라.
     *
     * 제한사항
     * 스테이지의 개수 N은 1 이상 500 이하의 자연수이다.
     * stages의 길이는 1 이상 200,000 이하이다.
     * stages에는 1 이상 N + 1 이하의 자연수가 담겨있다.
     * 각 자연수는 사용자가 현재 도전 중인 스테이지의 번호를 나타낸다.
     * 단, N + 1 은 마지막 스테이지(N 번째 스테이지) 까지 클리어 한 사용자를 나타낸다.
     * 만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 하면 된다.
     * 스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0 으로 정의한다.
     *
     * https://school.programmers.co.kr/learn/courses/30/lessons/42889
     * 실패율
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void ternaryReverse(int N, int[] stages){
        System.out.println(Arrays.toString(solution(N, stages)));
    }

    public int[] solution(int N, int[] stages) {
        Map<Integer, Integer> stageMap = new HashMap<>();
        Map<Integer, Double> failMap = new HashMap<>();

        for(int i = 1; i <= N; i++) {
            stageMap.put(i,0);
        }
        for(int stage : stages) {
            if(stage == N+1) continue;
            stageMap.put(stage,stageMap.getOrDefault(stage,0) + 1);
        }

        int len = stages.length;
        for(int i = 0; i < stageMap.size(); i++) {
            if(len > 0) failMap.put(i+1,stageMap.getOrDefault(i+1,0)/(double)len);
            else failMap.put(i+1,0.0);
            len -= stageMap.get(i+1);
        }
        List<Map.Entry<Integer, Double>> list = failMap.entrySet()
                .stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
        int[] answer = new int[N];
        int i = 0;
        for (Map.Entry<Integer, Double> entry : list) {
            answer[i++] = entry.getKey();
        }
        return answer;
    }
}