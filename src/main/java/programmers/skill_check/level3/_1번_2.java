package programmers.skill_check.level3;

import java.util.ArrayList;
import java.util.List;

public class _1번_2 {
    List<int[]> list = new ArrayList<>();

    public int[][] solution(int n) {
        move(1,2,3,n);
        int[][] answer = new int[list.size()][2];
        for(int i=0;i<list.size();i++) {
            answer[i][0] = list.get(i)[0];
            answer[i][1] = list.get(i)[1];
        }
        return answer;
    }

    private void move(int start, int mid, int end, int cnt) {
        if (cnt == 1) {
            list.add(new int[]{start, end});
            return;
        }
        move(start, end, mid, cnt - 1);
        move(start, mid, end, 1);
        move(mid, start, end, cnt - 1);
    }
}
