package programmers.kakao_internship_2021;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _2번_거리두기_확인하기_2 {
    char[][] board = new char[5][5];
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public int[] solution(String[][] places) {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < places.length; i++) {
            for (int j = 0; j < places[0].length; j++) {
                for (int k = 0; k < places[0][0].length(); k++) {
                    board[j][k] = places[i][j].charAt(k);
                }
            }
            boolean flag = true;
            for (int j = 0; j < board.length; j++) {
                if (!flag) {
                    break;
                }
                for (int k = 0; k < board[0].length; k++) {
                    if (board[j][k] == 'P') {
                        // 확인
                        if (!checkDist(j, k, 0)) {
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if (flag) answer.add(1);
            else answer.add(0);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean checkDist(int x, int y, int move) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, move});

        boolean[][] visited = new boolean[5][5];

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curMove = cur[2];

            if (board[curX][curY] == 'P' && curMove > 0 && curMove <= 2) {
                return false;
            }
            visited[curX][curY] = true;

            for (int i = 0; i < 4; i++) {
                int nX = curX + dx[i];
                int nY = curY + dy[i];
                if (nX >= 0 && nX < 5 && nY >= 0 && nY < 5 && !visited[nX][nY] && board[nX][nY] != 'X') {
                    q.add(new int[]{nX, nY, curMove + 1});
                }
            }
        }

        return true;
    }
}
