import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int[] arr = new int[n];
		input= br.readLine().split(" ");
		int maxLength = Integer.MIN_VALUE;
		for(int i=0; i< n; i++){
			arr[i] = Integer.parseInt(input[i]);
			maxLength = Math.max(arr[i], maxLength);
		}

		long start = 1;
		long end = maxLength;
		long result = 0;
		while(start <= end){
			long mid = (start + end) /2 ;
			long woodLength = 0;
			for (int j : arr) {
				long cut = (int) (j - mid);
				if(cut > 0){
					woodLength += cut;
				}
			}
			if(woodLength >= m){
				result = Math.max(result, mid);
				start = mid+1;
			}else{
				end = mid -1;
			}
		}
		System.out.println(result);
	}

}
