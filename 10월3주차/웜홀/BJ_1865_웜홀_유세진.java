import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_1865_웜홀>
 * 음의 가중치가 존재하고 음수 사이클이 존재하는지 판단해야하므로 벨만포드 알고리즘 문제.
 * 여기서는 시간이 줄어들면서 출발 위치로 돌아올 수 있는가를 알고 싶기 때문에 음수 사이클이 있는지에 초점을 맞추면 된다.
 * 주의할 점은 시작 정점이 따로 주어지지 않았다는 것이다.
 * 그렇다면 두가지 풀이 가능
 * 1) 모든 정점을 시작점으로 정한 다음 음의 사이클 발생하는지 확인
 * 2) 아무 정점을 출발점으로 정한 다음 음의 사이클이 발생하는지 확인
 * 		- 이 경우에는 대신 distance[to] != INF 를 생략한다.
 * 		- 만약 1번 정점에서 시작하는데 여기는 음수 사이클이 없고, 2번과 3번 정점에 음수 사이클이 있다고 하면 1번 정점에서 탐색이 끝나 음수 사이클이 없다고 판별할 것이다. 하지만 실제로는 음수 사이클이 있는 경우이다.
 * 		- 따라서 INF가 아닌 경우라는 조건을 빼고 모든 경우를 확인한다.
 * 
 * @author YooSejin
 *
 */

public class BJ_1865_웜홀 {
	
	static int N; // 지점의 수
	static int M; // 도로의 개수
	static int W; // 웜홀의 개수
	static List<Edge> edges; // 도로와 웜홀 정보 저장
	
	static class Edge {
		int from;
		int to;
		int cost;
		
		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int TC = Integer.parseInt(br.readLine());
		
        for(int tc=0; tc<TC; tc++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	W = Integer.parseInt(st.nextToken());
        	
        	edges = new ArrayList<>();
        	
        	// 도로와 웜홀 정보 저장
        	for(int i=0; i<M+W; i++) {
        		st = new StringTokenizer(br.readLine());
        		int from = Integer.parseInt(st.nextToken());
        		int to = Integer.parseInt(st.nextToken());
        		int cost = Integer.parseInt(st.nextToken());
        		
        		if(i < M) { // 도로(양방향)
        			edges.add(new Edge(from, to, cost));
        			edges.add(new Edge(to, from, cost));
        		} else { // 웜홀(단방향)
        			edges.add(new Edge(from, to, -cost));
        		}
        	}
        	
        	// 음수 사이클이 발생하면 즉, 시간이 줄어들면서 출발 위치로 돌아오는 것이 가능하면 YES 출력
        	System.out.println(bellmanford() ? "YES" : "NO");
        }
		
	}
	
	static boolean bellmanford() {
		long[] distance = new long[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[1] = 0; // 임의로 시작점을 1로 잡고 초기화
		
		for(int i=0; i<N; i++) { // 모든 정점의 수
			for(int j=0; j<edges.size(); j++) { // 모든 간선 확인
				Edge edge = edges.get(j);
				
				int from = edge.from;
				int to = edge.to;
				int cost = edge.cost;
				
				// 시작정점이 주어지지 않음
				// INF가 아닌 정점에서만 최소비용을 갱신하는게 아니라 모든 곳에서 최소 비용 갱신
				// 그러면 시작 정점이 어디인지에 상관없이 갱신할 수 있다?
				if(distance[to] > distance[from] + cost) {
					distance[to] = distance[from] + cost;
					
					if(i == N-1) { // i를 N-1 돌리고 N번째에 또 갱신되면 음수 사이클이 발생한 것
						return true;
					}
				}
			}
		}
		
		return false;
	}

}
