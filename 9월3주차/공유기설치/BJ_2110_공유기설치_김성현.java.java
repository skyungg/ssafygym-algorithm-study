import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static long[] arr;
	public static int c;
	public static int n;
	public static boolean canInstall(long mid){
		int count = 1;
		long prev = arr[0];
		for(int i=1; i<n; i++){
			if(arr[i] - prev >= mid){
				count++;
				prev = arr[i];
			}
		}
		return count >= c;

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");

		n = Integer.parseInt(input[0]);
		c = Integer.parseInt(input[1]);
		arr= new long[n];
		for(int i=0; i<n; i++){
			arr[i] = Long.parseLong(br.readLine());
		}
		Arrays.sort(arr);
		long right = arr[n-1] - arr[0];
		long left = 0;
		long result = Integer.MIN_VALUE;


		while(left <= right){
			long mid = (left + right) /2;

			if(canInstall(mid)){
				left = mid +1;
				result = Math.max(result, mid);
			}else{
				right = mid -1;
			}
		}
		System.out.println(result);
	}
}
