package programmers.practice_set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 나누어_떨어지는_숫자_배열 {
    public int[] solution(int[] arr, int divisor) {
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % divisor == 0) {
                answer.add(arr[i]);
            }
        }

        if (answer.size() == 0) {
            answer.add(-1);
        }

        Collections.sort(answer);

        return answer.stream().mapToInt(i -> i).toArray();
    }

}
