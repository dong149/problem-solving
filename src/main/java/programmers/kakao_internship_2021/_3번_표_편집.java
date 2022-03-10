package programmers.kakao_internship_2021;

import java.util.Stack;

public class _3번_표_편집 {
    public String solution(int n, int k, String[] cmd) {
        Stack<Integer> removed = new Stack<>();
        int arrSize = n;
        for (int i = 0; i < cmd.length; i++) {
            Command command = getCommand(cmd[i]);
            switch(command.cmd) {
                case 'U':
                    k -= command.move;
                    break;
                case 'D':
                    k += command.move;
                    break;
                case 'C':
                    removed.add(k);
                    arrSize--;
                    // last
                    if(k > arrSize-1) k--;
                    break;
                case 'Z':
                    int z = removed.pop();
                    // 복구하는 인덱스가 현재보다 앞설때
                    if(z<=k) k++;
                    arrSize++;
                    break;
                default:
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<arrSize;i++) {
            sb.append('O');
        }
        while(!removed.isEmpty()) {
            int z = removed.pop();
            sb.insert(z,'X');
        }

        return sb.toString();
    }

    private Command getCommand(String command) {
        char cmd = command.charAt(0);
        int move = 0;
        if (command.length() > 1) {
            move = Integer.parseInt(command.substring(2));
        }

        return new Command(cmd, move);
    }

    public static class Command {
        char cmd;
        int move;

        public Command(char cmd, int move) {
            this.cmd = cmd;
            this.move = move;
        }
    }
}
