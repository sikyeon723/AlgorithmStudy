package org.algorithm.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class ShipWithinDays {

    private static Stream<Arguments> setParameter() {
        int[] param = new int[5000];
        for(int i = 0; i < param.length; i++) {
            param[i] = (int)(Math.random() * 500);
        }
        return Stream.of(
                Arguments.of(new int[]{1,2,3,1,1}, 4)
        );
    }

    /**
     * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
     * 1011. Capacity To Ship Packages Within D Days
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void shipWithinDays(int[] weights, int days){
        System.out.println(solution(weights,days));
    }
    List list = new ArrayList();
    public int solution(int[] weights, int days) {
        int max = Arrays.stream(weights).sum();
        int min = Math.max(Arrays.stream(weights).max().getAsInt(), max / days);

        for(int i = min; i < max; i++) {
            if(days >= calcDays(weights, i)) return i;
        }
        return max;
    }

    public int calcDays(int[] weights, int shipWeight) {
        list.clear();
        int last = Arrays.stream(weights).reduce((a,b)->{
            if(shipWeight > a + b) {
                return a + b;
            } else if(shipWeight == a + b) {
                list.add(a + b);
                return 0;
            } else {
                list.add(a);
                return b;
            }
        }).getAsInt();
        if(last != 0) list.add(last);
        System.out.println(list);
        return list.size();
    }
}
