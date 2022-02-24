package programmers.etc;

public class 없는_숫자_더하기 {
    public int solution(int[] numbers) {
        int total = 45;

        for (int i : numbers) {
            total -= i;
        }

        return total;
    }
}
