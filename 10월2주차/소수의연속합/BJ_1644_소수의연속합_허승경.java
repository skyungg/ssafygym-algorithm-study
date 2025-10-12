import java.io.*;
import java.util.*;

/*
 * 아이디어: 투포인터
 * 1. N까지의 소수 구하기
 * 2. 투포인터해서 연속하는 합이 N 인 경우의 수 찾기
 * */

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		
		// 소수구하기
		boolean [] isPrime = new boolean[N+1];
		Arrays.fill(isPrime, true);
		
		isPrime[0] = false;
		isPrime[1] = false;
		
		for(int i = 2; i <= Math.sqrt(N); i++) {
			if(isPrime[i]) {
				for(int j = i*i; j <= N; j += i) {
					isPrime[j] = false;
				}
			}
		}
		
		List<Integer> list = new ArrayList<>();
		for(int i = 2; i <= N; i++) {
			if(isPrime[i]) list.add(i);
		}
		
		// 투포인터
		int left = 0;
		int right = 0;
		int sum = 0;
		int count = 0;
		
		while(true) {
			if(sum >= N) {			// N보다 많음 -> 왼쪽 늘리기
				if(sum == N)count++;
				sum -= list.get(left);
				left++;
			}else if(right == list.size()) break;		// 소수 리스트 끝까지 왔음 -> 완료
			else {							// N보다 작음 -> 오른쪽 늘리기
				sum += list.get(right);
				right++;
			}
		}
		
		System.out.println(count);
	}

}
