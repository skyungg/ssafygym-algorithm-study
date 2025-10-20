import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_14938_서강그라운드>
 * 이 문제는 수색범위 M까지의 거리 내에서만 아이템을 얻을 수 있다.
 * 그리고 임의의 한 정점에서 다른 모든 정점까지의 거리를 알면 아이템을 얻을 수 있는지 아닌지 알 수 있다.
 * 근데 따로 시작점이 주어지지 않았으므로 모든 정점에서 모든 정점까지의 최단 거리를 구한 후,
 * 수색이 가능한 거리인지를 파악하고 수색 범위 내에 있다면 아이템의 개수를 합쳐주면 된다.
 * 이를 각 정점별로 비교해 얻을 수 있는 최대 아이템의 개수를 구한다.
 * 즉, 플로이드워셜을 활용하면 쉽게 풀 수 있다.
 * 
 * 플로이드워셜을 사용하기 위해 2차원 배열의 초기값을 설정해주는데 주의하자.
 * INF로 초기화, (i,i)는 0으로 초기화, 이후 (i,j)의 거리 저장.
 * 
 * 그리고 2차원 배열의 타입을 long으로 해야 함에 주의하자.
 * 최단경로를 갱신하는 과정에서 arr[i][k] + arr[k][j] 의 합을 비교하게 되는데 이때 최악의 경우 INF+INF가 되면 오버플로우가 발생할 수 있다.
 * 따라서 long타입을 쓰는게 안전하다.
 * 
 * @author YooSejin
 *
 */

public class BJ_14938_서강그라운드 {
	
	static int N; // 지역의 개수 (정점의 개수)
	static int M; // 예은이의 수색범위
	static int E; // 길의 개수 (간선의 개수)
	static int[] item; // 각 지역에서 얻을 수 있는 아이템 수
	static long[][] arr; // 거리 정보 저장 (long 타입 주의)
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
		
        item = new int[N+1];
        
        // 각 지역에서 얻을 수 있는 아이템의 개수 저장
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
        	item[i] = Integer.parseInt(st.nextToken());
        }
        
        arr = new long[N+1][N+1];
        for(int i=0; i<=N; i++) {
        	Arrays.fill(arr[i], Integer.MAX_VALUE); // INF로 초기화
        	arr[i][i] = 0; // (i,i)는 0으로 초기화
        }
        
        // 거리 정보 저장
        for(int i=0; i<E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int dist = Integer.parseInt(st.nextToken());
        	arr[from][to] = dist;
        	arr[to][from] = dist;
        }
        
        // 모든 정점에서 모든 정점까지의 최단거리 구하기
        for(int k=1; k<=N; k++) {
        	for(int i=1; i<=N; i++) {
        		for(int j=1; j<=N; j++) {
        			arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
        		}
        	}
        }
        
        int answer = Integer.MIN_VALUE;
        
        // 수색범위 내에서 갈 수 있는 곳에서 최대 아이템 얻기
		for(int i=1; i<=N; i++) {
			int sum = 0;
			for(int j=1; j<=N; j++) {
				if(arr[i][j] <= M) {
					sum += item[j];
				}
				answer = Math.max(answer, sum);
			}
		}
		
		System.out.println(answer);
	}

}
