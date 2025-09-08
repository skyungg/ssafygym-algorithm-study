import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_1707_이분그래프>
 * 정점의 색깔을 통해 이분 그래프임을 구분한다.
 * 한 정점에서 연결된 모든 정점이 현재 정점과 다른 색깔을 가지고 있으면 이분 그래프이고,
 * 인접한 정점이 같은 색깔을 가지면 이분 그래프가 될 수 없으니 바로 "NO"를 출력한다.
 * 
 * 정점의 색 확인은 정점을 차례대로 for문 돌리고 bfs를 통해 확인한다.
 * 
 * !! 이분 그래프를 정점에 색깔을 부여해 구분하는 것을 처음 알았다 !!
 * 
 * @author YooSejin
 *
 */

public class BJ_1707_이분그래프 {
	
	static int K; // 테스트케이스의 개수
	static int V; // 정점의 개수
	static int E; // 간선의 개수
	static List<Integer>[] graph; // 간선 정보 저장
	static int[] color; // 정점의 색깔 저장
	static String answer;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        K = Integer.parseInt(br.readLine());
        
        for(int t=0; t<K; t++) {
        	st = new StringTokenizer(br.readLine());
        	V = Integer.parseInt(st.nextToken());
        	E = Integer.parseInt(st.nextToken());
        	
        	graph = new ArrayList[V+1];
        	for(int i=0; i<=V; i++) {
        		graph[i] = new ArrayList<>(); // 초기화
        	}
        	
        	// 간선 정보 입력
        	for(int i=0; i<E; i++) {
        		st = new StringTokenizer(br.readLine());
        		int from = Integer.parseInt(st.nextToken());
        		int to = Integer.parseInt(st.nextToken());
        		
        		graph[from].add(to);
        		graph[to].add(from);
        	}
        	
        	color = new int[V+1];
        	
        	answer = "YES"; // 초기화
        	
        	for(int i=1; i<=V; i++) { // 1~V번 정점까지 차례대로 색깔 확인 및 지정
        		if(color[i] == 0) {
        			if(!bfs(i)) { // 이분 그래프가 아닌 경우
        				break;
        			}
        		}
        	}
        	
        	System.out.println(answer);
        }
	}
	
	// node : 현재 정점 번호
	static boolean bfs(int node) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(node);
		color[node] = 1;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(int v : graph[now]) {
				if(color[v] == color[now]) { // 인접한 정점 색깔이 현재 정점이랑 같으면
					answer = "NO"; // NO를 출력하고
					return false; // 바로 false 리턴
				}
				
				if(color[v] == 0) { // 인접한 정점이 색깔이 아직 없다면
					color[v] = color[now] * -1; // 반대 색깔로 저장
					queue.add(v); // 큐에 추가 -> 아직까지는 이분그래프이니 계속 탐색
				}
			}
		}
		
		return true; // while문이 끝날 때까지 리턴하지 않으면 이분그래프이므로 true 리턴
	}

}
