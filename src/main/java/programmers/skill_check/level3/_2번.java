package programmers.skill_check.level3;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _2번 {
    PriorityQueue<Krew> bus = new PriorityQueue<>(Comparator.comparing(Krew::getArriveTime).reversed());
    PriorityQueue<Krew> wait = new PriorityQueue<>(Comparator.comparing(Krew::getArriveTime));

    public String solution(int n, int t, int m, String[] timetable) {
        for (int i = 0; i < timetable.length; i++) {
            Krew krew = new Krew(timetable[i]);
            wait.add(krew);
        }

        int busTime = 9 * 60;
        int busArrivedCount = 1;
        // bus 대기하는 사람 있으면
        while (true) {
            // 가능한 크루 모두 추가
            while(!wait.isEmpty()) {
                Krew krew = wait.peek();
                boolean flag = krew != null && krew.arriveTime <= busTime && bus.size() < m;
                if(flag) {
                    bus.add(wait.poll());
                }else{
                    break;
                }
            }
            // 마지막이면
            if(bus.size() == m && busArrivedCount == n) {
                return convertToString(bus.poll().arriveTime-1);
            }

            // bus 널널하면 그냥 마지막 버스시간
            if(bus.size() < m && busArrivedCount == n) {
                return convertToString(busTime);
            }

            busArrivedCount++;
            busTime += t;
            bus.clear();
        }
    }

    private String convertToString(int time) {
        int hours = time / 60;
        int mins = time - hours * 60;
        return String.format("%02d:%02d", hours, mins);
    }

    public static class Krew {
        int arriveTime;

        public int getArriveTime() {
            return arriveTime;
        }

        public Krew(String time) {
            String[] hourAndMin = time.split(":");
            this.arriveTime = Integer.parseInt(hourAndMin[0]) * 60 + Integer.parseInt(hourAndMin[1]);
        }
    }

    @Test
    void test(){
        solution(2,10,2,new String[]{"09:10", "09:09", "08:00"});
    }
}
