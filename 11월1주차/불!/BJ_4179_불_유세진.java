import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_4179_불>
 * 가장 빠른 시간 내에 미로를 탈출시켜야 하므로 즉, 격자를 빠져나와야 하므로 BFS로 풀면 된다.
 * 가장 먼저 격자를 빠져나올 때가 가장 빠른 탈출시간이다.
 * 
 * 매 초마다 지훈이와 불을 동시에 이동시켜야 한다.
 * 지훈이가 미로를 빠져나갈 수 있는지를 확인해야 하므로 while문은 지훈이의 위치를 담은 큐가 빌 때까지 돌린다.
 * 그리고 지훈이는 불이 퍼진 곳으로 이동할 수 없으므로 불을 먼저 이동시킨다.
 * 그리고 미로의 값을 확인해 불이 없고 길이 있는 곳으로만 지훈이를 이동시킨다.
 * 즉, while문이 한번 도는 동안 불 이동시키고 지훈이를 이동시키고 타임을 +1씩 증가시키면 된다.
 * 도중에 지훈이가 미로를 빠져나가면 즉시 시간을 출력하고 리턴한다.
 * 반면 지훈이가 갈 수 있는 곳이 없는데도 즉, 지훈이의 큐가 비었는데도 아직 탈출하지 못했다면 while문을 벗어나와 "IMPOSSIBLE"을 출력한다.
 * 
 * @author YooSejin
 *
 */

public class BJ_4179_불 {
	
	static int R;
	static int C;
	static char[][] maze;
	static Queue<Node> jihoon;
	static Queue<Node> fire;
	static boolean[][] visited; // 지훈이의 방문 여부
	
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
		
        maze = new char[R][C];
        jihoon = new ArrayDeque<>();
        fire = new ArrayDeque<>();
        visited = new boolean[R][C];
        
        for(int i=0; i<R; i++) {
        	String str = br.readLine();
        	for(int j=0; j<C; j++) {
        		maze[i][j] = str.charAt(j);
        		
        		if(maze[i][j] == 'J') {
        			jihoon.add(new Node(i, j));
        			visited[i][j] = true;
        		} else if(maze[i][j] == 'F') {
        			fire.add(new Node(i, j));
        		}
        	}
        }
        
        int time = 1;
        
        while(!jihoon.isEmpty()) {
        	// 불이 먼저 퍼진다.
        	// 지훈이는 불이 퍼진 곳으로 갈 수 없다.
        	int len = fire.size();
        	
        	for(int i=0; i<len; i++) {
        		Node current = fire.poll();
        		
        		for(int d=0; d<4; d++) {
        			int nx = current.x + dx[d];
        			int ny = current.y + dy[d];
        			
        			if(isRange(nx, ny)) {
        				if(maze[nx][ny] == '.' || maze[nx][ny] == 'J') { // 벽은 통과하지 못하고 이미 불이난 곳은 다시 갈 필요 없다.
        					fire.add(new Node(nx, ny));
        					maze[nx][ny] = 'F';
        				}
        			}
        		}
        	}
        	
        	// 지훈이가 이동한다.
        	len = jihoon.size();
        	
        	for(int i=0; i<len; i++) {
        		Node current = jihoon.poll();
        		
        		for(int d=0; d<4; d++) {
        			int nx = current.x + dx[d];
        			int ny = current.y + dy[d];
        			
        			if(!isRange(nx, ny)) { // 미로 탈출
        				System.out.println(time);
        				return;
        			}
        			
        			if(maze[nx][ny] == '.' && !visited[nx][ny]) { // 길이 있고 아직 방문하지 않은 곳으로만 이동
        				jihoon.add(new Node(nx, ny));
        				visited[nx][ny] = true;
        				if(maze[current.x][current.y] == 'J') { // 지훈이가 원래 있던 곳에 불이 퍼졌다면(즉, 'F'라면) 그냥 두고 그게 아니라면 이동했으니 빈 공간으로 바꿔준다.
        					maze[current.x][current.y] = '.';
        				}
        			}
        		}
        	}
        	
        	time++;
        }
        
        // 지훈이가 갈 수 있는 곳은 다 돌았는데도 미로를 탈출하지 못했다면 impossible 출력
		System.out.println("IMPOSSIBLE");
	}
	
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < R && y >= 0 && y < C) {
			return true;
		}
		return false;
	}

}
