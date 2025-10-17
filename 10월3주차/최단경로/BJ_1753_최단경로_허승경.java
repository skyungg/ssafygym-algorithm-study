import java.io.*;
import java.util.*;

/*
 * (구) k위치에서 모든 정점 방문하는데 최단 경로값
 * 아이디어: 다익스트라
 * 
 * */
public class Main {
	static class Point implements Comparable<Point> {
	    int v;  // 도착 정점
	    int w;  // 가중치

	    public Point(int v, int w) {
	    	this.v = v;
	    	this.w = w;
	    }

	    @Override
	    public int compareTo(Point p) {
	    	return this.w - p.w;		// 올므차순 정렬
	    }
	}
	
	static int V, E;
	static List<List<Point>> graph = new ArrayList<>();		// 인접 배열
	static int [] dist;			// 각 정점가지의 최단거리
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		
		int K = Integer.parseInt(br.readLine());	// 시작 정점의 번호
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(new Point(v, w));
		}
		
		// 구현
		dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);		//  결과 배열 최댓값으로 초기화
		dijkstra(K);
		
		// 결과 출력 
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= V; i++) {
			if(dist[i] == Integer.MAX_VALUE)sb.append("INF\n");
			else sb.append(dist[i]+"\n");
		}
		
		System.out.println(sb.toString());

	}
	
	static void dijkstra(int start) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(start, 0));		// 시작점, 가중치
		dist[start] = 0;	// 자기자신은 0
		
		while(!pq.isEmpty()) {
			Point point = pq.poll();
			
			if(dist[point.v] < point.w) continue;	// 이미 방문한 경우 -> 다음으로 넘어가기 

			
			List<Point> tmp = graph.get(point.v);
			for(int i = 0; i <  tmp.size(); i++) {
				int nextV = tmp.get(i).v;		// 인접 번호
				int nextW = tmp.get(i).w;		// 해당 가중치
				
				if(dist[point.v]+nextW < dist[nextV]) {
					dist[nextV] = dist[point.v]+nextW;
					pq.add(new Point(nextV, dist[nextV]));
				}
			}
		}
	}

}
