package programmers.kakao_internship_2020;

import java.util.*;

public class _3번_보석_쇼핑 {
    public int[] solution(String[] gems) {
        int[] answer = {0, gems.length - 1};
        Set<String> gemSet = new HashSet<>(Arrays.asList(gems));
        int gemCount = gemSet.size();

        int start = 0;
        int end = 0;
        Map<String, Integer> gemMap = new HashMap<>();

        for (int i = 0; i < gems.length; i++) {
            String curGem = gems[i];
            end = i;
            gemMap.put(curGem, gemMap.getOrDefault(curGem, 0) + 1);
            while (start <= end) {
                if (gemMap.get(gems[start]) > 1) {
                    gemMap.put(gems[start], gemMap.get(gems[start]) - 1);
                    start++;
                } else {
                    break;
                }
            }
            if (gemMap.keySet().size() == gemCount && answer[1] - answer[0] > end - start) {
                answer[0] = start;
                answer[1] = end;
            }
        }

        return new int[]{answer[0] + 1, answer[1] + 1};
    }
}
