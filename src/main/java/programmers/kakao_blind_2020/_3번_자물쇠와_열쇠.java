package programmers.kakao_blind_2020;

public class _3번_자물쇠와_열쇠 {
    public boolean solution(int[][] key, int[][] lock) {
        int extendedLockLength = lock.length + key.length * 2 - 2;
        int[][] extendedLock = new int[extendedLockLength][extendedLockLength];
        int needKey = 0;

        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                extendedLock[key.length - 1 + i][key.length - 1 + j] = lock[i][j];
                if (lock[i][j] == 0) needKey++;
            }
        }

        for (int k = 0; k < 4; k++) {
            int[][] rotatedExtendedLock = getRotatedLock(extendedLock);
            for (int i = 0; i <= rotatedExtendedLock.length - key.length; i++) {
                for (int j = 0; j <= rotatedExtendedLock.length - key.length; j++) {
                    int count = 0;
                    Outer:
                    for (int m = 0; m < key.length; m++) {
                        for (int n = 0; n < key.length; n++) {
                            int curX = i + m;
                            int curY = j + n;
                            if (curX >= key.length - 1
                                    && curX <= extendedLockLength - key.length
                                    && curY >= key.length - 1
                                    && curY <= extendedLockLength - key.length) {
                                if (key[m][n] == rotatedExtendedLock[curX][curY]) {
                                    break Outer;
                                }
                                if (rotatedExtendedLock[curX][curY] == 0) {
                                    count++;
                                }
                            }
                        }
                    }
                    if (count == needKey) return true;
                }
            }
            extendedLock = rotatedExtendedLock;
        }

        return false;
    }

    private int[][] getRotatedLock(int[][] lock) {
        int[][] rotated = new int[lock.length][lock.length];
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                rotated[i][j] = lock[j][lock.length - i - 1];
            }
        }
        return rotated;
    }
}
