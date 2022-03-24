package programmers.weekly_challenge;

import org.junit.jupiter.api.Test;

public class 피로도 {
    int[][] dungeons;
    int max = 0;

    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;

        for (int i = 0; i < dungeons.length; i++) {
            if (dungeons[i][0] > k) continue;
            boolean[] visited = new boolean[dungeons.length];
            visited[i] = true;
            allScan(k - dungeons[i][1], 1, visited);
        }
        return max;
    }

    private void allScan(int k, int count, boolean[] visited) {
        max = Math.max(count, max);
        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i] | dungeons[i][0] > k) continue;
            visited[i] = true;
            allScan(k - dungeons[i][1], count + 1, visited);
            visited[i] = false;
        }
    }

    @Test
    void test() {
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        solution(k, dungeons);
    }
}
