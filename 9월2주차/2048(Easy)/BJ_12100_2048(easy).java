import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] curMap;
    static int maxResult = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxResult = Math.max(maxResult, map[i][j]);
            }
        }

        curMap = new int[N][N];
        copyMap();
        start(0);
        System.out.println(maxResult);
    }

    static void start(int cnt) {
        if (cnt == 5) return;

        int[][] backup = new int[N][N];
        for (int i = 0; i < N; i++) backup[i] = curMap[i].clone();

        moveUp();
        start(cnt + 1);
        restore(backup);

        moveDown();
        start(cnt + 1);
        restore(backup);

        moveLeft();
        start(cnt + 1);
        restore(backup);

        moveRight();
        start(cnt + 1);
        restore(backup);
    }

    static void restore(int[][] backup) {
        for (int i = 0; i < N; i++) curMap[i] = backup[i].clone();
    }

    static void moveUp() {
        for (int j = 0; j < N; j++) {
            int[] temp = new int[N];
            int idx = 0, prev = 0;
            for (int i = 0; i < N; i++) {
                if (curMap[i][j] == 0) continue;
                if (prev == 0) {
                    prev = curMap[i][j];
                } else if (prev == curMap[i][j]) {
                    temp[idx++] = prev * 2;
                    maxResult = Math.max(maxResult, prev * 2);
                    prev = 0;
                } else {
                    temp[idx++] = prev;
                    prev = curMap[i][j];
                }
            }
            if (prev != 0) temp[idx] = prev;
            for (int i = 0; i < N; i++) curMap[i][j] = temp[i];
        }
    }

    static void moveDown() {
        for (int j = 0; j < N; j++) {
            int[] temp = new int[N];
            int idx = N - 1, prev = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (curMap[i][j] == 0) continue;
                if (prev == 0) {
                    prev = curMap[i][j];
                } else if (prev == curMap[i][j]) {
                    temp[idx--] = prev * 2;
                    maxResult = Math.max(maxResult, prev * 2);
                    prev = 0;
                } else {
                    temp[idx--] = prev;
                    prev = curMap[i][j];
                }
            }
            if (prev != 0) temp[idx] = prev;
            for (int i = 0; i < N; i++) curMap[i][j] = temp[i];
        }
    }

    static void moveLeft() {
        for (int i = 0; i < N; i++) {
            int[] temp = new int[N];
            int idx = 0, prev = 0;
            for (int j = 0; j < N; j++) {
                if (curMap[i][j] == 0) continue;
                if (prev == 0) {
                    prev = curMap[i][j];
                } else if (prev == curMap[i][j]) {
                    temp[idx++] = prev * 2;
                    maxResult = Math.max(maxResult, prev * 2);
                    prev = 0;
                } else {
                    temp[idx++] = prev;
                    prev = curMap[i][j];
                }
            }
            if (prev != 0) temp[idx] = prev;
            for (int j = 0; j < N; j++) curMap[i][j] = temp[j];
        }
    }

    static void moveRight() {
        for (int i = 0; i < N; i++) {
            int[] temp = new int[N];
            int idx = N - 1, prev = 0;
            for (int j = N - 1; j >= 0; j--) {
                if (curMap[i][j] == 0) continue;
                if (prev == 0) {
                    prev = curMap[i][j];
                } else if (prev == curMap[i][j]) {
                    temp[idx--] = prev * 2;
                    maxResult = Math.max(maxResult, prev * 2);
                    prev = 0;
                } else {
                    temp[idx--] = prev;
                    prev = curMap[i][j];
                }
            }
            if (prev != 0) temp[idx] = prev;
            for (int j = 0; j < N; j++) curMap[i][j] = temp[j];
        }
    }

    static void copyMap() {
        for (int i = 0; i < N; i++) curMap[i] = map[i].clone();
    }
}
