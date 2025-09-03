import java.io.*;
import java.util.*;

/*
 * 아이디어: 완전탐색, bfs
 * 1. 빙산이 분리되어 있는지 확인
 * 2. 바다와 인접한 구역 1씩 녹이기
 * 3. 1의 과정 반복
 * */
public class Main {
	static int N, M;
	static int [][] map;
	static int [] dx = {-1, 0, 1, 0};
	static int [] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		while(true) {
			// 1. 확인하기
			int result = check_map();
			if(result >= 2) break;
			else if(result == 0) {
				cnt = 0;
				break;	// 빙산 없음 -> 다 녹음
			}
			
			// 2. 얼음 녹이기
			cnt++;
			melt();
		}
		
		System.out.println(cnt);
	}
	
	static int check_map() {
		boolean [][] visited = new boolean[N][M];
		int count = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] > 0 && !visited[i][j]) {
					visited[i][j] = true;
					count++;
					
					Queue<int []> que = new LinkedList<>();
					que.add(new int[] {i, j});
					
					while(!que.isEmpty()){
						int [] cur = que.poll();
						
						for(int idx = 0; idx < 4; idx++) {
							int tx = cur[0] + dx[idx];
							int ty = cur[1] + dy[idx];
							
							if(check_range(tx, ty) && !visited[tx][ty] && map[tx][ty] > 0) {
								visited[tx][ty] = true;
								que.add(new int [] {tx, ty});
							}
						}
					}
				}
			}
		}
		
		return count;
	}
	
	static void melt() {
		int [][] count = new int[N][M];

		for(int i = 1; i < N; i++) {
			for(int j = 1; j < M; j++) {
				if(map[i][j] > 0) {
					// 인접한 바다 수 구하기
					for(int idx = 0; idx < 4; idx++) {
						int tx = i + dx[idx];
						int ty = j + dy[idx];
						
						if(check_range(tx, ty) && map[tx][ty] == 0) {
							count[i][j] += 1;
						}
					}
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] -= count[i][j];
				if(map[i][j] < 0 ) map[i][j] = 0;
			}
		}
	}
	
	static boolean check_range(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
