import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_1647_도시분할계획>
 * 모든 집(정점)이 연결되어 있고 무향 그래프이며 가중치의 최소 합을 구하는 문제이므로
 * 최소 신장 트리 문제이고 크루스칼 알고리즘을 이용한다.
 * 
 * 먼저 최소 신장 트리는 정점은 N개이고 간선은 N-1개인 가중치의 합이 최소인 그래프를 말한다.
 * 단, 싸이클이 발생하면 안되고 무향 그래프임에 주의한다.
 * 그리고 크루스칼 알고리즘은 최소 신장 트리를 구하기 위한 알고리즘이다.
 * 
 * 따라서 일단 먼저 최소 스패닝 트리를 만들고
 * 마을을 두 개로 분할해야 하므로 가중치의 합에서 최대 가중치의 값을 뺴주면 된다.
 * 
 * 1. 가중치를 기준으로 오름차순 정렬한다.
 * 2. 가중치가 낮은 간선부터 선택하며 두 노드 간에 union 함수 실행
 * 		- 가중치가 낮은 간선부터 연결하므로 자연스럽게 가중치의 최소 합을 구할 수 있다.
 * 		- 이 때, 싸이클이 발생하면 취소하고 다음 간선 선택
 * 		- 싸이클이 발생한 것은 두 노드의 대표 노드(루트 노드)가 같을 때를 말한다.
 * 3. 간선이 N-1개가 될 때까지 2번을 반복한다.
 * 4. 해당 문제에서는 두 도시로 분할하므로 최종 가중치의 합에서 현재까지 가중치 중 가장 큰 값을 빼주면 된다.
 * 
 * @author YooSejin
 *
 */

public class BJ_1647_도시분할계획 {
	
	static int N; // 집의 개수
	static int M; // 길의 개수
	static int[][] edges; // 간선 정보
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
		
		edges = new int[M][3];
		parents = new int[N+1];
		
		for(int i=0; i<=N; i++) {
			parents[i] = i; // 초기화
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i][0] = Integer.parseInt(st.nextToken());
			edges[i][1] = Integer.parseInt(st.nextToken());
			edges[i][2] = Integer.parseInt(st.nextToken());
		}
		
		// 크루스칼 알고리즘 (간선 중심)
		// 1. 가중치를 기준으로 오름차순 정렬
		Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);
		
		int sum = 0;
		int max = Integer.MIN_VALUE;
		int cnt = 0; // 간선 연결 횟수
		
		// 2. 가중치가 작은 간선부터 차례대로 연결 (단, 사이클이 발생하면 연결하지 않는다.)
		for(int i=0; i<M; i++) {
			if(union(edges[i][0], edges[i][1])) { // 사이클이 발생하지 않는 간선만 연결한다.
				sum += edges[i][2];
				max = Math.max(max, edges[i][2]);
				cnt++;
				
				if(cnt == N-1) break; // 최소 스패닝 트리 완성 -> 빠져나온다.
			}
		}
		
		// 최소 가중치의 합에서 가장 큰 가중치를 뺸 것이 도시를 둘로 분할했을 때 가중치의 최소 합과 같다.
		System.out.println(sum - max);
	}
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false; // 루트 노드가 같으면 사이클이 발생한 것
		
		if(a <= b) parents[b] = a;
		else parents[a] = b;
		return true;
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}

}
