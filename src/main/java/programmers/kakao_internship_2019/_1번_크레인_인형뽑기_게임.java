package programmers.kakao_internship_2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class _1번_크레인_인형뽑기_게임 {
    int[] count;
    int answer = 0;

    public int solution(int[][] board, int[] moves) {
        count = new int[board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    count[j]++;
                }
            }
        }

        List<Integer> basket = new ArrayList<>();
        for (int move : moves) {
            if (count[move - 1] == 0) continue;
            int item = board[move - 1][board.length - count[move - 1]];
            basket.add(item);
            count[move - 1]--;
            pop(basket);
        }

        return answer;
    }

    private void pop(List<Integer> basket) {
        while (true) {
            if (basket.size() >= 2 && (Objects.equals(basket.get(basket.size() - 1), basket.get(basket.size() - 2)))) {
                basket.remove(basket.size() - 1);
                basket.remove(basket.size() - 1);
                answer += 2;
            } else {
                return;
            }
        }
    }
}
