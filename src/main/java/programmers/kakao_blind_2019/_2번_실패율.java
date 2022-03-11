package programmers.kakao_blind_2019;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class _2번_실패율 {
    public int[] solution(int N, int[] stages) {
        int[] count = new int[N + 1];
        int[] success = new int[N + 1];

        for (int stage : stages) {
            for (int i = 1; i <= stage; i++) {
                if (i > N) break;
                count[i]++;
                if (i == stage) continue;
                success[i]++;
            }
        }

        PriorityQueue<Stage> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            pq.add(new Stage(i, count[i], success[i]));
        }

        List<Integer> answer = new ArrayList<>();
        while (!pq.isEmpty()) {
            answer.add(pq.poll().num);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    public static class Stage implements Comparable<Stage> {
        int num;
        double failure;

        public Stage(int num, int count, int success) {
            this.num = num;
            if (count == 0) {
                this.failure = 0;
            } else {
                int fail = count - success;
                this.failure = (1.0) * fail / count;
            }
        }

        @Override
        public int compareTo(Stage stage) {
            int res = Double.compare(stage.failure, this.failure);
            return res == 0 ? Integer.compare(this.num, stage.num) : res;
        }
    }
}
