/*
 * 아이디어: 구현, 완탐?
 * 1. 시작점과 d1, d2 선정에 꼼수 있음? -> X
 * 	   -> 완탐 돌려서 구한 좌표들로 최솟값 갱신해나가기
 * */

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int [][] map;
	static int total_people = 0;	// 총 인원수
	static int min_diff = Integer.MAX_VALUE;	// 두 지역 간의 최소 차이
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total_people += map[i][j];	// 총 인원 카운트 -> 나중에 5구역 카운트 안하고 바로 구하려고
            }
        }
        
        // 구현 -> 4중 for문 돌려버려~~
        for(int x = 0; x < N; x++) {
        	for(int y = 0; y < N; y++) {
        		for(int d1 = 0; d1 < N; d1++) {
                	for(int d2 = 0; d2 < N; d2++) {
                		if(x + d1 + d2 >= N) continue;				// 아래 범위 벗어남
                		if (y - d1 < 0 || y + d2 >= N) continue;	// 왼쪽-오른쪽 범위 벗어나느 경우
                    	
                		solve(x, y, d1, d2);	// 기준점과 거리 두개 선정하기
                    }
                }
            }
        }
        
        System.out.println(min_diff);
        
	}
	
	static void solve(int x, int y, int d1, int d2) {
		boolean [][] visited = new boolean[N][N];	// 방문 배열
		
		// 0. 경계선 표시
		for(int i = 0; i <= d1; i++) {
			visited[x+i][y-i] = true;
			visited[x+d2+i][y+d2-i] = true;
		}
		
		for (int i = 0; i <= d2; i++) {
			visited[x + i][y + i] = true;
			visited[x + d1 + i][y - d1 + i] = true;
        }

        int[] count = new int[5];

        // 1. 1구역 인구수 구하기 (0, 0) - (x+d1-1, y)
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (visited[i][j]) break;		// 경계선 만나면 바로 나가
                count[0] += map[i][j];
            }
        }

        // 2. 2구역 인구수 구하기 (0, 0) - (x+d1-1, y) -> 오른쪽 위 영역
        for (int i = 0; i <= x + d2; i++) {			// 오른쪽에서 -> 왼쪽 확인
            for (int j = N - 1; j > y; j--) {
                if (visited[i][j]) break; 		// 경계선 만나면 바로 나가
                count[1] += map[i][j];
            }
        }

        // 3. 3구역 인구수 구하기 (x+d1, 0) - (N-1, y-d1+d2-1) -> 왼쪽아래
        for (int i = x + d1; i < N; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (visited[i][j]) break;		// 경계선 만나면 바로 나가기
                count[2] += map[i][j];
            }
        }

        // 4. 4구역 인구수 구하기 (x+d2+1, N-1) - (N-1, y-d1+d2) -> 오른쪽아래
        for (int i = x + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= y - d1 + d2; j--) {
                if (visited[i][j]) break;
                count[3] += map[i][j];
            }
        }

        // 5. 5구역 인구수
        count[4] = total_people;
        for (int i = 0; i < 4; i++) {		
        	count[4] -= count[i];		// 전체 인구수에서 (1~4)구역 합 빼기
        }

        // 정렬
        Arrays.sort(count);			// 정렬 -> 차이 구하려고

        // 최솟값 갱신하기
        min_diff = Math.min(min_diff, count[4] - count[0]);
	}

}
