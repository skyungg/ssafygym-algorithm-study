import java.io.*;
import java.util.*;

public class Main {
	
	static class Point implements Comparable<Point>{
		int num;
		int cost;
		
		public Point(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Point p) {
			return this.cost - p.cost;
		}
	}
	static int n, m, r;
	static int [] item;
	static List<List<Point>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 지역 개수
		m = Integer.parseInt(st.nextToken()); // 수색 범위
		r = Integer.parseInt(st.nextToken()); // 길의 개수
		
		item = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 	// 연결된 지역 번호
			int b= Integer.parseInt(st.nextToken()); 	// 연결된 지역 번호
			int l = Integer.parseInt(st.nextToken()); 	// 길의 길이
			
			graph.get(a).add(new Point(b, l));
			graph.get(b).add(new Point(a, l));
		}
		
		int maxValue = 0;
		for(int i = 1; i <= n; i++) {
			maxValue = Math.max(maxValue, dijkstra(i));
		}
		
		System.out.println(maxValue);
		
	}
	
	static int dijkstra(int start) {
		int [] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(start, 0));

		while(!pq.isEmpty()) {
			Point p = pq.poll();
			
			if(p.cost > dist[p.num]) continue;		// 현재 cost가 해당 위치의 최단거리보다 큼 -> 다음으로 넘어가기
			
			for(Point next : graph.get(p.num)) {
				int new_dist = p.cost + next.cost;
				if(new_dist < dist[next.num]){
					dist[next.num] = new_dist;
					pq.add(new Point(next.num, new_dist));
				}
			}
		}
		
		// 수색 범위 내에서 획득 가능한 아이템 수 구하기
		int count = 0;
		for(int i =  1; i <= n; i++) {
			if(dist[i] <= m) count += item[i];
		}
		
		return count;
	}

}
