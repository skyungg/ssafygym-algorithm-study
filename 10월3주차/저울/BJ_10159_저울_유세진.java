import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_10159_저울> - 플로이드워셜
 * 무게가 [2]>[3], [3]>[4]가 주어지면 [2]>[4]임을 자동으로 알 수 있다.
 * 따라서 i와 j번 물건에 대해 임의의 중간점 k를 확인하면 되는 문제이다.
 * 즉, 플로이드워셜을 사용할 수 있다.
 * 이를 적용하면 i가 k보다 무겁고 k는 j보다 무겁다면 i는 j보다 무겁다.
 * 반대로 i가 k보다 가볍고 k는 j보다 가볍다면 i는 j보다 가볍다.
 * i가 j보다 무거우면 2차원 배열에 1을, 가벼우면 -1을 저장해서 비교해 줄 수 있다.
 * 이후 비교 결과를 알 수 없는 물건의 개수를 세야하므로 i번 물건에서 비교 결과가 정해지지 않은, 즉, INF값인 것만 세어주면 된다.
 * 
 * Tip : 무거우면 1, 가벼우면 -1, INF라면 순위를 알 수 없음
 * 
 * @author YooSejin
 *
 */

public class BJ_10159_저울 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine()); // 물건의 개수
        int M = Integer.parseInt(br.readLine()); // 물건 쌍의 개수
        
        long[][] arr = new long[N+1][N+1];
        
        // 초기화
        for(int i=0; i<=N; i++) {
        	Arrays.fill(arr[i], Integer.MAX_VALUE);
        	arr[i][i] = 0;
        }
		
        // 무게 비교 결과 저장
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	arr[a][b] = 1; // a가 b보다 무겁다
        	arr[b][a] = -1; // b는 a보다 가볍다
        }
        
        // 플로이드워셜을 이용해 다른 쌍의 무게 비교
        for(int k=1; k<=N; k++) {
        	for(int i=1; i<=N; i++) {
        		for(int j=1; j<=N; j++) {
        			if(arr[i][k] == 1 && arr[k][j] == 1) arr[i][j] = 1;
        			if(arr[i][k] == -1 && arr[k][j] == -1) arr[i][j] = -1;
        		}
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        
        // 비교 결과를 알 수 없는 물건의 개수 출력
        for(int i=1; i<=N; i++) {
        	int cnt = 0;
        	for(int j=1; j<=N; j++) {
        		if(arr[i][j] == Integer.MAX_VALUE) {
        			cnt++;
        		}
        	}
        	sb.append(cnt + "\n");
        }
        
        System.out.println(sb);
	}

}
