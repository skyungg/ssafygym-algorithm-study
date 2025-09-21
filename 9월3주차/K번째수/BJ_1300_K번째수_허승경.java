import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		long left = 1;
		long right = (long)N*N;
		
		long result = 0;
		while(left <= right) {
			long mid = (left + right) / 2;
			
			if(count(mid, N) >= K) {		// mid 이하의 수가 K개 이상임 -> 작게 만들어야 한다.
				result = mid;
				right = mid - 1;
			}else left = mid + 1;
		}
		
		System.out.println(result);

	}
	
	static long count(long mid, int N) {
		long cnt = 0;
		for(int row = 1; row <= N; row++) {
			cnt += Math.min(N,  mid/row);		// 현재 행에서 mid 이하의 원소의 개수 구하기
		}
		
		return cnt;
	}
}
