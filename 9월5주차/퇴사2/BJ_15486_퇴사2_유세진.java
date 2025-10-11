import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_15486_퇴사2> - DP
 * 이 문제는 입력값의 범위가 작다면 DFS로도 풀 수 있다.
 * 하지만 주어진 입력값이 최대 150만까지이므로 DFS로 풀면 시간초과가 나기 때문에 DP로 풀어야 한다.
 * 
 * dp[i] : i일까지의 최대 수익 저장
 * 
 * 처음에는 이중 for문을 돌려 dp[i]의 최댓값을 갱신했는데 입력값의 범위 때문에 시간초과가 났다.
 * 따라서 그 방식이 아니라 i번째 날 상담을 진행한 후 상담이 끝난 다음날의 최댓값을 갱신시켜 준다.
 * 최대 수익에 금액을 더한 것과 비교해 갱신하기 때문에 마지막 dp값을 출력하면 최대 수익이다.
 * 
 * @author YooSejin
 *
 */

public class BJ_15486_퇴사2 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine()); // 상담은 N일 동안 가능
		
        int[][] arr = new int[N+2][2]; // 상담을 완료하는데 걸리는 기간과 받는 금액 저장
        
        for(int i=1; i<=N; i++) {
        	st = new StringTokenizer(br.readLine());
        	arr[i][0] = Integer.parseInt(st.nextToken());
        	arr[i][1] = Integer.parseInt(st.nextToken());
        }
		
        // dp[i] : i일까지의 최대 수익 저장
        // 마지막 날까지 상담을 하는 경우 그 다음날 수익을 얻기 때문에 크기를 +1 해준다.
        int[] dp = new int[N+2];
        
        int max = -1;
        
        for(int i=1; i<=N+1; i++) {
        	if(max < dp[i]) max = dp[i]; // 최대 수익 갱신
        	
        	int next = i + arr[i][0]; // 수익이 발생하는 날
        	if(next > N+1) continue; // 수익 정산받는 날이 N+1을 넘어가면 안됨
        	dp[next] = Math.max(dp[next], max + arr[i][1]); // dp[i]+금액이 아니라 i번째 날까지의 최대 수익(max)에 금액을 더한 값과 비교해야 한다.
        }
        
        System.out.println(dp[N+1]);
	}

}
