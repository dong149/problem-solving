package programmers.kakao_blind_2022;

public class _4번_양궁_대회 {
    final int LEN = 11;
    int maxScore = 0;
    int[] answer = new int[LEN];

    public int[] solution(int n, int[] info) {
        int[] rian = new int[LEN];
        dfs(info, rian, LEN - 1, n);

        if (maxScore == 0) {
            return new int[]{-1};
        } else {
            return answer;
        }
    }

    private void dfs(int[] apeach, int[] ryan, int idx, int arrow) {
        if (idx < 0 || arrow == 0) {
            int res = calc(apeach, ryan);
            if (res > maxScore) {
                maxScore = res;
                if (arrow > 0) {
                    ryan[LEN - 1] += arrow;
                }
                answer = ryan.clone();
            }
            return;
        }
        // val 혹은 0가 들어가야 된다.
        int val = apeach[idx] + 1;
        ryan[idx] = val;
        if (arrow >= val) {
            dfs(apeach, ryan, idx - 1, arrow - val);
        }
        ryan[idx] = 0;
        dfs(apeach, ryan, idx - 1, arrow);
    }

    // 계산
    private int calc(int[] apeach, int[] ryan) {
        int apeechScore = 0;
        int lionScore = 0;
        for (int i = 0; i < LEN; i++) {
            if (apeach[i] != 0 && apeach[i] >= ryan[i]) {
                apeechScore += 10 - i;
            } else if (apeach[i] < ryan[i]) {
                lionScore += 10 - i;
            }
        }

        return lionScore - apeechScore;
    }
}
