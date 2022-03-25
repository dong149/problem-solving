package programmers.skill_check.level2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class _2번 {
    int one;
    int zero;
    public int[] solution(int[][] arr) {
        quadZip(arr);
        return new int[]{zero, one};
    }

    private void quadZip(int[][] arr) {
        int curLength = arr.length;
        if(curLength == 1) {
            if(arr[0][0] == 1) one++;
            else zero++;
            return;
        }
        int oneCount = 0;
        int zeroCount = 0;
        for(int i=0;i<curLength;i++) {
            for(int j=0;j<curLength;j++) {
                if(arr[i][j]==1) oneCount++;
                else zeroCount++;
            }
        }
        if(oneCount > 0 && zeroCount == 0) {
            quadZip(new int[][]{{1}});
            return;
        }else if(zeroCount > 0 && oneCount == 0) {
            quadZip(new int[][]{{0}});
            return;
        }
        int[][] leftTop = new int[curLength/2][curLength/2];
        for(int i=0;i<curLength/2;i++) {
            for(int j=0;j<curLength/2;j++) {
                leftTop[i][j] = arr[i][j];
            }
        }
        int[][] rightTop = new int[curLength/2][curLength/2];
        for(int i=curLength/2;i<curLength;i++) {
            for(int j=0;j<curLength/2;j++) {
                rightTop[i-curLength/2][j] = arr[i][j];
            }
        }
        int[][] leftBottom = new int[curLength/2][curLength/2];
        for(int i=0;i<curLength/2;i++) {
            for(int j=curLength/2;j<curLength;j++) {
                leftBottom[i][j-curLength/2] = arr[i][j];
            }
        }
        int[][] rightBottom = new int[curLength/2][curLength/2];
        for(int i=curLength/2;i<curLength;i++) {
            for(int j=curLength/2;j<curLength;j++) {
                rightBottom[i-curLength/2][j-curLength/2] = arr[i][j];
            }
        }

        quadZip(leftTop);
        quadZip(rightTop);
        quadZip(leftBottom);
        quadZip(rightBottom);
    }

    @Test
    void test() {
        int[][] arr = {{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}};
        System.out.println(Arrays.toString(solution(arr)));
    }
}
