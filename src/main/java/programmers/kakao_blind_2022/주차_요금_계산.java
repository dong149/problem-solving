package programmers.kakao_blind_2022;

import java.util.HashMap;
import java.util.Map;

public class 주차_요금_계산 {

    Map<String, Integer> recordMap = new HashMap<>();
    Map<String, Integer> feeMap = new HashMap<>();
    static final int TIME_MAX = 23 * 60 + 59;

    public int[] solution(int[] fees, String[] records) {
        for (String record : records) {
            String[] splitRecord = record.split(" ");

            int time = toMinutes(splitRecord[0]);
            String num = splitRecord[1];

            if (recordMap.containsKey(num)) {
                Integer timeIn = recordMap.get(num);
                feeMap.put(num, feeMap.getOrDefault(num, 0) + (time - timeIn));
                recordMap.remove(num);
            } else {
                recordMap.put(num, time);
            }
        }

        for (String num : recordMap.keySet()) {
            feeMap.put(num, feeMap.getOrDefault(num, 0) + (TIME_MAX - recordMap.get(num)));
        }


        int defaultTime = fees[0];
        int defaultFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        for (String num : feeMap.keySet()) {
            int time = feeMap.get(num);

            if (time <= defaultTime) {
                feeMap.put(num, defaultFee);
            } else {
                int overTime = time - defaultTime;
                int units = overTime / unitTime;

                if (overTime % unitTime != 0) {
                    units++;
                }
                int fee = defaultFee + units * unitFee;
                feeMap.put(num, fee);
            }
        }

        return feeMap.keySet()
                .stream()
                .sorted()
                .mapToInt(key -> feeMap.get(key))
                .toArray();
    }

    private int toMinutes(String time) {
        String[] sArr = time.split(":");
        return Integer.parseInt(sArr[0]) * 60 + Integer.parseInt(sArr[1]);
    }
}
