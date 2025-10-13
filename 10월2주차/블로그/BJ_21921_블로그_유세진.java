import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_21921_블로그>
 * X일로 길이가 고정되어 있으므로 슬라이딩 윈도우 문제임을 알 수 있다.
 * 처음에는 기간의 개수를 구하기 위해 각 방문자 수의 합을 배열에 따로 저장해 한 번 더 for문을 돌려 개수를 셌었는데
 * 기간의 개수를 1로 초기화시키면 그럴 필요가 없다.
 * 새롭게 max값이 갱신되면 처음으로 최대 방문자 수가 나온 것이므로 기간의 개수를 1로 초기화하고
 * 이미 max값과 같은 방문자 수의 합이 나오면 기간을 하나 더 카운트해주면 된다.
 * 
 * @author YooSejin
 *
 */

public class BJ_21921_블로그 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
		
        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int sum = 0; // 방문자 수의 합
        int max = 0; // 최대 방문자 수
        int cnt = 1; // 기간의 개수
        
        // 초기화
        for(int i=0; i<X; i++) {
        	sum += arr[i];
        }
        max = sum;
        
        for(int i=0; i<N-X; i++) {
        	sum -= arr[i];
        	sum += arr[i+X];
        	
        	if(sum == max) { // 최대 방문자 수가 여러 기간 동안 있는 경우
        		cnt++;
        	}
        	
        	if(sum > max) {
        		max = sum;
        		cnt = 1;
        	}
        }
        
        System.out.println(max == 0 ? "SAD" : max + "\n" + cnt);
	}

}
