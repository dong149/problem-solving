package programmers.kakao_internship_2021;

import java.util.LinkedList;
import java.util.Queue;

public class _2번_거리두기_확인하기 {
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    final char PLAYER = 'P';
    final char EMPTY = 'O';

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int idx = 0;

        for (String[] strings : places) {
            char[][] board = new char[5][5];
            for (int i = 0; i < strings.length; i++) {
                board[i] = strings[i].toCharArray();
            }

            answer[idx++] = check(board) ? 1 : 0;
        }
        return answer;
    }

    private boolean check(char[][] board) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == PLAYER && !bfs(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean bfs(char[][] board, int x, int y) {
        boolean[][] visited = new boolean[5][5];
        Queue<Entity> q = new LinkedList<>();
        q.add(new Entity(x, y, 0));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Entity cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if (nextX >= 0 &&
                        nextX < 5 &&
                        nextY >= 0 &&
                        nextY < 5 &&
                        !visited[nextX][nextY]) {
                    if (board[nextX][nextY] == EMPTY) {
                        if (cur.dist + 1 < 2) {
                            q.add(new Entity(nextX, nextY, cur.dist + 1));
                            visited[nextX][nextY] = true;
                        }
                    } else if (board[nextX][nextY] == PLAYER) {
                        int manDist = Math.abs(cur.x - nextX) + Math.abs(cur.y - nextY);
                        if (manDist <= 2) {
                            return false;
                        }
                    }
                }
            }
        }


        return true;
    }

    private class Entity {
        int x;
        int y;
        int dist;

        Entity(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
