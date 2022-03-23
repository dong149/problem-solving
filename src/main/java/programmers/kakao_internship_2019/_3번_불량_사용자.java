package programmers.kakao_internship_2019;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _3번_불량_사용자 {
    Map<String, Integer> indexMap = new HashMap<>();
    String[] user_id, banned_id;
    Set<Integer> answerSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        for (int i = 0; i < banned_id.length; i++) {
            banned_id[i] = banned_id[i].replaceAll("[*]", ".");
        }
        this.user_id = user_id;
        this.banned_id = banned_id;
        for (int i = 0; i < user_id.length; i++) {
            indexMap.put(user_id[i], i);
        }

        solve(0, 0);

        return answerSet.size();
    }

    private void solve(int idx, int checked) {
        if (idx == banned_id.length) {
            answerSet.add(checked);
            return;
        }

        String bannedId = banned_id[idx];
        for (int i = 0; i < user_id.length; i++) {
            int userIdIdx = indexMap.get(user_id[i]);
            if (user_id[i].matches(bannedId) && (checked & (1 << userIdIdx)) != (1 << userIdIdx)) {
                checked |= 1 << userIdIdx;
                solve(idx + 1, checked);
                checked &= ~(1 << userIdIdx);
            }
        }
    }
}
