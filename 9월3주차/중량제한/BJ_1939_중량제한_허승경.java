import java.io.*;
import java.util.*;

/*
 * 아이디어: 이분 탐색  + 그래프 탐색
 * 1. 이분탐색으로 적절한 무게 선정
 * 2. 1에서 정한 무게로 출발지점 -> 도착지점 이동 가능한지 보기 
 * 3. 통과한다면 -> 1의 무게 증가
 *    통과 못 한다면 -> 1의 무게 감소
 * */

public class Main {
	static int N, M, start, end;
	static List<List<int[]>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int left = Integer.MAX_VALUE;
		int right = 0;
	
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new int[] {b, c});
			graph.get(b).add(new int[] {a, c});
			
			left = Math.min(left, c);		// 무게 최솟값 및 최댓값 구하기
			right = Math.max(right, c);
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		int result = 0;
		
		while(left <= right) {
			int mid = (left+right)/2;	//  무게값 구하기
			 
			if(bfs(mid)) {
				// 모든 다리 건널 수 있음 -> 무게값 늘리기
				left = mid + 1;
				result = mid;
			}else {
				// 모든 다리 건널 수 없음 -> 무게값 줄이기
				right = mid -1;
			}
		}
		
		// 정답 출력 
		System.out.println(result);

	}
	
	static boolean bfs(int weight) {
		Queue<Integer> que = new LinkedList<>();
		que.add(start);	// 시작점

		boolean [] visited = new boolean[N+1];		// 전부 방문 가능한지 여부
		visited[start] = true;
		
		while(!que.isEmpty()) {
			int cur_num = que.poll();
			
			if(cur_num == end) return true;		// 목표지점 도달 
			
			for(int [] list : graph.get(cur_num)) {
				int next_num = list[0];
				int limit = list[1];
				
				if(!visited[next_num] && limit >= weight) {	// 아직 방문전, 제한된 중량보다 해당 다리 사이의 거리 중량이 더 큰경우 -> 가능
					visited[next_num] = true;
					que.add(next_num);
				}
			}
		}
		
		return false;
	}

}
