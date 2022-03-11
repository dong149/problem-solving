package programmers.kakao_blind_2019;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class _3번_후보키 {
    String[][] relation;
    Set<String> superKeySet = new HashSet<>();
    Set<String> compareSet;

    public int solution(String[][] relation) {
        this.relation = relation;
        findSuperKeySet(0, 0, new boolean[relation[0].length], new StringBuilder());
        int answer = 0;
        for (String keyA : superKeySet) {
            boolean isValid = true;
            for (String keyB : superKeySet) {
                if (!keyA.equals(keyB) && keyA.length() > keyB.length()) {
                    compareSet = new HashSet<>();
                    findCompareSet(keyA, 0, keyB.length(), new boolean[keyA.length()], new StringBuilder());
                    for (String compare : compareSet) {
                        if (keyB.contains(compare)) {
                            isValid = false;
                            break;
                        }
                    }
                }
                if (!isValid) break;
            }
            if (isValid) answer++;
        }
        return answer;
    }

    private void findSuperKeySet(int start, int dataSize, boolean[] isIncluded, StringBuilder sb) {
        for (int i = start; i < relation[0].length; i++) {
            isIncluded[i] = true;
            sb.append(i);
            if (validateCandidateKey(dataSize + 1, isIncluded)) {
                superKeySet.add(sb.toString());
            }
            findSuperKeySet(i + 1, dataSize + 1, isIncluded, sb);
            sb.deleteCharAt(sb.length() - 1);
            isIncluded[i] = false;
        }
    }

    private boolean validateCandidateKey(int dataSize, boolean[] isIncluded) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < relation.length; i++) {
            Object[] datas = new Object[dataSize];
            int idx = 0;
            for (int j = 0; j < isIncluded.length; j++) {
                if (isIncluded[j]) {
                    datas[idx++] = relation[i][j];
                }
            }
            int hash = Objects.hash(datas);
            if (set.contains(hash)) {
                return false;
            } else {
                set.add(hash);
            }
        }
        return true;
    }

    private void findCompareSet(String str, int start, int goalLength, boolean[] visited, StringBuilder sb) {
        for (int i = start; i < str.length(); i++) {
            visited[i] = true;
            sb.append(str.charAt(i));

            if (goalLength == sb.length()) {
                compareSet.add(sb.toString());
            }

            if (goalLength > sb.length()) {
                findCompareSet(str, start + 1, goalLength, visited, sb);
            }

            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }
}
