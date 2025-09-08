import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_7569_토마토>
 * 3차원 배열 BFS 문제
 * 
 * 1. 3차원 배열에 토마토 상자의 상태를 저장한다.
 * 		- 주의할 점은 각 상자를 아래에서부터 입력받는다. 따라서 높이는 H-1부터 0까지에 저장한다.
 * 		- 입력값을 받으면서 모든 토마토의 개수를 센다. 즉, 빈 칸을 제외한 모든 토마토 개수가 모두 익어야 하는 토마토의 개수가 된다.
 * 		- 또한 처음부터 익어있는 토마토의 개수를 센다. 현재 익어있는 토마토는 큐에 저장하고 방문처리 한다.
 * 2. 만약 이미 모든 토마토가 익어있다면 바로 0을 출력한다. 그렇지 않다면 bfs를 돌려 최소 일수를 구한다.
 * 		- 현재 익어있는 토마토를 처리할 때까지는 같은 날짜이므로 현재 큐의 크기만큼 for문을 돌려 큐에 저장하고 날짜를 하루 증가시킨다.
 * 		- 큐에서 하나씩 뽑아 다음 위치를 구하고, 다음 위치가 범위를 벗어나지 않고 안익은 토마토이며 아직 방문하지 않았을 떄에만 큐에 저장한다.
 * 		- 큐에 저장할 때 익은 토마토 개수를 하나씩 증가시킨다.
 * 		- 만약 모든 토마토가 익었으면 총 걸린 일수를 리턴하고, 아니라면 일수를 증가시켜 다시 for문을 돌린다.
 * 		- 만약 while문을 다 돌렸는데도 최소 일수를 리턴하지 못하면 모든 토마토가 익지 못하는 것으로 -1을 리턴한다.
 * 3. bfs를 통해 구한 최소 일수를 출력한다.
 * 
 * @author YooSejin
 *
 */

public class BJ_7569_토마토 {
	
	static int M; // 가로 (열)
	static int N; // 세로 (행)
	static int H; // 높이
	static int[][][] arr;
	static int totalCnt = 0; // 익어야 하는 토마토 개수
	static int ripe = 0; // 익은 토마토 개수
	static Queue<Node> queue;
	static boolean[][][] visited;
	
	// 뒤, 오른쪽, 앞, 왼쪽, 위, 아래
	static int[] dx = { -1, 0, 1, 0, 0, 0 };
	static int[] dy = { 0, 1, 0, -1, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, -1, 1 };
	
	static class Node {
		int x;
		int y;
		int h;
		
		Node(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
		
        arr = new int[N][M][H];
        queue = new ArrayDeque<>();
        visited = new boolean[N][M][H];
        
        for(int h=H-1; h>=0; h--) {
        	for(int i=0; i<N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j=0; j<M; j++) {
        			arr[i][j][h] = Integer.parseInt(st.nextToken());
        			
        			if(arr[i][j][h] != -1) totalCnt++;
        			if(arr[i][j][h] == 1) {
        				queue.add(new Node(i, j, h));
        				visited[i][j][h] = true;
        				ripe++;
        			}
        		}
        	}
        }
        
        if(ripe == totalCnt) {
        	System.out.println(0);
        } else {
        	System.out.println(bfs());
        }
	}
	
	static int bfs() {
		int min = 1; // 토마토가 다 익는데 걸리는 최소 일수
		
		while(!queue.isEmpty()) {
			int len = queue.size();
			
			for(int i=0; i<len; i++) {
				Node current = queue.poll();
				
				for(int dir=0; dir<6; dir++) {
					int nx = current.x + dx[dir];
					int ny = current.y + dy[dir];
					int nh = current.h + dh[dir];
					
					if(isRange(nx, ny, nh)) {
						if(arr[nx][ny][nh] == 0 && !visited[nx][ny][nh]) {
							queue.add(new Node(nx, ny, nh));
							visited[nx][ny][nh] = true;
							ripe++;
						}
					}
				}
			}
			
			if(ripe == totalCnt) return min;
			min++;
		}
		
		return -1; // 토마토가 모두 익지 못하면 -1 출력
	}
	
	static boolean isRange(int x, int y , int h) {
		if(x >= 0 && x < N && y >= 0 && y < M && h >= 0 && h < H) {
			return true;
		}
		return false;
	}

}
