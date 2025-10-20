import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <BJ_1753_최단경로>
 * 주어진 시작점(한 정점)에서 다른 모든 정점으로의 최단 경로를 구하는 문제이고,
 * 모든 간선의 가중치는 양의 정수이므로 다익스트라 문제임을 알 수 있다.
 * 다익스트라 알고리즘은 우선순위 큐를 사용해 최적의 시간복잡도로 풀이할 수 있다. -> O(ElogV)
 * 
 * <우선순위 큐를 사용하는 이유>
 * 어떤 정점에서 뻗어나갈 것인가를 하나씩 큐에서 뺴서 확인 가능
 * 그리고 누적된 거리를 기준으로 오름차순 정렬하면 가장 짧은 거리부터 확인해 가장 빠르게 최단경로를 갱신할 수 있음
 * 
 * 1. 그래프 정보 저장 -> List<Node>[]
 * 2. 최단 거리를 저장할 DP 배열 필요
 * 		- INF로 초기화
 * 		- 시작점은 0으로 초기화
 * 3. while문으로 우선순위 큐가 빌 때까지 돌리며 최단경로 갱신
 * 		- 다음 정점의 최단경로 distance[next.num]와 현재 정점까지의 누적 거리에서 다음 정점으로 가는 가중치를 더해 나온 값(cost + next.cost) 비교
 * 		- 새로운 경로가 더 최단이라면 최단경로 갱신
 * 		- 최단경로가 갱신될 때만 우선순위 큐에 추가
 * 		- 추가로 가지치기 가능 : distance[정점]에 갱신된 값보다 큐에서 꺼낸 누적 거리가 더 크면 아무리 비교해도 최단 경로로 갱신 불가 -> continue 가지치기
 * 4. 최단경로가 저장된 distance 배열 출력
 * 
 * @author YooSejin
 *
 */

public class BJ_1753_최단경로 {
	
	static int V; // 점점의 개수
	static int E; // 간선의 개수
	static int K; // 시작 정점의 번호
	static List<Node>[] graph;
	
	static class Node implements Comparable<Node> {
		int num; // 정점 번호
		int cost; // 현재까지 누적된 거리의 합 -> 우선순위 큐에 넣을 때 사용
		
		Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost; // 누적된 거리를 기준으로 오름차순 정렬
		}
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[V+1];
		for(int i=0; i<V+1; i++) {
			graph[i] = new ArrayList<>(); // 초기화
		}
		
		// 간선 정보 저장
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from].add(new Node(to, cost));
		}
		
		// 최단 거리를 저장하는 DP 배열
		int[] distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE); // INF로 초기화
		distance[K] = 0; // 시작점은 0으로 초기화
		
		// 우선순위 큐를 사용해 최단 경로 구하기
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(K, 0));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			int num = current.num;
			int cost = current.cost;
			
			if(distance[num] < cost) continue; // 최단거리로 갱신되어 있는 값보다 큐에서 꺼낸 누적 거리가 더 크다면 아무리 비교해도 더 짧은 거리로 갱신할 수 없으니 continue 가지치기
			
			for(Node next : graph[num]) {
				if(distance[next.num] > cost + next.cost) { // 현재 저장된 최단경로보다 새로운 경로가 더 최단거리라면
					distance[next.num] = cost + next.cost; // 최단경로 갱신
					pq.add(new Node(next.num, distance[next.num])); // 갱신할 때만 우선순위 큐에 추가
				}
			}
		}
        
		// distance 배열에 저장된 최단 경로 출력하기
		for(int i=1; i<=V; i++) {
			System.out.println(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]);
		}
	}

}
