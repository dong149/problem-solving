package programmers.dev_matching_2021.first_half;

import java.util.HashMap;
import java.util.Map;

public class _3번_다단계_칫솔_판매 {
    Map<String, String> relation = new HashMap<>();
    Map<String, Integer> amountInfo = new HashMap<>();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for (int i = 0; i < referral.length; i++) {
            String recommendName = referral[i];
            if (recommendName.equals("-")) continue;
            String referencedName = enroll[i];
            relation.put(referencedName, recommendName);
        }

        for (int i = 0; i < seller.length; i++) {
            putAmount(seller[i], amount[i] * 100);
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = amountInfo.getOrDefault(enroll[i], 0);
        }

        return answer;
    }

    private void putAmount(String name, int amount) {
        int remainAmount = (int) (amount * 1.0 / 10);
        int putAmount = amount - remainAmount;

        amountInfo.put(name, amountInfo.getOrDefault(name, 0) + putAmount);
        if (relation.containsKey(name)) {
            if (remainAmount == 0) return;
            putAmount(relation.get(name), remainAmount);
        }
    }
}
