package org.algorithm.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class MostWater {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7})
        );
    }

    /**
     * https://leetcode.com/problems/container-with-most-water/
     * 11. Container With Most Water
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void mostWater(int[] height){
        System.out.println(solution(height));
    }

    public int solution(int[] height) {
        int max = 0;
        int h = 0;
        for (int i = 0; i < height.length - 1; i++) {
            if(h >= height[i]) continue;
            h = height[i];
            for(int j = i + 1; j < height.length; j++) {
                int w = j - i;
                max = Math.max(max,w * Math.min(height[i], height[j]));
            }
        }

        return max;
    }
}
