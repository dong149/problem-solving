package programmers.kakao_internship_2019;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _4번_호텔_방배정 {
    Map<Long, Long> map = new HashMap<>();

    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        for (int i = 0; i < room_number.length; i++) {
            long curNumber = room_number[i];
            answer[i] = getNumber(curNumber);
        }
        return answer;
    }

    private long getNumber(long number) {
        if (!map.containsKey(number)) {
            map.put(number, number + 1);
            return number;
        }

        map.put(number, getNumber(map.get(number)));
        return map.get(number);
    }

    @Test
    void test() {
        long[] answer = solution(10L, new long[]{1, 3, 4, 1, 3, 1});
        System.out.println(Arrays.toString(answer));
    }
}
