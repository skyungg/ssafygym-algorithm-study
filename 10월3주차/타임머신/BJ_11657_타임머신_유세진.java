import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_11657_타임머신>
 * 1) 시작 정점에서 모든 정점까지의 최단거리
 * 2) 음의 가중치 존재
 * 3) 음수 싸이클이 존재하는지 판단해야 함
 * => 벨만포드 알고리즘 !!
 * 
 * 이 문제는 1번 도시에서 출발해 나머지 모든 도시로 가는 최단 시간을 구해야하고, 버스 타고 가는데 걸리는 시간이 음수도 가능하다.
 * 또한 시간을 무한히 오래 전으로 되돌릴 수 있다면, 즉, 합이 음수인 싸이클이 존재하는 것으로 음수 싸이클을 판단해 -1을 출력해야 하므로 벨만포드가 알고리즘을 써야 한다.
 * 
 * 1. 벨만포드 알고리즘은 간선 중심이므로 간선 정보를 배열 혹은 리스트에 저장한다.
 * 2. 최단거리를 저장할 배열을 초기화한다.
 * 		- 이 때 배열 타입에 주의 : distance[to] > distance[from] + cost 를 비교하는 과정에서 INF+cost가 될 수 있으므로 오버플로우 발생할 수 있음. long 타입 사용.
 * 		- INF로 초기화
 * 		- 시작 정점은 0으로 초기화
 * 3. 벨만포드 알고리즘을 호출해 음수 사이클 여부를 판단
 * 		- N-1만큼 for문 돌림
 * 		- 간선의 개수만큼 for문 돌림
 * 		- 시작점의 최단거리가 INF가 아니고
 * 		- 더 작은 값으로 갱신할 수 있으면 갱신
 * 		- 한번 더 모든 간선을 돌려 작은 값으로 갱신이 가능하면 음수 사이클이 존재하는 것으로 true 반환
 * 		- 아니라면 false 반환
 * 4. 음수 사이클이 존재하면 -1 출력 / 아니라면 각 정점까지의 최단거리 출력(경로가 없으면 -1 출력)
 * 
 * @author YooSejin
 *
 */

public class BJ_11657_타임머신 {
	
	static int N; // 도시의 개수
	static int M; // 버스 노선의 개수
	static Edge[] edges; // 간선 정보를 저장할 배열
	static long[] distance; // 최단 거리를 저장할 배열 (long 타입 주의)
	
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        edges = new Edge[M];
		
        // 간선 정보 저장
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	edges[i] = new Edge(from, to, cost);
        }
        
        // 최단거리를 저장할 배열 초기화
        distance = new long[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0; // 시작 도시는 0으로 초기화
        
        if(bellmanford()) { // 음수 사이클이 존재하면 -1 출력
        	System.out.println(-1);
        } else {
        	StringBuilder sb = new StringBuilder();
        	for(int i=2; i<=N; i++) {
        		sb.append(distance[i] == Integer.MAX_VALUE ? -1 : distance[i]);
        		sb.append("\n");
        	}
        	System.out.println(sb);
        }
	}
	
	// 음수 싸이클이 있으면 true 반환, 없으면 false 반환
	static boolean bellmanford() {
		for(int i=0; i<N-1; i++) { // 정점의 개수 -1 만큼 반복
			for(int j=0; j<M; j++) { // 모든 간선을 탐색하기 반복
				Edge edge = edges[j];
				int from = edge.from;
				int to = edge.to;
				int cost = edge.cost;
				
				if(distance[from] != Integer.MAX_VALUE) {
					if(distance[to] > distance[from] + cost) {
						distance[to] = distance[from] + cost; // 최소비용 갱신
					}
				}
			}
		}
		
		// 음수 싸이클 있는지 확인. 한번 더 간선을 탐색한다.
		for(int i=0; i<M; i++) {
			Edge edge = edges[i];
			int from = edge.from;
			int to = edge.to;
			int cost = edge.cost;
			
			// 더 작은 값으로 갱신이 되면 음수 사이클이 존재하는 것!!
			if(distance[from] != Integer.MAX_VALUE) {
				if(distance[to] > distance[from] + cost) {
					return true;
				}
			}
		}
		
		return false; // 음수 사이클 없으면 false 반환
	}

}
