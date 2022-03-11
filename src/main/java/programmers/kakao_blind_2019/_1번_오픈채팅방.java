package programmers.kakao_blind_2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _1번_오픈채팅방 {
    static final String ENTER = "Enter";
    static final String LEAVE = "Leave";
    static final String ENTER_FORMAT = "%s님이 들어왔습니다.";
    static final String LEAVE_FORMAT = "%s님이 나갔습니다.";

    public String[] solution(String[] record) {
        // 훑으면서 id 저장
        Map<String, String> map = new HashMap<>();
        for (String str : record) {
            String[] commands = str.split(" ");
            if (commands.length <= 2) continue;

            String id = commands[1];
            String name = commands[2];
            map.put(id, name);
        }

        // get answer
        List<String> answer = new ArrayList<>();
        for (String str : record) {
            String[] commands = str.split(" ");

            String cmd = commands[0];
            String id = commands[1];

            if (ENTER.equals(cmd)) {
                answer.add(String.format(ENTER_FORMAT, map.get(id)));
            } else if (LEAVE.equals(cmd)) {
                answer.add(String.format(LEAVE_FORMAT, map.get(id)));
            }
        }

        return answer.toArray(new String[]{});
    }
}
