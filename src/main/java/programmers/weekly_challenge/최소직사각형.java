package programmers.weekly_challenge;

public class 최소직사각형 {
    public int solution(int[][] sizes) {
        int len = sizes.length;
        int width = 0;
        int height = 0;

        for (int i = 0; i < len; i++) {
            width = Math.max(width, Math.max(sizes[i][0], sizes[i][1]));
            height = Math.max(height, Math.min(sizes[i][0], sizes[i][1]));
        }

        return width * height;
    }
}
