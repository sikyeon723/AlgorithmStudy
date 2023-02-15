package org.algorithm.Lv2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

class IntegerToRoman {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of(1964)
        );
    }

    /**
     * https://leetcode.com/problems/integer-to-roman/
     * 12. Integer to Roman
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void intToRoman(int num){
        System.out.println(solution(num));
    }

    public String solution(int num) {
        StringBuilder sb = new StringBuilder();
        for(int length = 3 ;length >= 0; length--) {
            int div = (int) Math.pow(10,length);
            sb.append(changeRoman( num / div, length));
            num %= div;
        }
        return sb.toString();
    }

    /*
     * Symbol       Value
     *  I             1
     *  V             5
     *  X             10
     *  L             50
     *  C             100
     *  D             500
     *  M             1000
     */
    public String changeRoman(int num, int length) {
        String character = "";
        String fCharacter = "";
        String tCharacter = "";
        switch (length) {
            case 3:
                character = "M";
                break;
            case 2:
                character = "C";
                fCharacter = "D";
                tCharacter = "M";
                break;
            case 1:
                character = "X";
                fCharacter = "L";
                tCharacter = "C";
                break;
            case 0:
                character = "I";
                fCharacter = "V";
                tCharacter = "X";
                break;
        }
        StringBuilder sb = new StringBuilder();

        for(int i = num; i > 0; i--) sb.append(character);
        if(num == 4) {
            sb.replace(1,4, fCharacter);
        } else if (4 < num && num < 9) {
            sb.replace(0,5, fCharacter);
        } else if (num == 9) {
            sb.replace(1,9, tCharacter);
        }

        return sb.toString();
    }

}