import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> graph;
	static int V;
	static int [] color;	// 0: 미방문, 1: 시작, -1: 인접
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<>();		// 테스트케이스마다 그래프 초기화
			
			for(int i = 0; i <= V; i++) {
				graph.add(new ArrayList<>());
			}
		
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph.get(a).add(b);
				graph.get(b).add(a);
			}
			
			
			// 색 배열 초기화
			color = new int[V+1];
			boolean flag = true;
			
			// 모든 정점을 출발 정점으로 해서 검사하기
			for(int i = 0; i <= V; i++) {
				if(color[i] == 0) {		// 아직 검사X
					if(!bfs(i)) {
						flag = false;
						break;
					}
				}
			}
			
//			if(flag) sb.append("YES\n");
//			else sb.append("NO\n");
			sb.append(flag ? "YES\n" : "NO\n");
		}
		
		System.out.print(sb.toString());
		
	}
	
	static boolean bfs(int start) {
		Queue<Integer> que = new LinkedList<>();
		que.add(start);
		color[start] = 1;	// 시작 색
		
		while(!que.isEmpty()) {
			int p = que.poll();
			
			for(int num : graph.get(p)) {
				if(color[num] == 0) {	// 아직 칠하기 전
					color[num] = -color[p];	// 인접색은, 현재 꺼낸 색과 반대색으로 찰하기
					que.add(num);
				}else if(color[num] == color[p]) {	// 인접한데, 같은색 -> 이분 그래프 아님
					return false;
				}
			}
		}
		
		return true;
	}

}
