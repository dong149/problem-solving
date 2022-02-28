package programmers.kakao_blind_2022;

public class k진수에서_소수_개수_구하기 {
    public static void main(String[] args) {
        int n = 23;
        int k = 10;

        System.out.print(solution(n, k));
    }

    public static int solution(int n, int k) {
        int answer = 0;

        String kStr = toKnumber(n, k);
        int len = kStr.length();

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                String temp;
                if (i == 0 && j == len - 1) {
                    temp = kStr.substring(i, j + 1);
                } else if (j - i > 1 && kStr.charAt(i) == '0' && kStr.charAt(j) == '0') {
                    temp = kStr.substring(i + 1, j);
                } else if (j - i > 0 && kStr.charAt(i) == '0' && j == len - 1) {
                    temp = kStr.substring(i + 1, j + 1);
                } else if (j - i > 0 && i == 0 && kStr.charAt(j) == '0') {
                    temp = kStr.substring(i, j);
                } else {
                    continue;
                }
                if (isPrime(temp)) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private static String toKnumber(int n, int k) {
        StringBuilder answer = new StringBuilder();

        while (n != 0) {
            answer.append(n % k);
            n /= k;
        }
        return answer.reverse().toString();
    }

    private static boolean isPrime(String s) {
        if (s.contains("0") || s.equals("")) {
            return false;
        }

        long n = Long.parseLong(s);

        if (n < 2) {
            return false;
        }

        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
