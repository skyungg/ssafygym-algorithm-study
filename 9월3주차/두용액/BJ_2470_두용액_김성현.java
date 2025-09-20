import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] tmp = br.readLine().split(" ");
		Long[] arr = new Long[n];
		for(int i=0; i<n; i++){
			arr[i] = Long.parseLong(tmp[i]);
		}
		Arrays.sort(arr);


		long a=-1,b = -1;
		long minValue = Integer.MAX_VALUE;
		int start = 0;
		int end = n-1;
		while(start < end){
			long sum = arr[start] + arr[end];
			if(Math.abs(sum) < Math.abs(minValue)){
				a = arr[start];
				b = arr[end];
				minValue = sum;
			}

			if(sum == 0){
				System.out.println(arr[start] + " " + arr[end]);
				return;
			}else if(sum < 0){
				start+=1;
			}else{
				end-=1;
			}

		}
		System.out.println(a + " " + b);
	}
}
