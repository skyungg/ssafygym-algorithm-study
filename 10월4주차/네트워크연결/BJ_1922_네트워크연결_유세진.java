import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_1922_네트워크연결>
 * 이 문제는 최소 스패닝 트리(MST)에 대한 조건을 모두 만족한다.
 * 먼저 모든 정점을 연결해야 하고 가중치의 합을 최소로 해야한다.
 * 또한 무방향 그래프이고 가중치의 합을 최소로 하기 위해 사이클은 포함시키지 않는다.
 * 따라서 크루스칼 알고리즘을 이용해 풀면 된다.
 * 
 * @author YooSejin
 *
 */

public class BJ_1922_네트워크연결 {
	
	static int N; // 컴퓨터의 수
	static int M; // 연결할 수 있는 선의 수
	static Edge[] edges; // 간선 정보 저장
	static int[] parent;
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int cost;
		
		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost; // 비용을 기준으로 오름차순 정렬
		}
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
		
        edges = new Edge[M];
        parent = new int[N+1];
        for(int i=0; i<=N; i++) {
        	parent[i] = i;
        }
        
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	edges[i] = new Edge(from, to, cost);
        }
        
        Arrays.sort(edges);
        
        int answer = 0; // 모든 컴퓨터를 연결하는데 필요한 최소 비용
        int cnt = 0;
        
        for(int i=0; i<M; i++) {
        	if(union(edges[i].from, edges[i].to)) {
        		answer += edges[i].cost;
        		cnt++;
        	}
        	
        	if(cnt == N-1) break;
        }
        
        System.out.println(answer);
	}
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false; // 이미 연결되어 있거나 사이클 발생하는 경우
		
		if(a <= b) parent[b] = a;
		else parent[a] = b;
		return true;
	}
	
	static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}

}
