package programmers.kakao_blind_2020;

import java.util.ArrayList;
import java.util.List;

public class _2번_괄호_변환 {
    final String EMPTY = "";
    final char LEFT = '(';
    final char RIGHT = ')';

    public String solution(String s) {
        if (isCorrect(s)) return s;
        return getAnswer(s);
    }

    private String getAnswer(String s) {
        // 1
        if (EMPTY.equals(s)) {
            return EMPTY;
        }
        // 2
        String[] uAndV = getUV(s);
        // 3
        if (isCorrect(uAndV[0])) {
            uAndV[1] = getAnswer(uAndV[1]);
            return uAndV[0] + uAndV[1];
        } else { // 4
            StringBuilder sb = new StringBuilder();
            sb.append(LEFT);
            sb.append(getAnswer(uAndV[1]));
            sb.append(RIGHT);
            sb.append(getReverse(uAndV[0].substring(1, uAndV[0].length() - 1)));
            return sb.toString();
        }
    }

    private String[] getUV(String s) {
        int left = 0;
        int right = 0;
        int i;
        for (i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (cur == LEFT) left++;
            else right++;

            if (left > 0 && left == right) {
                break;
            }
        }

        String u = s.substring(0, i + 1);
        String v;
        if (i + 1 >= s.length()) v = EMPTY;
        else v = s.substring(i + 1);

        return new String[]{u, v};
    }

    private boolean isCorrect(String s) {
        if (EMPTY.equals(s)) return true;

        List<Character> st = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (st.isEmpty()) st.add(cur);
            else {
                if (st.get(st.size() - 1) == LEFT && cur == RIGHT)
                    st.remove(st.size() - 1);
                else st.add(cur);
            }
        }
        return st.isEmpty();
    }

    private String getReverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == LEFT) sb.append(RIGHT);
            else sb.append(LEFT);
        }
        return sb.toString();
    }
}
