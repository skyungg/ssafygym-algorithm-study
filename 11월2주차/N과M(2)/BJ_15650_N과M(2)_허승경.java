import java.io.*;
import java.util.*;

/*
 * 아이디어: 조합
 * 힌트:  중복 없이 M개를 고른 수열
 * */
public class Main {
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dfs(0, 1,  new int[M]);
		
		System.out.println(sb);
	}
	
	static void dfs(int cnt, int idx, int [] arr) {
		if(cnt == M) {
			// 조합 완성 
			for(int i = 0; i < M; i++) {
				sb.append(arr[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = idx; i <= N; i++) {
			arr[cnt] = i;
			dfs(cnt+1, i+1, arr);
		}
	}

}
