package baekjoon;

import java.util.*;

public class _2331 {
    static int p;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        p = sc.nextInt();

        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        while (true) {
            if (set.contains(a)) {
                break;
            }
            set.add(a);
            list.add(a);
            a = getNext(a);
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == a) {
                System.out.println(i);
                break;
            }
        }
    }

    private static int getNext(int before) {
        String st = String.valueOf(before);
        int res = 0;
        for (int i = 0; i < st.length(); i++) {
            res += Math.pow(st.charAt(i) - '0', p);
        }
        return res;
    }
}
