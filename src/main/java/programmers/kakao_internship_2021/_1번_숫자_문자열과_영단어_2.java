package programmers.kakao_internship_2021;

import java.util.HashMap;
import java.util.Map;

public class _1번_숫자_문자열과_영단어_2 {
    Map<String, Integer> map = new HashMap<>();

    public int solution(String s) {
        init();

        StringBuilder temp = new StringBuilder();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (cur >= '0' && cur <= '9') {
                result.append(cur);
                continue;
            }

            temp.append(cur);
            if (map.containsKey(temp.toString())) {
                result.append(map.get(temp.toString()));
                temp = new StringBuilder();
            }
        }

        return Integer.parseInt(result.toString());
    }

    public void init() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        map.put("zero", 0);
    }
}
