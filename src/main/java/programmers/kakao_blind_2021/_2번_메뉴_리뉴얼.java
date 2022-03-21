package programmers.kakao_blind_2021;

import org.junit.jupiter.api.Test;

import java.util.*;

public class _2번_메뉴_리뉴얼 {
    int[] courses;
    String[] orders;
    Set<String> answerSet = new HashSet<>();

    public String[] solution(String[] orders, int[] courses) {
        this.orders = orders;
        this.courses = courses;
        for (int i = 0; i < orders.length; i++) {
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            orders[i] = String.valueOf(chars);
        }
        for (int i = 0; i < courses.length; i++) {
            solve(courses[i]);
        }

        return answerSet.stream().sorted().toArray(String[]::new);
    }

    private void solve(int course) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < orders.length; i++) {
            putCombination(
                    map,
                    new StringBuilder(),
                    orders[i],
                    new boolean[orders[i].length()],
                    0,
                    course);
        }
        TreeMap<Integer, List<String>> answerMap = new TreeMap<>(Collections.reverseOrder());
        for (String key : map.keySet()) {
            answerMap.computeIfAbsent(map.get(key), k -> new ArrayList<>());
            List<String> cur = answerMap.get(map.get(key));
            cur.add(key);
        }

        if (!answerMap.isEmpty())
            if (answerMap.firstKey() >= 2)
                answerSet.addAll(answerMap.firstEntry().getValue());
    }

    private void putCombination(Map<String, Integer> map, StringBuilder sb, String order, boolean[] visited, int cur, int size) {
        if (sb.length() == size) {
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            return;
        }

        for (int i = cur; i < order.length(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            sb.append(order.charAt(i));
            putCombination(map, sb, order, visited, i + 1, size);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }

    @Test
    void test() {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] courses = {2, 3, 4};

        solution(orders, courses);
    }

}
