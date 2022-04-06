package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class _1058 {
    static char[][] board;
    static int n;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        for (int i = 0; i < n; i++) {
            bfs(i);
        }
        System.out.println(answer);
    }

    private static void bfs(int start) {
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        q.add(new int[]{start, 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] != start && cur[1] <= 2) {
                set.add(cur[0]);
            }
            if (cur[1] >= 2) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                if (board[cur[0]][i] == 'Y' && i != start) {
                    q.add(new int[]{i, cur[1] + 1});
                }
            }
        }
        answer = Math.max(answer, set.size());
    }
}
