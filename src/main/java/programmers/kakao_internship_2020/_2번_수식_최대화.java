package programmers.kakao_internship_2020;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class _2번_수식_최대화 {
    final String[] OPERATOR_CASE = {"+*-", "+-*", "*-+", "*+-", "-+*", "-*+"};

    public long solution(String expression) {
        List<Long> nums = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        long answer = 0;

        StringTokenizer st = new StringTokenizer(expression, "+*-", true);

        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.length() == 1 && token.matches("[\\+\\*-]")) {
                operators.add(token.charAt(0));
            } else {
                nums.add(Long.parseLong(token));
            }
        }

        for (int i = 0; i < OPERATOR_CASE.length; i++) {
            List<Long> numsCopy = new ArrayList<>(nums);
            List<Character> operatorsCopy = new ArrayList<>(operators);
            for (int j = 0; j < 3; j++) {
                solve(OPERATOR_CASE[i].charAt(j), numsCopy, operatorsCopy);
            }
            answer = Math.max(Math.abs(numsCopy.get(0)), answer);
        }

        return answer;
    }

    private void solve(char operator, List<Long> nums, List<Character> operators) {
        for (int i = 0; i < operators.size(); i++) {
            if (operator == operators.get(i)) {
                switch (operator) {
                    case '+':
                        nums.set(i, nums.get(i) + nums.get(i + 1));
                        break;
                    case '*':
                        nums.set(i, nums.get(i) * nums.get(i + 1));
                        break;
                    case '-':
                        nums.set(i, nums.get(i) - nums.get(i + 1));
                        break;
                }
                nums.remove(i + 1);
                operators.remove(i--);
            }
        }
    }

    @Test
    void test() {
        String expression = "100-200*300-500+20";

        assertThat(solution(expression)).isEqualTo(60420);
    }
}
