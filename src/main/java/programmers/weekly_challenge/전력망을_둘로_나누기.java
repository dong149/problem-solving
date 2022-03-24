package programmers.weekly_challenge;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class 전력망을_둘로_나누기 {
    List<List<Integer>> map = new ArrayList<>();
    int n;
    int min = 100;

    public int solution(int n, int[][] wires) {
        this.n = n;
        for (int i = 0; i < n + 1; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] wire : wires) {
            map.get(wire[0]).add(wire[1]);
            map.get(wire[1]).add(wire[0]);
        }

        for (int[] wire : wires) {
            boolean[] visited = new boolean[n + 1];
            int diff = Math.abs(
                    getTopCount(wire[0], 0, visited, wire) -
                            getTopCount(wire[1], 0, visited, wire));
            min = Math.min(min, diff);
        }
        return min;
    }

    private int getTopCount(int cur, int count, boolean[] visited, int[] wire) {
        count++;
        visited[cur] = true;
        for (int i = 0; i < map.get(cur).size(); i++) {
            int goal = map.get(cur).get(i);
            if ((cur == wire[0] && goal == wire[1]) || cur == wire[1] && goal == wire[0]) continue;
            if (visited[goal]) continue;
            count = getTopCount(goal, count, visited, wire);
        }
        return count;
    }

    @Test
    void test() {
        solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}});
    }
}
