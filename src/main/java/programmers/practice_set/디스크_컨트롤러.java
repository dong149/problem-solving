package programmers.practice_set;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 디스크_컨트롤러 {
    PriorityQueue<Job> waitQ = new PriorityQueue<>(Comparator.comparingInt(s -> s.inputTime));
    PriorityQueue<Job> processQ = new PriorityQueue<>(Comparator.comparingInt(s -> s.processTime));
    int jobSize;
    int totalTime = 0;

    public int solution(int[][] jobs) {
        for (int[] job : jobs) {
            waitQ.add(new Job(job[0], job[1]));
        }
        jobSize = jobs.length;

        int curTime = 0;
        while (!waitQ.isEmpty() || !processQ.isEmpty()) {
            // 빼고 넣어줌.
            popWaitAndAddToProcess(curTime);
            // SJF 적용
            if (!processQ.isEmpty()) {
                Job ran = processQ.poll();
                curTime += ran.processTime;
                totalTime += curTime - ran.inputTime;
            } else {
                curTime++;
            }
        }

        return totalTime / jobSize;
    }

    private void popWaitAndAddToProcess(int curTime) {
        while (!waitQ.isEmpty()) {
            Job peek = waitQ.peek();
            if (peek.inputTime <= curTime) {
                processQ.add(waitQ.poll());
            } else {
                break;
            }
        }
    }

    public static class Job {
        int inputTime;
        int processTime;

        public Job(int inputTime, int processTime) {
            this.inputTime = inputTime;
            this.processTime = processTime;
        }
    }
}
