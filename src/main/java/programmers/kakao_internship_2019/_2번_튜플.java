package programmers.kakao_internship_2019;

import java.util.*;

public class _2번_튜플 {
    public int[] solution(String s) {
        String[] sets = s.replaceAll("[{][{]", "")
                .replaceAll("[}][}]", "")
                .replaceAll("[}],[{]", " ")
                .split(" ");

        Map<Integer, Integer> map = new HashMap<>();
        for (String set : sets) {
            String[] ints = set.split(",");
            for (int i = 0; i < ints.length; i++) {
                int putInt = Integer.parseInt(ints[i]);
                map.put(putInt, map.getOrDefault(putInt, 0) + 1);
            }
        }

        List<Integer> tuple = new ArrayList<>(map.keySet());
        return tuple.stream()
                .sorted(Comparator.comparing(map::get).reversed())
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
