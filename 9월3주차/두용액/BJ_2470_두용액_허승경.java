import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int [] nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int [] result = new int[2];
		int left = 0;
		int right = N-1;
		int minSum = Integer.MAX_VALUE;	// 0에서부터 얼마나 떨어져 있는지  절댓값
		Arrays.sort(nums);		// 오름 정렬
		
		while(left < right) {
			int curSum = nums[left] + nums[right];
			
			if(Math.abs(curSum) < minSum) {
				// 절대값이 작음 -> 0에 더 가까움 -> 갱신
				minSum = Math.abs(curSum);
				result[0] = nums[left];
				result[1] = nums[right];
			}
			
			if(curSum > 0) right--;		// 0보다 큼 -> 값을 줄여나가야함
			else left++;				// 0보다 작음 -> 값을 커나가야함.
		}
		
		
		System.out.println(result[0]+" "+result[1]);

	}

}
