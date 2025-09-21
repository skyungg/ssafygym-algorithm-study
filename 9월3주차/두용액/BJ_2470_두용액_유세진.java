import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_2470_두용액>
 * 이분탐색과 투포인터 두 가지로 풀이할 수 있지만, 사실상 투포인터 문제라고 생각함.
 * 이분탐색은 가능한 범위에서 절반씩 줄여나가며 정답을 찾아야하지만, 이 문제는 인덱스를 하나씩 증가/감소시키며 답을 찾음.
 * 혹은 다른 이분탐색 풀이는 복잡하고 시간복잡도 측면에서도 투포인터보다 비효율적임.
 * 
 * @author YooSejin
 *
 */

public class BJ_2470_두용액 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N];
		
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        int left = 0;
        int right = N-1;
        int answer1 = 0;
        int answer2 = 0;
        int min = Integer.MAX_VALUE;
        
        while(left < right) {
        	int sum = arr[left] + arr[right];
        	
        	// 두 용액의 합이 0이면 즉시 종료
        	if(sum == 0) {
        		answer1 = arr[left];
        		answer2 = arr[right];
        		break;
        	}
        	
        	// 절댓값의 최소값 갱신 + 합이 0에 가장 가까운 두 수 저장
        	if(Math.abs(sum) < min) {
        		min = Math.abs(sum);
        		answer1 = arr[left];
        		answer2 = arr[right];
        	}
        	
        	if(sum < 0) {
        		left++;
        	} else if(sum > 0) {
        		right--;
        	}
        }
        
        System.out.println(answer1 + " " + answer2);
	}

}
