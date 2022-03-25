package programmers.skill_check.level2;

import java.util.PriorityQueue;

public class _1번 {
    PriorityQueue<Music> pq = new PriorityQueue<>();

    public String solution(String m, String[] musicinfos) {
        for (int i = 0; i < musicinfos.length; i++) {
            Music music = new Music(musicinfos[i]);
            if (music.play.contains(replaceAllShop(m))){
                pq.add(music);
            }
        }

        if (pq.isEmpty()) return "(None)";
        else return pq.poll().name;
    }

    private static String replaceAllShop(String music) {
        return music.replaceAll("C#", "H")
                .replaceAll("D#", "I")
                .replaceAll("F#", "J")
                .replaceAll("G#", "K")
                .replaceAll("A#", "L");
    }

    public static class Music implements Comparable<Music> {
        int start;
        int end;
        int duration;
        String name;
        String play;

        public Music(String musicInfo) {
            String[] info = musicInfo.split(",");


            this.start = getMin(info[0]);
            this.end = getMin(info[1]);
            this.duration = this.end - this.start;
            this.name = info[2];
            this.play = convertToPlay(info[3], this.end - this.start);
        }

        private int getMin(String time) {
            String[] hourAndMin = time.split(":");
            return Integer.parseInt(hourAndMin[0]) * 60 + Integer.parseInt(hourAndMin[1]);
        }

        private String convertToPlay(String info, int duration) {
            info = replaceAllShop(info);
            StringBuilder sb = new StringBuilder();
            int idx = 0;
            for (int i = 0; i < duration; i++) {
                sb.append(info.charAt(idx++));
                if (idx >= info.length()) idx = 0;
            }
            return sb.toString();
        }

        @Override
        public int compareTo(Music o) {
            int durationComp = Integer.compare(o.duration, this.duration);
            if (durationComp == 0) {
                return Integer.compare(this.start, o.start);
            } else {
                return durationComp;
            }
        }
    }
}
