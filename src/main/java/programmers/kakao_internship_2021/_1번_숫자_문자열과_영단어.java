package programmers.kakao_internship_2021;

import java.util.HashMap;
import java.util.Map;

public class _1번_숫자_문자열과_영단어 {
    Map<String, Integer> dict = new HashMap<>();

    public int solution(String s) {

        dict.put("zero", 0);
        dict.put("one", 1);
        dict.put("two", 2);
        dict.put("three", 3);
        dict.put("four", 4);
        dict.put("five", 5);
        dict.put("six", 6);
        dict.put("seven", 7);
        dict.put("eight", 8);
        dict.put("nine", 9);

        StringBuilder answer = new StringBuilder();
        StringBuilder cache = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur >= '0' && cur <= '9') {
                answer.append(cur);
            } else {
                cache.append(cur);
                if (dict.containsKey(cache.toString())) {
                    answer.append(dict.get(cache.toString()));
                    cache = new StringBuilder();
                }
            }
        }


        return Integer.parseInt(answer.toString());
    }
}
