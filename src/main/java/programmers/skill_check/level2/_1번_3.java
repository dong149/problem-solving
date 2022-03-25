package programmers.skill_check.level2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class _1번_3 {

    Set<Road> set = new HashSet<>();
    int[] curPos = {5, 5};
    int answer = 0;

    public int solution(String dirs) {
        for (int i = 0; i < dirs.length(); i++) {
            char cur = dirs.charAt(i);
            move(cur);
        }

        return answer;
    }

    private void move(char command) {
        int curX = curPos[0];
        int curY = curPos[1];
        int nX, nY;
        if (command == 'U') {
            nX = curX;
            nY = curY - 1;
        } else if (command == 'R') {
            nX = curX + 1;
            nY = curY;
        } else if (command == 'L') {
            nX = curX - 1;
            nY = curY;
        } else {
            nX = curX;
            nY = curY + 1;
        }
        if (!(nX >= 0 && nY >= 0 && nX <= 10 && nY <= 10)) {
            return;
        }
        if (!set.contains(new Road(curX, curY, nX, nY))) {
            set.add(new Road(curX, curY, nX, nY));
            set.add(new Road(nX, nY, curX, curY));
            answer++;
        }
        curPos[0] = nX;
        curPos[1] = nY;
    }

    public static class Road {
        int x;
        int y;
        int nX;
        int nY;

        public Road(int x, int y, int nX, int nY) {
            this.x = x;
            this.y = y;
            this.nX = nX;
            this.nY = nY;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Road road = (Road) o;
            return x == road.x && y == road.y && nX == road.nX && nY == road.nY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, nX, nY);
        }
    }
}
