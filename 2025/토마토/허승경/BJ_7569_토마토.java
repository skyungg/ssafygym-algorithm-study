// 125952KB /	564ms
import java.io.*;
import java.util.*;

/*
 * 아이디어: bfs + 구현
 * 1. 익은 토마토의 좌표 값, 날짜(초기: 0)을 que에 저장 하고, count 하기
 * 	- COUNT가 0이라면 익을 토마토 x -> 이미 다 익혀있는 상태 -> 0출력 끝
 * 2. bfs 이용해서, 인접 토마토 익히기
 * 	- que에서 하나씩 꺼낼때마다, 날짜 최댓값 구하기
 * 	- 범위 체크
 *  - 방문 체크(기존의 map 활용, 따로 방문배열 X) + 익지 않은 토마토 여부 확인
 *  	-> 해당 좌표, 시간+1 que에 넣기
 * 3. count값과 익힌 토마토 개수 일치한지 확인 한 후, 결과 내기
 * 	- 같음 -> 날짜 최댓값 / 같지 X -> -1 / 
 * 정수 1은 익은 토마토, 정수 0 은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸
 * */

public class Main {
	static int N, M, H;
	static int [][][] map;
	static Queue<int []> que = new LinkedList<>();
	static int count = 0;
	static int [] dh = {-1, 1, 0, 0, 0, 0};		// 인접 6방향
	static int [] dx = {0, 0, -1, 0, 1, 0};
	static int [] dy = {0, 0, 0, 1, 0, -1};	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());	// 열, 행, 높이 입력
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N][M];
		
		for(int h = 0; h < H; h++) {
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[h][i][j] = Integer.parseInt(st.nextToken());
					if(map[h][i][j] == 1) {
						que.add(new int[] {h, i, j, 0});
					}else if(map[h][i][j] == 0) count++;		// 안 익은 토마토 개수
				}
			}
		}
		
		if(count == 0) {
			// 이미 다 익은 상태
			System.out.println(0);
		}else {
			System.out.println(bfs());
		}
		
	}
	
	static int bfs() {
		int result = 0;	// 최소 일수
		
		int cnt = 0;
		while(!que.isEmpty()) {
			int [] point = que.poll();
			
			result = Math.max(result, point[3]);
			
			for(int i = 0; i < 6; i++) {
				int th = point[0] + dh[i];
				int tx = point[1] + dx[i];
				int ty = point[2] + dy[i];
				
				if(checkRange(th, tx, ty) && map[th][tx][ty] == 0) {
					map[th][tx][ty] = 1;
					que.add(new int[] {th, tx, ty, point[3]+1});
					cnt++;	// 0에서 1로 익힌 토마토 개수 1증가
				}	
			}
		}
		
		if(count == cnt) return result;
		else return -1;
	}
	
	static boolean checkRange(int h, int x, int y) {
		return (h >= 0 && h < H && x >= 0 && x < N && y >= 0 && y < M);
	}
}
