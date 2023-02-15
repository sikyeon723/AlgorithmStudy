package org.algorithm.Lv1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CaesarCipher {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of("a B z", 4)
        );
    }

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12926
     * 시저 암호
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void caesarCipher(String s, int n){
        System.out.println(solution(s, n));
    }

    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(c != ' '){
                int before = c;
                c = (char)(c+n);
                if(c > 122) c = (char)(c - 26);
                else if(before <= 90 && c > 90) c = (char)(c - 26);
            }
            if(c > 122) c = (char)(c - 26);
            sb.append(c);
        }

        return sb.toString();
    }
}