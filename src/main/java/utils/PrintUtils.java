package utils;

import java.util.Arrays;

/**
 * 출력 관련 Util
 */
public final class PrintUtils {
    public static void printArr(int[] board) {
        System.out.println(Arrays.toString(board));
    }

    public static void printArr(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println();
    }

    public static void printArr(boolean[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println();
    }
}
