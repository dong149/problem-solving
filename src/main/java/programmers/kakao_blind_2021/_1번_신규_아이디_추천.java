package programmers.kakao_blind_2021;


import org.junit.jupiter.api.Test;

public class _1번_신규_아이디_추천 {
    public String solution(String new_id) {
        new_id = new_id.toLowerCase()
                .replaceAll("[^a-z0-9\\-_.]", "")
                .replaceAll("\\.{2,}", ".")
                .replaceAll("^[.]|[.]$", "");
        if (new_id.isEmpty()) new_id += "a";
        if (new_id.length() >= 16) new_id = new_id.substring(0, 15).replaceAll("[.]$", "");
        if (new_id.length() <= 2) {
            char add = new_id.charAt(new_id.length() - 1);
            StringBuilder sb = new StringBuilder(new_id);
            while (sb.length() < 3) sb.append(add);
            new_id = sb.toString();
        }

        return new_id;
    }

    @Test
    void test() {
        solution("...!@BaT#*..y.abcdefghijklm");
    }
}
