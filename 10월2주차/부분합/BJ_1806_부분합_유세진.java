import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1806_부분합> - 투 포인터
 * 입력값의 범위를 보았을 때 절대 이중 for문 등을 사용할 수 없음
 * 따라서 투 포인터를 활용해 부분 수열의 합이 S보다 작으면 right 포인터를 증가시켜 수를 더해주고,
 * 합이 S보다 크거나 같으면 left 포인터를 증가시켜 짧은 수열을 만들며 길이를 비교해 나간다.
 * 처음 sum값의 초기화를 arr[0]으로 해줌에 주의!
 * 
 * @author YooSejin
 *
 */

public class BJ_1806_부분합 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 수열의 길이
        int S = Integer.parseInt(st.nextToken()); // 연속된 수들의 부분합이 S 이상이 되어야 함
        
        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int min = Integer.MAX_VALUE; // 연속된 가장 짧은 길이 (S이상의 합을 만드는 것이 불가능하면 0 출력)
        
        while(left <= right && right < N) {
        	if(sum < S) {
        		right++;
        		if(right >= N) break;
        		sum += arr[right];
        	} 
        	
        	if(sum >= S) {
        		min = Math.min(min, right - left + 1);
        		sum -= arr[left];
        		left++;
        	}
        }
        
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
	}

}
