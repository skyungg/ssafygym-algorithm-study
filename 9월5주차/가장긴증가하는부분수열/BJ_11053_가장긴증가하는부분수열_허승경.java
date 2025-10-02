import java.io.*;
import java.util.*;

/*
 * 아이디어: dp
 * dp[i] -> A[i]를 마지막으로 하는 가장긴증가하는부분수열의 길이
 * */
public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		int N = Integer.parseInt(br.readLine());
		int [] A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// 구현
		int [] dp = new int[N];
		int answer = 1;		// 정답, 초기값 1
		for(int i = 0; i < N; i++) {
			dp[i] = 1;	// 자기 자신 1개 존재하니까, 초기값으로 설정
			int standard_num = A[i];
			
			for(int j = 0; j < N; j++) {
				if(A[j] < standard_num) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			
			answer = Math.max(answer, dp[i]);
		}
		
		
		// 출력
		System.out.println(answer);
		
	}

}
