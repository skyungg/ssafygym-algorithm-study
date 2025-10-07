import java.io.*;
import java.util.*;

/*
 * 아이디어: 하나 고정, 두 개 탐색
 * 
 * 
 * */
public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long [] arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
	
		
		// 정렬
		Arrays.sort(arr);
		
		// 구현
		long minAbsSum = Long.MAX_VALUE;	// 절대값의 최소값
		long [] num = new long[3];
		
		for(int i = 0; i < N-2; i++) {
			int left = i+1;
			int right = N-1;
			
			while(left < right) {
				long tmpSum = arr[i] + arr[left] + arr[right];
				
				if(tmpSum == 0) {		// 0과 같음
					System.out.println(arr[i] + " " + arr[left] + " "+ arr[right]);
					return;
				}
				
				if(Math.abs(tmpSum) < minAbsSum) {		// 갱신
					minAbsSum = Math.abs(tmpSum);
					num[0] = arr[i];
					num[1] = arr[left];
					num[2] = arr[right];
				}
				
				if(tmpSum < 0) {
					left++;
				}else if(tmpSum > 0) {
					right--;
				}
				
			}
		}
		
		System.out.println(num[0]+ " "+num[1] + " " + num[2]);
	
	}

}
