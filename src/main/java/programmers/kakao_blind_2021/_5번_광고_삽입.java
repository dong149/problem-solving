package programmers.kakao_blind_2021;

import org.junit.jupiter.api.Test;

public class _5번_광고_삽입 {
    int[] timeArr;
    int[] accumTimeArr;

    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = getMinutesFromTime(play_time);
        int advTime = getMinutesFromTime(adv_time);

        this.timeArr = new int[playTime + 1];
        this.accumTimeArr = new int[playTime + 1];

        for (String log : logs) {
            int[] startAndEnd = getStartAndEndFromLog(log);
            timeArr[startAndEnd[0]]++;
            timeArr[startAndEnd[1]]--;
        }

        accumTimeArr[0] = timeArr[0];
        for (int i = 1; i < timeArr.length; i++) {
            accumTimeArr[i] = timeArr[i] + accumTimeArr[i - 1];
        }

        long maxTime = 0;
        for (int i = 0; i <= advTime; i++) {
            maxTime += accumTimeArr[i];
        }

        long curTime = maxTime;
        int answerTime = 0;
        for (int i = 1; i <= playTime - advTime + 1; i++) {
            curTime -= accumTimeArr[i - 1];
            curTime += accumTimeArr[i + advTime - 1];
            if (curTime > maxTime) {
                maxTime = curTime;
                answerTime = i;
            }
        }

        return getTimeFromMinutes(answerTime);
    }

    private int[] getStartAndEndFromLog(String log) {
        String[] startAndEnd = log.split("-");
        return new int[]{
                getMinutesFromTime(startAndEnd[0]),
                getMinutesFromTime(startAndEnd[1])};
    }

    private int getMinutesFromTime(String time) {
        String[] times = time.split(":");
        return Integer.parseInt(times[0]) * 3600 +
                Integer.parseInt(times[1]) * 60 +
                Integer.parseInt(times[2]);
    }

    private String getTimeFromMinutes(int time) {
        int hour = time / 3600;
        int min = (time - 3600 * hour) / 60;
        int second = (time - 3600 * hour - 60 * min);

        return String.format("%02d:%02d:%02d", hour, min, second);
    }

    @Test
    void test() {
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
        System.out.println(solution(play_time, adv_time, logs));
    }
}
