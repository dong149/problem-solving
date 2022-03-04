package programmers.kakao_blind_2022;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _1번_신고_결과_받기 {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reportMap = new HashMap<>();
        Map<String, Integer> reportedCountMap = new HashMap<>();

        for (String s : report) {
            String[] idList = s.split(" ");
            String reportId = idList[0];
            String reportedId = idList[1];

            if (!reportMap.containsKey(reportId)) {
                reportMap.put(reportId, new HashSet<>());
            }

            Set<String> set = reportMap.get(reportId);

            if (!set.contains(reportedId)) {
                reportedCountMap.put(reportedId, reportedCountMap.getOrDefault(reportedId, 0) + 1);
                set.add(reportedId);
            }
        }

        int[] answer = new int[id_list.length];

        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];

            if (reportMap.containsKey(id)) {
                Set<String> set = reportMap.get(id);

                for (String s : set) {
                    if (reportedCountMap.get(s) >= k) {
                        answer[i]++;
                    }
                }
            }
        }

        return answer;
    }
}
