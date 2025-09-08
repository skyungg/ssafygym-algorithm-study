import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_2146_다리만들기>
 * 1. 먼저 다른 섬임을 구분짓기 -> 각 섬마다 다른 번호 부여
 * 2. 전체를 이중 for문 돌리면서 바다와 접해있으면 최단 거리 찾기 위한 bfs 돌리기
 * 		- bfs 돌릴 때마다 방문 배열은 초기화 -> 이전에 최단 경로를 찾기 위해 지났던 길을 또 지나갈 수 있기 때문.
 * 		- 시작섬의 번호랑 다른 번호의 섬을 만나면 최단 거리를 갱신
 * 3. 다른 섬까지의 최단 거리 출력
 * 
 * @author YooSejin
 *
 */

public class BJ_2146_다리만들기 {
	
	static int N;
	static int[][] arr;
	static int answer = Integer.MAX_VALUE;
	
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	
	static class Node {
		int x;
		int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        
        arr = new int[N][N];
		
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 1. 섬마다 다른 번호 부여하기
        int num = 1;
        boolean[][] visited = new boolean[N][N];
        
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		if(arr[i][j] != 0 && !visited[i][j]) {
        			decideNumber(new Node(i, j), num, visited);
        			num++;
        		}
        	}
        }
        
        // 2. 최단 거리 찾기
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		if(arr[i][j] != 0) {
        			findMin(new Node(i, j));
        		}
        	}
        }
        
        System.out.println(answer);
	}
	
	static void decideNumber(Node node, int num, boolean[][] visited) {
		Queue<Node> queue = new ArrayDeque<>();

		queue.add(node);
		visited[node.x][node.y] = true;
		arr[node.x][node.y] = num;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if(isRange(nx, ny)) {
					if(arr[nx][ny] != 0 && !visited[nx][ny]) {
						queue.add(new Node(nx, ny));
						visited[nx][ny] = true;
						arr[nx][ny] = num;
					}
				}
			}
		}
	}
	
	static void findMin(Node node) {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		queue.add(node);
		visited[node.x][node.y] = true;
		
		int startNum = arr[node.x][node.y]; // 시작섬의 번호
		int cnt = 1; // 거리
		
		while(!queue.isEmpty()) {
			int len = queue.size();
			
			for(int i=0; i<len; i++) {
				Node current = queue.poll();
				
				for(int dir=0; dir<4; dir++) {
					int nx = current.x + dx[dir];
					int ny = current.y + dy[dir];
					
					if(isRange(nx, ny)) {
						if(arr[nx][ny] == 0 && !visited[nx][ny]) { // 다음칸이 바다이고 아직 방문하지 않았으면
							queue.add(new Node(nx, ny));
							visited[nx][ny] = true;
						}
						if(arr[nx][ny] != 0 && arr[nx][ny] != startNum) { // 다른 섬을 만나면
							answer = Math.min(answer, cnt-1);
							return;
						}
					}
				}
			}
			
			cnt++;
		}
	}
	
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < N) {
			return true;
		}
		return false;
	}

}
