import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <BJ_1939_중량제한>
 * 이분탐색 + DFS
 * 
 * 섬 사이의 다리는 최악의 경우 중량을 최대 10억까지 버틸 수 있다.
 * 모든 경우의 수를 확인할 수 없으니 물품의 무게를 기준으로 이분탐색을 사용해 시작점에서 도착점까지 다리를 건널 수 있는지 확인한다.
 * 따라서 
 * 1. 물품의 무게를 임의로 설정하고 (이분탐색)
 * 		- 시작점에서 도착점까지 DFS 탐색을 돌린다.
 * 		- 다리를 건널 때 물품의 무게를 견딜 수 있고, 아직 방문하지 않은 경우 계속해서 재귀를 돌린다.
 * 		- 도착점에 도착할 수 있다면 임의의 물품 무게를 견딜 수 있는 것으로 -> 물품의 무게를 늘린다. 가능한 물품의 무게의 최댓값을 갱신한다.
 * 		- 도착할 수 없다면 -> 물품의 무게를 줄인다.
 * 
 * @author YooSejin
 *
 */

public class BJ_1939_중량제한 {
	
	static int N; // 섬의 개수
	static int M; // 다리의 개수
	static ArrayList<Island>[] graph;
	static int start;
	static int destination;
	static boolean[] visited;
	static boolean possible = false;
	
	static class Island {
		int num; // 섬 번호
		int weight; // 중량제한
		
		Island(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
		
        graph = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
        	graph[i] = new ArrayList<>(); // 초기화
        }
        
        int max = Integer.MIN_VALUE;
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int weight = Integer.parseInt(st.nextToken());
        	
        	max = Math.max(max, weight);
        	
        	graph[from].add(new Island(to, weight));
        	graph[to].add(new Island(from, weight));
        }
		
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());
        
        // 물품의 중량을 이분탐색 기준으로 사용
        int left = 1;
        int right = max;
        int answer = 0; // 물품의 중량의 최댓값
        
        while(left <= right) {
        	int mid = (left + right) / 2;
        	
        	visited = new boolean[N+1];
        	possible = false;
        	dfs(start, destination, mid);
        	
        	if(possible) { // 다리가 무너지지 않고 이동이 가능하면 물품의 가능한 중량 올리기
        		answer = Math.max(answer, mid);
        		left = mid + 1;
        	} else { // 중량제한 때문에 이동할 수 없다면 물품의 중량 줄이기
        		right = mid - 1;
        	}
        }
        
        System.out.println(answer);
	}
	
	// now : 현재 정점(현재 섬 번호)
	// target : 다음 타겟 정점(연결된 섬 번호)
	// limit : 현재 임의로 설정된 물품의 중량값
	static void dfs(int now, int target, int limit) {
		if(now == target) {
			possible = true;
			return;
		}
		
		visited[now] = true;
		
		for(Island i : graph[now]) {
			// 아직 방문하지 않았고, 물품의 중량값이 다리가 견딜 수 있는 무게보다 작거나 같아야 함
			if(!visited[i.num] && limit <= i.weight) {
				dfs(i.num, target, limit);
			}
		}
	}

}
