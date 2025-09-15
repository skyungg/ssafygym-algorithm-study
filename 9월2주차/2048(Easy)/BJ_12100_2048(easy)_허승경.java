/*
 * 아이디어: 그냥 타고타고 구현하기
 * 
 * */

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
                maxResult = Math.max(maxResult, map[i][j]);		// 현재 가장 큰 블록값으로 값 초기화
            }
        }

        curMap = new int[N][N];
        copyMap();		// 초기 상황을 복사
        start(0);		// dfs 시작
        System.out.println(maxResult);		// 결과 출력
    }

    static void start(int cnt) {
        if (cnt == 5) return;		// 최대 5번 까지 가능하니까

        int[][] backup = new int[N][N];
        for (int i = 0; i < N; i++) backup[i] = curMap[i].clone();

        // 4방향으로 각각 이동하기 
        moveUp();			// 1. 위쪽으로 올려
        start(cnt + 1);
        restore(backup);	// 다시 복구하기

        moveDown();			// 2. 아래로 내리기
        start(cnt + 1);
        restore(backup);
        	
        moveLeft();			// 3. 왼쪽으로
        start(cnt + 1);
        restore(backup);

        moveRight();		// 4. 오른쪽으로 
        start(cnt + 1);
        restore(backup);
    }

    static void restore(int[][] backup) {
        for (int i = 0; i < N; i++) curMap[i] = backup[i].clone();
    }

    static void moveUp() {
    	// 위쪽으로 블록 다 올려버리기
    	
        for (int j = 0; j < N; j++) {
            int[] temp = new int[N];		// 합쳐진 후 결과 저장
            int idx = 0, prev = 0;
            for (int i = 0; i < N; i++) {
                if (curMap[i][j] == 0) continue;	// 현재 빈칸은 넘어가기
                if (prev == 0) {					// 비어 있음 -> 현재값을 직전값으로 저장하기
                    prev = curMap[i][j];		// 
                } else if (prev == curMap[i][j]) {	// 현재값과 직전값이 같음 0> 합치고 직전값은 0
                    temp[idx++] = prev * 2;		// 합쳐진 값 넣기
                    maxResult = Math.max(maxResult, prev * 2);	// 최대 블록 값 갱신
                    prev = 0;
                } else {
                    temp[idx++] = prev;	// 직전값 저장
                    prev = curMap[i][j];	// 직전값 갱신	
                }
            }
            
            if (prev != 0) temp[idx] = prev;	// 남은 직전값 temp에 넣기
            for (int i = 0; i < N; i++) curMap[i][j] = temp[i];		// 최종 결과 반영
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
