/*
 * 아이디어: dp
 * dp[i] -> i번째 값을 마지막으로 하는 가장 긴 수열의 길이
 * 정답-> 전체 길이 - 가장 긴 수열의 길이
 * -> 남은 얘들만 자리 바꾸면 되니까!
 * */
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int [] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int [] dp = new int[N];
		int maxLen = 1;
		for(int i = 0; i < N; i++) {
			dp[i] = 1;
			int num = arr[i];
			
			for(int j = 0; j < N; j++) {
				if(arr[j] < num) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			
			maxLen = Math.max(maxLen, dp[i]);
		}
		
		System.out.println(N - maxLen);
		
	}

}
