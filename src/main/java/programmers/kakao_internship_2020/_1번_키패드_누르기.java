package programmers.kakao_internship_2020;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class _1번_키패드_누르기 {
    final String LEFT = "L";
    final String RIGHT = "R";
    final int[][] NUM_POS = {{3, 1}, {0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};
    final List<Integer> ONLY_LEFT = Arrays.asList(1, 4, 7);
    final List<Integer> ONLY_RIGHT = Arrays.asList(3, 6, 9);
    boolean isLeftHand;
    int[] leftHand = {3, 0};
    int[] rightHand = {3, 2};
    StringBuilder sb = new StringBuilder();

    public String solution(int[] numbers, String hand) {
        isLeftHand = hand.equals("left");

        for (int number : numbers) {
            if (ONLY_LEFT.contains(number)) {
                push(number, LEFT);
            } else if (ONLY_RIGHT.contains(number)) {
                push(number, RIGHT);
            } else {
                int leftDistance = getDistance(leftHand, NUM_POS[number]);
                int rightDistance = getDistance(rightHand, NUM_POS[number]);

                if (leftDistance > rightDistance) {
                    push(number, RIGHT);
                } else if (leftDistance == rightDistance) {
                    if (isLeftHand) {
                        push(number, LEFT);
                    } else {
                        push(number, RIGHT);
                    }
                } else {
                    push(number, LEFT);
                }
            }
        }

        return sb.toString();
    }

    private int getDistance(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }

    private void push(int number, String dir) {
        if (dir.equals(LEFT)) {
            leftHand = NUM_POS[number];
            sb.append(LEFT);
        } else {
            rightHand = NUM_POS[number];
            sb.append(RIGHT);
        }
    }

    @Test
    void test() {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String expectedResult = "LRLLLRLLRRL";

        assertThat(solution(numbers, "right")).isEqualTo(expectedResult);
    }
}
