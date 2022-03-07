package programmers.kakao_internship_2020;

public class _4번_경주로_건설 {
    // 우, 하 , 좌, 상
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int n;
    int minCost = Integer.MAX_VALUE;
    int[][] board;
    int[][][] minCostBoard;

    public int solution(int[][] board) {
        this.n = board.length;
        this.board = board;
        minCostBoard = new int[n][n][4];

        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        dfs(0, 0, 0, 0, visited);

        visited = new boolean[n][n];
        visited[0][0] = true;
        dfs(0, 0, 0, 1, visited);

        return minCost;
    }

    private void dfs(
            int x,
            int y,
            int cost,
            int prevDir,
            boolean[][] visited) {

        if (minCostBoard[x][y][prevDir] != 0) {
            if (minCostBoard[x][y][prevDir] > cost) {
                minCostBoard[x][y][prevDir] = cost;
            } else {
                return;
            }
        } else {
            minCostBoard[x][y][prevDir] = cost;
        }

        if (x == n - 1 && y == n - 1) {
            minCost = Math.min(minCost, cost);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX >= 0 &&
                    nextX < n &&
                    nextY >= 0 &&
                    nextY < n &&
                    !visited[nextX][nextY] &&
                    board[nextX][nextY] == 0) {

                if (prevDir != i) {
                    visited[nextX][nextY] = true;
                    dfs(nextX, nextY, cost + 600, i, visited);
                    visited[nextX][nextY] = false;
                } else {
                    visited[nextX][nextY] = true;
                    dfs(nextX, nextY, cost + 100, i, visited);
                    visited[nextX][nextY] = false;
                }
            }
        }
    }
}
