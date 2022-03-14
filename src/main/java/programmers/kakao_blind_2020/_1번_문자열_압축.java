package programmers.kakao_blind_2020;

import java.util.Stack;

public class _1번_문자열_압축 {
    public int solution(String s) {
        int answer = s.length();
        for (int i = 1; i <= s.length(); i++) {
            answer = Math.min(answer, getCompressedLength(s, i));
        }
        return answer;
    }

    private int getCompressedLength(String s, int unit) {
        StringBuilder sb = new StringBuilder();
        Stack<String> st = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (idx + unit > s.length()) {
                st.add(s.substring(idx));
            } else {
                st.add(s.substring(idx, idx + unit));
            }
            idx += unit;
        }

        String before = st.pop();
        int count = 1;
        while (!st.isEmpty()) {
            String cur = st.pop();
            if (cur.equals(before)) {
                count++;
            } else {
                if (count > 1) sb.append(count);
                sb.append(before);
                count = 1;
                before = cur;
            }
        }
        if (count > 1) sb.append(count);
        sb.append(before);
        return sb.length();
    }
}
