package programmers.kakao_blind_2020;

import java.util.HashMap;
import java.util.Map;

// 완전탐색
public class _4번_가사_검색 {
    final char WILD_CHAR = '?';
    Map<String, Integer> wordsMap = new HashMap<>();

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        for (String word : words)
            putToMap(word);

        for (int i = 0; i < answer.length; i++) {
            answer[i] = wordsMap.getOrDefault(queries[i], 0);
        }

        return answer;
    }

    private void putToMap(String word) {
        StringBuilder forward = new StringBuilder(word);
        for (int i = 0; i < word.length(); i++) {
            forward.setCharAt(i, WILD_CHAR);
            wordsMap.put(
                    forward.toString(),
                    wordsMap.getOrDefault(forward.toString(), 0) + 1);
        }
        StringBuilder backward = new StringBuilder(word);
        for (int i = word.length() - 1; i >= 0; i--) {
            backward.setCharAt(i, WILD_CHAR);
            wordsMap.put(
                    backward.toString(),
                    wordsMap.getOrDefault(backward.toString(), 0) + 1);
        }
    }
}
