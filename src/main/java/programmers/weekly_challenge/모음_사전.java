package programmers.weekly_challenge;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

public class 모음_사전 {
    final String[] alpha = new String[]{"A","E","I","O","U"};
    PriorityQueue<String> dict = new PriorityQueue<>();
    int[] count = new int[5];
    public int solution(String word) {
        putAllDictionary(new StringBuilder());
        int answer = 0;
        while(!dict.isEmpty()) {
            answer++;
            if(word.equals(dict.poll())) {
                break;
            }
        }
        return answer;
    }

    private void putAllDictionary(StringBuilder sb) {
        for(int i=0;i<alpha.length;i++) {
            if(count[i]>=5 | sb.length()>=5) continue;
            count[i]++;
            sb.append(alpha[i]);
            dict.add(sb.toString());
            putAllDictionary(sb);
            sb.deleteCharAt(sb.length()-1);
            count[i]--;
        }
    }

    @Test
    void test() {
        System.out.println(solution("AAAAE"));
    }
}
