/*
	123.280mb	/ 472 ms
*/


import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 나무 수
		long M = Integer.parseInt(st.nextToken());	// 가져갈 나무 합
		
		long [] tree = new long[N];
		long start = 0;
		long end = -1;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			end = Math.max(end,  tree[i]);
		}
		
		
		long maxHeight = 0;		// 절단기 최대 높이
		while(start <= end) {
			long mid = (start + end)/2;
			
			long sum = 0;
			for(int i = 0; i < N; i++) {
				if(tree[i] - mid > 0 ) sum += (tree[i]-mid);
			}
			
			if(sum >= M) {			// 목표치 달성 -> 절단기 높이 좀 더 올리기
				maxHeight = mid;
				start = mid+1;
			}else {					// 부족 -> 절단기 높이 내리기
				end = mid-1;
			}
		}
		
		System.out.println(maxHeight);
	}

}
