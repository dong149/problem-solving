package programmers.weekly_challenge;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class 교점에_별_만들기 {
    long minX = Long.MAX_VALUE;
    long maxX = Long.MIN_VALUE;
    long minY = Long.MAX_VALUE;
    long maxY = Long.MIN_VALUE;
    Set<Point> crossPos = new HashSet<>();

    public String[] solution(int[][] lines) {
        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                if (i == j) continue;
                getXY(lines[i], lines[j]);
            }
        }

        String[] answer = new String[(int) maxY - (int) minY + 1];
        int idx = 0;
        for (long i = maxY; i >= minY; i--) {
            StringBuilder sb = new StringBuilder();
            for (long j = minX; j <= maxX; j++) {
                if (crossPos.contains(new Point((int) j, (int) i))) {
                    sb.append("*");
                } else {
                    sb.append(".");
                }
            }
            answer[idx++] = sb.toString();
        }

        return answer;
    }

    private void getXY(int[] first, int[] second) {
        long upX = (long) first[1] * (long) second[2] - (long) first[2] * second[1];
        long downX = (long) first[0] * second[1] - (long) first[1] * second[0];

        long upY = (long) first[2] * second[0] - (long) first[0] * second[2];
        long downY = (long) first[0] * second[1] - (long) first[1] * second[0];

        if (downX == 0) return;
        if (upX % downX != 0 || upY % downY != 0) return;

        long x = upX / downX;
        long y = upY / downY;

        minX = Math.min(x, minX);
        minY = Math.min(y, minY);
        maxX = Math.max(x, maxX);
        maxY = Math.max(y, maxY);

        crossPos.add(new Point((int) x, (int) y));
    }
}
