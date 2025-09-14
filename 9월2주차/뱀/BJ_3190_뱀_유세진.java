import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_3190_뱀>
 * 초마다 비슷한 행위가 반복되는 시뮬레이션
 * 구현이 어렵다기보다 자료구조를 잘 활용해야 하는 문제!!
 * 
 * 생각해야 할 것 : 길이가 2이상인 뱀을 어떻게 경로따라 움직이게 할 것인가
 * 단순히 2차원 배열에 값을 저장하기만 하면 뱀을 이동시킬 때 뱀의 위치와 다음 위치를 파악하기 어렵다. 뱀이 꼬불꼬불있기 때문.
 * 따라서 뱀의 위치 정보를 Deque에 저장하고 양방향에 머리는 추가하고 꼬리는 제거하는 식으로 코드를 작성해야 한다.
 * 그러면 중간 몸통의 정보는 항상 유지가 되기 때문이다.
 * 
 * 그렇다면 방향 변환 정보는 어떻게 처리할 것인가?
 * 배열이나 리스트에 저장하면 X초 후 한번 처리하고 난 뒤 인덱스 값을 바꿔줘야 하므로 번거롭다.
 * 하지만 Queue를 사용한다면?
 * 현재 시간과 큐의 첫번째 시간 정보를 비교해 X초 후인지 확인하면 되고, 방향을 바꾸면 해당 변환 정보는 큐에서 제거하면 된다.
 * 따라서 인덱스 값을 불필요하게 관리할 필요가 없다.
 * 
 * 구현하면서 한가지 주의할 점은 머리를 먼저 추가하고, arr[nx][ny]의 값을 1(뱀)로 바꾼 후
 * 꼬리를 제거하려고 하면 if문에 걸리지 않으므로 꼬리를 먼저 제거하고 머리를 추가하는 것이 좋다.
 * 
 * @author YooSejin
 *
 */

public class BJ_3190_뱀 {
	
	static int N; // 보드의 크기
	
	static int[] dx = { 0, 1, 0, -1 }; // 우하좌상
	static int[] dy = { 1, 0, -1, 0 };
	
	static class Node {
		int x;
		int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class ChangeDirection {
		int time;
		String dir;
		
		ChangeDirection(int time, String dir) {
			this.time = time;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine()); // 사과의 개수
		
        int[][] arr = new int[N][N]; // 0:없음, 1:뱀, 2:사과
        
        // 사과의 위치 저장
        for(int i=0; i<K; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken()) - 1;
        	int y = Integer.parseInt(st.nextToken()) - 1;
        	arr[x][y] = 2;
        }
        
        int L = Integer.parseInt(br.readLine()); // 방향 변환 횟수
        
        // 방향 변환 정보 저장
        Queue<ChangeDirection> dirList = new ArrayDeque<>();
        for(int i=0; i<L; i++) {
        	st = new StringTokenizer(br.readLine());
        	int X = Integer.parseInt(st.nextToken()); // X초 뒤
        	String C = st.nextToken(); // C방향으로 90도 회전
        	dirList.add(new ChangeDirection(X, C));
        }
        
        int time = 1; // 게임이 끝나기까지 걸리는 시간
        int dir = 0; // 뱀 이동 방향 인덱스
        
        arr[0][0] = 1; // 뱀의 시작 위치
        Deque<Node> snake = new ArrayDeque<>(); // 뱀의 위치 정보 저장 (머리는 )
        snake.add(new Node(0, 0));
        
        // 뱀 이동 시작
        while(true) {
        	Node head = snake.peek(); // 뱀 머리 좌표 조회 (제거x)
        	
        	int nx = head.x + dx[dir];
        	int ny = head.y + dy[dir];
        	
        	// 벽이나 자기 몸에 부딪히면 게임 종료
        	if(!isRange(nx, ny) || arr[nx][ny] == 1) break;
        	
        	// 다음 칸이 빈칸이라면 꼬리 제거, 아니라면 꼬리 유지
        	// 머리 추가하고(즉, 배열값을 1로 바꾼 후) 꼬리를 제거하려고 하면 이미 arr[nx][ny] 값이 1로 바뀌어 있기 때문에 꼬리를 먼저 제거하고 머리를 추가해야 한다.
        	if(arr[nx][ny] == 0) {
        		Node n = snake.removeLast();
        		arr[n.x][n.y] = 0;
        	}
        	
        	// 다음 칸이 빈칸이든 사과가 있든 머리는 추가
        	snake.addFirst(new Node(nx, ny));
        	arr[nx][ny] = 1;
        	
        	// X초 후 방향 변환
        	if(!dirList.isEmpty() && dirList.peek().time == time) {
        		ChangeDirection info = dirList.poll();
        		
        		if(info.dir.equals("D")) { // 오른쪽으로 90도 회전
        			dir = (dir + 1) % 4;
        		} else if(info.dir.equals("L")) { // 왼쪽으로 90도 회전
        			dir = (dir - 1 + 4) % 4;
        		}
        	}
        	
        	time++;
        }
        
		System.out.println(time);
	}
	
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < N) {
			return true;
		}
		return false;
	}

}
