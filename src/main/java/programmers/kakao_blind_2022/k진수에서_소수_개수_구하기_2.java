package programmers.kakao_blind_2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// 다른 방식 풀이
public class k진수에서_소수_개수_구하기_2 {
    public int solution(int n, int k) {
        String s = Integer.toString(n, k);
        return (int) Arrays.stream(s.split("0"))
                .filter(p ->
                        !p.isEmpty()
                                && isPrime(Long.parseLong(p))
                                && (s.contains("0${p}0")
                                || s.contains("${p}0")
                                || s.contains("0${p}")
                                || s.contains(p)))
                .count();
    }

    private boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void test() {
        int res = solution(437674, 3);
        assertThat(res).isEqualTo(3);
    }
}
