/*
 * 아이디어: dp
 * 문제 요점 -> 주어진 추로 주어진 구슬 무게 측정 가능함?
 * 포인트: 측정 가능 여부를 양팔저울이용해서 알아내면 됨
 * -> 양쪽에 구슬이나 추를 올려서 수평이 되도록하면 알아낼 수 있음
 * -> 가능한 모든 조합 탐색해서
 * -> 만들 수 있는 무게 차이 미리 만들기
 * -> 구슬의 무게가 그 차이에 존재하면 Y, 없으면 N
 * */

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int [] weight;
	static boolean[][] dp;		// i번째 추까지 사용해서 무게차이 diff를 만들 수 있음.
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		weight = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());	// 추 무게 
		}
		
		dp = new boolean[N+1][15001];	// 최대 무게시 -> 500*30
		
		dfs(0, 0);	// 무게차이 만들러가기 (idx가 0번째인 구슬부터, 무게차이 0부터 시작)
		
		int M = Integer.parseInt(br.readLine());		// 구슬 개수
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int marble = Integer.parseInt(st.nextToken());	// 구슬 무개
			if(marble > 15000) {			// 구슬의 무게가 만들수 있는 무게차이의 최댓값을 벗어남 -> 확인 불가
				sb.append("N ");
			}else {
				if(dp[N][marble]) sb.append("Y ");		// N개의 추를 모두 고려했을 경우 무게차이가 구슬의 무게인 경우 -> 가능
				else sb.append("N ");
			}
		}
		
		// 출력
		System.out.println(sb.toString());
		
	}
	
	static void dfs(int idx, int diff) {
		// diff: 왼족이 오른쪽보다 몇 g 무거운가
		
		if(dp[idx][diff]) return;		// 이미 처리한 상태 -> 끝
		dp[idx][diff] = true;		// 처리 확인
		if(idx == N) return;		// 마지막 구슬까지 완료 -> 끝
		
		/*
		 * 다음 세 가지 경우를 고려함.
		 * 1. 추를 왼쪽에 올리는 경우
		 * 2. 추를 오른쪽에 올리는 경우
		 * 3. 추를 아예 올리지 않는 경우
		 * */
		
		if(diff + weight[idx] <= 15000) dfs(idx + 1, diff + weight[idx]);		// 추를 왼족에 올리는 경우
		int abs_diff = Math.abs(diff - weight[idx]);
		if(abs_diff <= 15000) dfs(idx + 1, abs_diff);			// 추를 오른쪽에 올리는 경우
		dfs(idx + 1, diff);		// 추를 사용하지 않는 경우
		
	}
	
}
