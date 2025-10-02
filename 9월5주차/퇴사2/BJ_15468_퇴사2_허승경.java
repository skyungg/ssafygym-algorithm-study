import java.io.*;
import java.util.*;

/*
 * 아이디어: dp
 * dp[i]: i일부터 N일까지 얻을 수 있는 최대 수익
 * */
public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		int N = Integer.parseInt(br.readLine());
		int [][] arr = new int[N+1][2];
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());		// 오늘 걸리는 시간
			arr[i][1] = Integer.parseInt(st.nextToken());		// 오늘 받는 금액
		}
		
		// 구현
		int [] dp = new int[N+2];
		
		for(int i = N; i >= 1; i--) {
			if(i+arr[i][0] <= N+1) {	// 오늘 상담이 퇴사일까지 가능한 경우
				dp[i] = Math.max(arr[i][1] + dp[i+arr[i][0]],			// 오늘 상담 수익 + 오늘로부터 끝난 날의 상담수익
						dp[i+1]);		// 오늘 상담 안했을 때
			}else {
				dp[i] = dp[i+1];	// 상담 불가능 -> 내일 상담수익으로 셋팅
			}
		}
		
		
		// 출력
		System.out.println(dp[1]);
		
	}

}
