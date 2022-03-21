package programmers.kakao_blind_2020;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class _6번_외벽_점검 {
    int[] spreadedWeak;
    int[] weak;
    int[] dist;
    int answer = Integer.MAX_VALUE;

    public int solution(int n, int[] weak, int[] dist) {
        this.weak = weak;
        this.dist = dist;

        spreadedWeak = new int[weak.length * 2];
        for (int i = 0; i < spreadedWeak.length; i++) {
            if (i < weak.length) spreadedWeak[i] = weak[i];
            else spreadedWeak[i] = weak[i - weak.length] + n;
        }

        for (int i = 1; i <= dist.length; i++) {
            permutation(i, new ArrayList<>(), new boolean[dist.length]);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private void permutation(int size, List<Integer> friends, boolean[] visited) {
        if (answer != Integer.MAX_VALUE)
            return;

        if (friends.size() == size) {
            check(friends);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            friends.add(dist[i]);
            permutation(size, friends, visited);
            friends.remove(friends.size() - 1);
            visited[i] = false;
        }
    }

    private void check(List<Integer> friends) {
        for (int i = 0; i < weak.length; i++) {
            int start = i;
            boolean flag = true;

            for (int idx = 0; idx < friends.size(); idx++) {
                for (int j = i; j < i + weak.length; j++) {
                    if (spreadedWeak[j] - spreadedWeak[start] > friends.get(idx)) {
                        start = j;
                        idx++;

                        if (idx == friends.size()) {
                            flag = false;
                            break;
                        }
                    }
                }

                if (flag) {
                    answer = friends.size();
                    return;
                }
            }
        }
    }

    @Test
    void test() {
        int res = solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4});
        System.out.println(res);
    }
}
