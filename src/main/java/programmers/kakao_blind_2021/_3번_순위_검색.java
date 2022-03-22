package programmers.kakao_blind_2021;

import org.junit.jupiter.api.Test;

import java.util.*;

public class _3번_순위_검색 {
    final String ALL = "-";
    Map<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] infos, String[] queries) {
        for (String info : infos) {
            putCases(info.split(" "), new StringBuilder(), 0);
        }

        map.keySet().forEach(key -> Collections.sort(map.get(key)));

        List<Integer> answer = new ArrayList<>();
        for (String query : queries) {
            answer.add(solve(query));
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private void putCases(String[] splitedInfo, StringBuilder sb, int count) {
        if (count == 4) {
            int score = Integer.parseInt(splitedInfo[4]);
            String info = sb.toString();
            if (!map.containsKey(info)) {
                map.put(info, new ArrayList<>());
            }
            map.get(info).add(score);
            return;
        }

        sb.append(splitedInfo[count]);
        putCases(splitedInfo, sb, count + 1);
        sb.delete(sb.length() - splitedInfo[count].length(), sb.length());
        sb.append(ALL);
        putCases(splitedInfo, sb, count + 1);
        sb.deleteCharAt(sb.length() - 1);
    }

    private int solve(String query) {
        String[] splited = query.replaceAll(" and", "").split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) sb.append(splited[i]);

        if (!map.containsKey(sb.toString())) return 0;


        List<Integer> scoreList = map.get(sb.toString());
        int left = 0;
        int right = scoreList.size();
        Integer score = Integer.valueOf(splited[4]);

        while (left < right) {
            int mid = (left + right) / 2;
            if (scoreList.get(mid) >= score) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return scoreList.size() - left;
    }

    @Test
    void test() {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};

        int[] res = solution(info, query);
        System.out.println(Arrays.toString(res));
    }
}
