import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_2473_세용액>
 * 입력값의 범위가 크기 때문에 for문 3개를 돌릴 수 없음.
 * 그렇다고 세 개의 포인터를 움직이는 방식도 복잡하다.
 * 처음엔 양 끝에 두 개의 포인터를 두고 그 중간값을 mid로 설정해
 * 합이 음수냐 양수냐에 따라 왼쪽/오른쪽으로 mid의 포인터를 옮기며 비교하려고 했지만 이는 코드가 복잡해진다.
 * 가장 간단한 방법은 하나의 기준점을 잡고 남은 두 개를 투 포인터로 사용하는 방식이다.
 * 따라서 for문을 돌려 i를 기준으로 사용하고 i+1과 N-1을 두 포인터로 사용한다.
 * (left = i, mid = i+1, right = N-1 인 셈이다.)
 * 
 * 주의할 점은 sum 값을 구할 때 타입 캐스팅 필수!
 * int형으로 더하면 오버플로우가 발생한 후 long으로 변환되므로 더하기 전에 long으로 캐스팅 해줘야 한다.
 * 
 * @author YooSejin
 *
 */

public class BJ_2473_세용액 {

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
        
        int ans1 = 0;
        int ans2 = 0;
        int ans3 = 0;
        long min = Long.MAX_VALUE;
        
        for(int i=0; i<N-2; i++) {
        	int left = i+1;
            int right = N-1;
            
            while(left < right) {
            	long sum = (long)arr[i] + arr[left] + arr[right]; // 타입 캐스팅 필수! int형으로 더하면 오버플로우가 발생한 후 long으로 변환되므로 더하기 전에 long으로 캐스팅 해줘야 한다.
            	
            	if(Math.abs(sum) < min) { // 0에 가장 가까운 경우 갱신
            		min = Math.abs(sum);
            		ans1 = i;
            		ans2 = left;
            		ans3 = right;
            	}
            	
            	if(sum < 0) left++;
            	else if(sum > 0) right--;
            	else { // 합이 0이면 즉시 종료
            		System.out.println(arr[ans1] + " " + arr[ans2] + " " + arr[ans3]);
            		return;
            	}
            }
        }
        
        System.out.println(arr[ans1] + " " + arr[ans2] + " " + arr[ans3]);
	}

}
