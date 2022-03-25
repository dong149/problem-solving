package programmers.skill_check.level2;

import org.junit.jupiter.api.Test;

import java.util.*;

public class _2번_2 {
    Map<String, Integer> map = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        for (int i = 0; i < orders.length; i++) {
            char[] orderChar = orders[i].toCharArray();
            Arrays.sort(orderChar);
            orders[i] = String.valueOf(orderChar);
        }

        List<String> answer = new ArrayList<>();

        for (int i = 0; i < course.length; i++) {
            for (int j = 0; j < orders.length; j++) {
                combination(orders[j], new StringBuilder(), course[i], 0);
            }
            if (!map.isEmpty()) {
                List<Integer> values = new ArrayList<>(map.values());
                int max = Collections.max(values);
                if (max > 1) {
                    for (String key : map.keySet())
                        if (map.get(key) == max) {
                            answer.add(key);
                        }
                }
                map.clear();
            }
        }

        Collections.sort(answer);
        return answer.toArray(new String[]{});
    }

    private void combination(String order, StringBuilder sb, int m, int cur) {
        if (sb.length() == m) {
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            return;
        }

        for (int i = cur; i < order.length(); i++) {
            sb.append(order.charAt(i));
            combination(order, sb, m, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    @Test
    void test() {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        System.out.println(Arrays.toString(solution(orders, new int[]{2, 3, 4})));
    }
}
