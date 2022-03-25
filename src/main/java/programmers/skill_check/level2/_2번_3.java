package programmers.skill_check.level2;

import org.junit.jupiter.api.Test;

public class _2번_3 {
    final int MOD = 1234567;
    int[] cache = new int[100001];

    public int solution(int n) {
        return fibo(n);
    }

    private int fibo(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (cache[n] > 0) {
            return cache[n];
        }
        return cache[n] = (fibo(n - 2) % MOD + fibo(n - 1) % MOD) % MOD;
    }

    @Test
    void test() {
        solution(100000);
    }
}
