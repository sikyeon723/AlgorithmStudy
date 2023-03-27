package org.algorithm.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.regex.Pattern;
import java.util.stream.Stream;

class RegularExpressionMatching {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of("aa","a*")
        );
    }

    /**
     * https://leetcode.com/problems/regular-expression-matching/
     * 10. Regular Expression Matching
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void regularExpressionMatching(String s, String p){
        System.out.println(solution(s, p));
    }

    public boolean solution(String s, String p) {
        System.out.println(Pattern.matches(p, s));
        return false;
    }
}
