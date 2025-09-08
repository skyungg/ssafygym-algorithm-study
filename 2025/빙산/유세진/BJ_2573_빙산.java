import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_2573_빙산>
 * 1. 입력값 받으면서 빙산이 있는 곳은 큐에 추가 (빙산의 높이로 확인하면 되기 때문에 방문배열 필요없음)
 * 2. 큐가 빌 때까지 wihle문 돌리면서 빙산의 높이 줄이기
 * 		- 단 현재 큐의 크기만큼 for문을 돌려 년수를 센다.
 * 		- 주의할 점 : arr 배열에 빙산의 높이를 반영하기 때문에 복사 배열을 만들어 사방이 바다인지 확인해야 한다.
 * 		- 1년이 지난 후 빙산이 몇 덩어리인지 확인한다. -> bfs, 방문처리
 * 		- 빙산이 두 덩어리 이상이면 최소 년수를 출력하고, while문이 끝날 때까지 분리되지 않으면 0을 출력한다.
 * 
 * @author YooSejin
 *
 */

public class BJ_2573_빙산 {
	
	static int N;
	static int M;
	static int[][] arr;
	static Queue<Ice> queue;
	static boolean[][] visited; // 빙산 덩어리의 개수 확인할 때 사용
	
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	
	static class Ice {
		int x;
		int y;
		int h;
		
		Ice(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
		
        arr = new int[N][M];
        queue = new ArrayDeque<>();
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<M; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        		
        		if(arr[i][j] != 0) {
        			queue.add(new Ice(i, j, arr[i][j]));
        		}
        	}
        }
        
        System.out.println(bfs());
	}
	
	static int bfs() {
		int time = 1;
		
		while(!queue.isEmpty()) {
			// 배열 복사
			int[][] copy = new int[N][N];
			for(int i=0; i<N; i++) {
				copy[i] = Arrays.copyOf(arr[i], M);
			}
			
			int len = queue.size();
			
			for(int i=0; i<len; i++) {
				Ice current = queue.poll();
				int x = current.x;
				int y = current.y;
				
				int cnt = 0; // 둘러싸인 바닷물의 개수 = 0의 개수
	        	
	        	for(int dir=0; dir<4; dir++) {
	        		int nx = x + dx[dir];
	        		int ny = y + dy[dir];
	        		
	        		if(isRange(nx, ny)) {
	        			if(copy[nx][ny] == 0) cnt++;
	        		}
	        	}
	        	
	        	if(copy[x][y] - cnt < 0) {
	        		arr[x][y] = 0;
	        	} else {
	        		arr[x][y] -= cnt; // 빙산의 높이 줄이기
	        		queue.add(new Ice(x, y, arr[x][y]));
	        	}
			}
			
			// 빙산이 두 덩어리 이상으로 분리됐는지 확인하기
			int count = 0; // 빙산 덩어리 개수
			visited = new boolean[N][M];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(arr[i][j] != 0 && !visited[i][j]) {
						check(new Ice(i, j, arr[i][j]));
						count++;
						
						if(count >= 2) return time;
					}
				}
			}
        	
        	time++;
        }
		
		return 0; // 빙산이 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 0 출력
	}
	
	static void check(Ice ice) {
		Queue<Ice> queue = new ArrayDeque<>();
		queue.add(ice);
		visited[ice.x][ice.y] = true;
		
		while(!queue.isEmpty()) {
			Ice current = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if(isRange(nx, ny)) {
					if(arr[nx][ny] != 0 && !visited[nx][ny]) {
						queue.add(new Ice(nx, ny, arr[nx][ny]));
						visited[nx][ny] = true;
					}
				}
			}
		}
	}
	
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < M) {
			return true;
		}
		return false;
	}

}
