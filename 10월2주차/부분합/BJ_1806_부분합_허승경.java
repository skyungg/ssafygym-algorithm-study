import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int [] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int minLen = Integer.MAX_VALUE;
		int sum = 0;
		int start = 0;
		
		for(int end = 0; end < N; end++) {
			sum += arr[end];
			
			while(sum >= S) {
				minLen = Math.min(minLen, end-start+1);
				sum -= arr[start];
				start++;
			}
		}
		
		if(minLen == Integer.MAX_VALUE) {
			System.out.println(0);
		}else {
			System.out.println(minLen);
		}
	}

}
