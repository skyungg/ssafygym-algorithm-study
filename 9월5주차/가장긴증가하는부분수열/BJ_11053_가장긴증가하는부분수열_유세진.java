import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_11053_가장긴증가하는부분수열>
 * dp[i] = arr[i]로 끝나는 부분 수열 중 최대 길이를 저장
 * 
 * @author YooSejin
 *
 */

public class BJ_11053_가장긴증가하는부분수열 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N];
		
        st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N];
		
		for(int i=0; i<N; i++) {
			dp[i] = 1; // 1로 초기화
			for(int j=0; j<i; j++) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		
		int answer = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			answer = Math.max(answer, dp[i]);
		}
		
		System.out.println(answer);
	}

}
