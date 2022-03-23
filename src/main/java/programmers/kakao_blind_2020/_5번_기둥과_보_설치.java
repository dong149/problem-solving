package programmers.kakao_blind_2020;

import org.junit.jupiter.api.Test;

public class _5번_기둥과_보_설치 {
    final int GIDUNG = 0;
    final int BO = 1;
    final int INSTALL = 1;
    int n;
    boolean[][] gidung;
    boolean[][] bo;

    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        gidung = new boolean[n + 1][n + 1];
        bo = new boolean[n + 1][n + 1];
        int count = 0;

        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int a = frame[2];
            int b = frame[3];

            if (INSTALL == b) {
                if (GIDUNG == a) {
                    if (isGidungPossible(x, y)) {
                        gidung[x][y] = true;
                        count++;
                    }
                } else {
                    if (isBoPossible(x, y)) {
                        bo[x][y] = true;
                        count++;
                    }
                }
            } else { // 삭제
                if (GIDUNG == a) {
                    gidung[x][y] = false;
                    if (!canDelete()) {
                        gidung[x][y] = true;
                    } else {
                        count--;
                    }
                } else {
                    bo[x][y] = false;
                    if (!canDelete()) {
                        bo[x][y] = true;
                    } else {
                        count--;
                    }
                }
            }
        }

        int[][] answer = new int[count][3];

        int idx = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (gidung[i][j]) {
                    answer[idx][0] = i;
                    answer[idx][1] = j;
                    answer[idx][2] = GIDUNG;
                    idx++;
                }
                if (bo[i][j]) {
                    answer[idx][0] = i;
                    answer[idx][1] = j;
                    answer[idx][2] = BO;
                    idx++;
                }
            }
        }
        return answer;
    }

    private boolean isGidungPossible(int x, int y) {
        return (y == 0 && !gidung[x][y])
                | (isPossiblePos(x, y - 1) && gidung[x][y - 1])
                | (isPossiblePos(x - 1, y) && bo[x - 1][y])
                | bo[x][y];
    }

    private boolean isBoPossible(int x, int y) {
        return (isPossiblePos(x - 1, y) && bo[x + 1][y] && bo[x - 1][y])
                || (isPossiblePos(x + 1, y - 1) && gidung[x + 1][y - 1])
                || (isPossiblePos(x, y - 1) && gidung[x][y - 1]);

    }

    private boolean canDelete() {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (gidung[i][j] && !isGidungPossible(i, j))
                    return false;
                if (bo[i][j] && !isBoPossible(i, j))
                    return false;
            }
        }
        return true;
    }

    private boolean isPossiblePos(int x, int y) {
        return x >= 0 && y >= 0 && x <= n && y <= n;
    }

    @Test
    void test() {
        int n = 5;
        int[][] build_frame = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
        solution(n, build_frame);
    }
}
