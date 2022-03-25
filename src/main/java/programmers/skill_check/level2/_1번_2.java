package programmers.skill_check.level2;

import java.util.PriorityQueue;

public class _1번_2 {
    PriorityQueue<File> pq = new PriorityQueue<>();

    public String[] solution(String[] files) {
        int idx = 0;
        for (String file : files) {
            pq.add(new File(file,idx++));
        }

        String[] answer = new String[files.length];
        idx = 0;
        while (!pq.isEmpty()) {
            answer[idx++] = pq.poll().name;
        }
        return answer;
    }

    public static class File implements Comparable<File> {
        String head;
        int number;
        String name;
        int idx;

        public File(String name, int idx) {
            int numberStart = 1;
            int numberEnd = name.length();
            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) >= '0' && name.charAt(i) <= '9') {
                    numberStart = i;
                    break;
                }
            }
            for (int i = numberStart; i < name.length(); i++) {
                if (i - numberStart >= 5 || !(name.charAt(i) >= '0' && name.charAt(i) <= '9')) {
                    numberEnd = i;
                    break;
                }
            }

            this.head = name.substring(0, numberStart);
            this.number = Integer.parseInt(name.substring(numberStart, numberEnd));
            this.name = name;
            this.idx = idx;
        }

        @Override
        public int compareTo(File o) {
            int headComp = this.head.toLowerCase().compareTo(o.head.toLowerCase());
            if (headComp == 0) {
                int numberComp = Integer.compare(this.number, o.number);
                if(numberComp == 0 ) {
                    return Integer.compare(this.idx, o.idx);
                }
                return numberComp;
            }
            return headComp;
        }
    }
}
