package programmers.dev_matching_2021.first_half;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _2번_행렬_테두리_회전하기 {
    int[][] board;
    int rows, columns;

    public int[] solution(int rows, int columns, int[][] queries) {
        this.rows = rows;
        this.columns = columns;
        List<Integer> answer = new ArrayList<>();
        initBoard();

        for (int[] query : queries) {
            Point leftTop = new Point(query[0] - 1, query[1] - 1);
            Point rightBottom = new Point(query[2] - 1, query[3] - 1);
            answer.add(rotateAndGetMin(leftTop, rightBottom));
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private void initBoard() {
        board = new int[rows][columns];
        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = num++;
            }
        }
    }

    private int rotateAndGetMin(Point leftTop, Point rightBottom) {
        int[][] newBoard = new int[rows][columns];
        int min = Integer.MAX_VALUE;
        for (int i = leftTop.y + 1; i <= rightBottom.y; i++) {
            newBoard[leftTop.x][i] = board[leftTop.x][i - 1];
        }
        for (int i = leftTop.x + 1; i <= rightBottom.x; i++) {
            newBoard[i][rightBottom.y] = board[i - 1][rightBottom.y];
        }
        for (int i = rightBottom.y - 1; i >= leftTop.y; i--) {
            newBoard[rightBottom.x][i] = board[rightBottom.x][i + 1];
        }
        for (int i = rightBottom.x - 1; i >= leftTop.x; i--) {
            newBoard[i][leftTop.y] = board[i + 1][leftTop.y];
        }

        for (int i = leftTop.x; i <= rightBottom.x; i++) {
            for (int j = leftTop.y; j <= rightBottom.y; j++) {
                if (newBoard[i][j] == 0) continue;
                board[i][j] = newBoard[i][j];
                min = Math.min(newBoard[i][j], min);
            }
        }

        return min;
    }

    private void printArr(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println();
    }

    @Test
    void test() {
        int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        solution(6, 6, queries);
    }
}
