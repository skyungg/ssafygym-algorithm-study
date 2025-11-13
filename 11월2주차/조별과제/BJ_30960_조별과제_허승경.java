import java.io.*;
import java.util.*;

/*
 * 아이디어: 정렬? + 완탐 -> DP
 * dp[i] -> i번째 학생까지 조를 지었을 때의 최소 어색함의 합
 * 마지막 두 명이 2인조 -> dp[i] = dp[i-2] + (arr[i] - arr[i-1)
 * 마지막 세 명이 3인조 -> dp[i] = dp[i-3] + (arr[i] - arr[i-2])
 * */

public class Main {
	public static void main(String [] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());		
		int [] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
        // 정렬
        Arrays.sort(arr);
        
		//초기 -> 3인조
		int sum = arr[2] - arr[0];
		for(int i = 4; i < N; i += 2) {
			sum += arr[i] - arr[i-1];
		}
		
		int minSum = sum;
		
		// 3인조 시작 -> 인덱스 두 칸씩 이동하면서 어색함 갱신
		for(int i = 2; i < N-1; i+=2) {
			sum -= (arr[i] - arr[i - 2]);
            sum -= (arr[i + 2] - arr[i + 1]);
            sum += (arr[i - 1] - arr[i - 2]);
            sum += (arr[i + 2] - arr[i]);
            
            minSum = Math.min(minSum, sum);
		}
		
		System.out.println(minSum);

	}
}
