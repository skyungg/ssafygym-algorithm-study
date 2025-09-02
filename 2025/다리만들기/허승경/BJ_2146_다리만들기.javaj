import java.io.*;
import java.util.*;

/*
 * 아이디어: 완전탐색 + bfs
 * 1. 각 육지 구역마다 표시하기 (idx++ 하면서 각 구역 색칠해나가기)
 * 2. 전체 탐색하면서 다리 연결하기
 * 	(1) 육지의 가장자리 선정하기 -> 4방탐색만 해서 하나라도 바다와 인접하면 가장자리	-> map 돌 때, 같이 구해버리기
 * 	(2) 현재 위치에서 타 육지 간의 다리 길이 구하기
 * 		- 타 육지 만남 -> 해당 다리 길이와 최솟값 비교해서 저장하기
 * 	(3) 최솟값 출력하기
 * */
public class Main {
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N;
	static int [][] map;
	static int [][] dis;
	static int [] dx = {-1, 0, 1, 0};
	static int [] dy = {0, 1, 0, -1};
	static Queue<Point> edge_que = new LinkedList<>();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		dis = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1. 각 육지마다 마킹하기
		int idx = 2;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 1) {	// 해당 구역 표시 전
					mark_area(i, j, idx);
					idx++;
				}
			}
		}
		
		System.out.println(make_bridge());
		
	}
	
	static void mark_area(int x, int y, int idx) {
		Queue<int []> que = new LinkedList<>();
		que.add(new int[] {x, y});
		
		boolean [][] visited = new boolean[N][N];
		visited[x][y] = true;
		map[x][y] = idx;
		
		while(!que.isEmpty()) {
			int [] point = que.poll();
			
			// 현재 위치가 가장자리인지 판단하기 -> 바다가 하나라도 나오면 OK
			for(int i = 0; i < 4; i++) {
				int gx = point[0] + dx[i];
				int gy = point[1] + dy[i];
				
				if(checkRange(gx, gy) && map[gx][gy] == 0) {	// 방문 배열 따로 처리 안해도 되는 이유 -> 어차피 que에는 중복으로 안 들어가니까
					edge_que.add(new Point(point[0], point[1]));	//현재 좌표, 초기 거리, 현재 육지 번호
					break;
				}
			}
			
			// 육지 구역 구하기
			for(int i = 0; i < 4; i++) {
				int tx = point[0] + dx[i];
				int ty = point[1] + dy[i];
				
				if(checkRange(tx, ty) && !visited[tx][ty] && map[tx][ty] == 1) {
					visited[tx][ty] = true;
					map[tx][ty] = idx;		// 구역 색칠하기
					que.add(new int[] {tx, ty});
				}
			}
		}
	}
	
	static int make_bridge() {
		int min_length = Integer.MAX_VALUE;		// 다리 길이 최솟값
		boolean [][] visited = new boolean[N][N];
		
		while(!edge_que.isEmpty()) {
			Point cur_point = edge_que.poll();
			
			for(int i = 0; i < 4; i++) {
				int tx = cur_point.x + dx[i];
				int ty = cur_point.y + dy[i];
				
				if(!checkRange(tx, ty)) continue;	// 범위 밖
				
				if(!visited[tx][ty] && map[tx][ty] == 0) {	// 방문 전+ 바다
					dis[tx][ty] = dis[cur_point.x][cur_point.y] + 1;	// 거리+1
					map[tx][ty] = map[cur_point.x][cur_point.y];	// 어느 육지에서 시작된 건지 표시하기
					edge_que.add(new Point(tx, ty));
				}else if(dis[tx][ty] > 0 || map[tx][ty] >= 1) {	// 이미 방문 or 다른 섬 도착
					if(map[tx][ty] != map[cur_point.x][cur_point.y]) {
						min_length = Math.min(min_length, dis[tx][ty] + dis[cur_point.x][cur_point.y]);
					}
				}
			}
		}
		return min_length;
	}
	
	static boolean checkRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
