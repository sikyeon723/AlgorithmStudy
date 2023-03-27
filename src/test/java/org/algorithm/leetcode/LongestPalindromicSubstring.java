package org.algorithm.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class LongestPalindromicSubstring {

    private static Stream<Arguments> setParameter() {
        return Stream.of(
                Arguments.of("vvgogaewginhopuxzwyryobjjpieyhwfopiowxmyylvcgsnhfcnogpqpukzmnpewavoutbloyrrhatimmxfqmcwgfebuoqbbgvubbkjfvxivjfijlpvtsnhagzfptahhyojwzamayoiegkenycnkxzkambimhdykdcxyyfjnvztzypmfczdhhnkmfuvgkhzbwmjznykkagqdrueohgcmeidjqsvfugcioeduohprjtfdmtzosnhoiganffarokxjifzzxhixdzycwfheqqegduzwtiacmdhqfmxhazcxsqyrvrihfqzjxvawdeandnwgjlquvzadruiqmcsgibglhicsvzqknztqpkiihdoisxipkourentfvrltieihiktgzswtgcmmlfrjifqinhrbplbsgswqlbjkyxjwoshsvxlhlpgzwbmxzwaemtowcxwourjwmmwjruowxcwotmeawzxmbwzgplhlxvshsowjxykjblqwsgsblpbrhniqfijrflmmcgtwszgtkihieitlrvftneruokpixsiodhiikpqtznkqzvscihlgbigscmqiurdazvuqljgwndnaedwavxjzqfhirvryqsxczahxmfqhdmcaitwzudgeqqehfwcyzdxihxzzfijxkoraffnagiohnsoztmdftjrphoudeoicgufvsqjdiemcghoeurdqgakkynzjmwbzhkgvufmknhhdzcfmpyztzvnjfyyxcdkydhmibmakzxkncynekgeioyamazwjoyhhatpfzgahnstvpljifjvixvfjkbbuvgbbqoubefgwcmqfxmmitahrryolbtuovawepnmzkupqpgoncfhnsgcvlyymxwoipofwhyeipjjboyrywzxupohnigweagogvv")
        );
    }

    /**
     * https://leetcode.com/problems/longest-palindromic-substring/
     * 5. Longest Palindromic Substring
     * 펠린드롬이란 ❓
     * : 해당 문자열이 뒤집었을 때와 서로 같은 문자열을 뜻한다. 예를 들어, aba를 뒤집었을 때도 aba 이므로 aba는 펠린드롬이다.
     * ex stemmets
     */

    @ParameterizedTest
    @MethodSource("setParameter")
    void longestPalindromicSubstring(String s){
        System.out.println(solution(s));
    }

    public String solution(String s) {
        String setStr = null;
        String[] sArr = s.split("");
        int maxLen = 0;
        for(int i = 0; i < s.length(); i++) {
            Pattern pattern =  Pattern.compile(sArr[i]);
            Matcher matcher = pattern.matcher(s);
            System.out.println(matcher.groupCount());
            int maxIdx = s.indexOf(sArr[i]);
            for(int j = i+1; j <= maxIdx; j++) {
                if(maxLen > j-i) continue;
                if(sArr[j].equals(sArr[i])){
                    String substr = s.substring(i,j+1);
                    if(checkPalindromic(substr)) {
                        maxLen = substr.length();
                        setStr = substr;
                    }
                }
            }
        }
        if(setStr == null) setStr = s.split("")[0];
        return setStr;
    }

    public boolean checkPalindromic(String s) {
        double half = s.length() / (double) 2;
        StringBuilder sb = new StringBuilder(s.substring((int)half));
        if(sb.reverse().toString().equals(s.substring(0,(int)Math.round(half)))) return true;
        return false;
    }
}