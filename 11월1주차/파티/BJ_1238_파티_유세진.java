import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_1238_파티> - 플로이드워셜
 * 각 i번 마을에서 X번 마을까지 갔다가 돌아오는 길의 최단거리를 구해야 한다.
 * 그리고 각 마을마다의 최단거리 중 가장 큰 값을 출력하면 되는 문제이다.
 * 그렇다면 모든 마을에서 모든 마을까지의 최단거리를 구하면 필요한 데이터를 모두 얻을 수 있다.
 * 따라서 플로이드워셜을 사용하고 N의 최댓값이 1,000이므로 O(N^3)도 가능하다.
 * 
 * @author YooSejin
 *
 */

public class BJ_1238_파티 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
		
        long[][] arr = new long[N+1][N+1];
        
        // 초기화
        for(int i=0; i<=N; i++) {
        	Arrays.fill(arr[i], Integer.MAX_VALUE);
        	arr[i][i] = 0;
        }
		
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	arr[from][to] = cost;
        }
        
        for(int k=1; k<=N; k++) {
        	for(int i=1; i<=N; i++) {
        		for(int j=1; j<=N; j++) {
        			arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
        		}
        	}
        }
        
        int max = Integer.MIN_VALUE;
        
        for(int i=1; i<=N; i++) {
        	if(i == X) continue;
        	
        	int sum = (int)(arr[i][X] + arr[X][i]);
        	max = Math.max(max, sum);
        }
        
        System.out.println(max);
	}

}
