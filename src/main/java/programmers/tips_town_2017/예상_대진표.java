package programmers.tips_town_2017;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class 예상_대진표 {
    public int solution(int n, int a, int b) {
        int round = 1;
        while (true) {
            if (Math.abs(a - b) == 1 && Math.max(a, b) % 2 == 0) {
                return round;
            }
            a = getDivided(a);
            b = getDivided(b);
            round++;
        }
    }

    private int getDivided(int num) {
        if (num % 2 != 0) {
            return (num + 1) / 2;
        } else {
            return num / 2;
        }
    }

    @Test
    void test() {
        assertThat(solution(17, 4, 5)).isEqualTo(3);
    }
}
