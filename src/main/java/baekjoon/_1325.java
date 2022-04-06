package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1325 {
    static int n, m;
    static List<List<Integer>> board = new ArrayList<>();
    static int[] rank;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nAndM = br.readLine();
        n = Integer.parseInt(nAndM.split(" ")[0]);
        m = Integer.parseInt(nAndM.split(" ")[1]);
        for (int i = 0; i <= n; i++) {
            board.add(new ArrayList<>());
        }

        rank = new int[n + 1];

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board.get(a).add(b);
        }

        for (int i = 1; i <= n; i++) {
            bfs(i);
        }

        for (int i = 1; i <= n; i++) {
            max = Math.max(rank[i], max);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (max == rank[i]) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] check = new boolean[n + 1];
        q.add(start);
        check[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : board.get(cur)) {
                if (!check[next]) {
                    check[next] = true;
                    rank[next]++;
                    q.add(next);
                }
            }
        }
    }
}
