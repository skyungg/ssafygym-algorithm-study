import java.io.*;
import java.util.*;

/*
 * 아이디어: dp
 * dp[i][w] -> i번째 물건에서 현재 배낭 무게가 한도가 w일때, 가장 최대 가치
 * */

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, K;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());		// 물건 개수
		K = Integer.parseInt(st.nextToken());		// 최대 무게 
		
		int [][] arr = new int[N+1][2];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());		// 물건 무게
			arr[i][1] = Integer.parseInt(st.nextToken());		// 가치 
		}
		
		// 구현
		int [][] dp = new int[N+1][K+1];
		for(int i = 1; i <= N; i++) {
			for(int w = 1; w <= K; w++) {
				dp[i][w] = dp[i-1][w];			// 우선, i번째 물건은 선택 안한 값(=이전 값)으로 초기화
				
				if(arr[i][0] <= w) {			// i번째 물건이 배낭 한도 무게보다 가벼운 경우 -> 짐 추가 가능
					dp[i][w] = Math.max(dp[i][w], dp[i-1][w-arr[i][0]]+arr[i][1]);	// i번째 물건 안 넣은 경우 최대 가치 vs 넣었을 때 중 최대 가치			
				}
			}
		}
		
		// 출력
		System.out.println(dp[N][K]);

	}

}
