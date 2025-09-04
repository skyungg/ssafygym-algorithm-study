import java.io.*;
import java.util.*;

public class Main {
	static int [] arr;
	static boolean [] visited, done;
	static int count;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			arr = new int[n+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i < n+1; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			visited = new boolean[n+1];		// 현재 사이클 탐색할 때 방문 배열
			done = new boolean[n+1];		// 전체 영역에서 탐색 종료 확인용 배열
			
			count = 0;
			for(int i = 1; i <= n; i++) {
				if(!visited[i]) dfs(i);
			}

			sb.append((n-count)+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void dfs(int num) {
		visited[num] = true;	// 현재 사이클 진입 확인
		
		int next_num = arr[num];	// 다음 사람
		
		if(!visited[next_num]) {	// 그 다음 사람으로 파도 타기
			dfs(next_num);
		}else if(!done[next_num]) {
			for(int i = next_num; i != num; i = arr[i]) {	// 사이클에 해당되는 사람 수
				count++;
			}
			count++;	// 자기자신
		}
		
		done[num] = true;
	}
}
