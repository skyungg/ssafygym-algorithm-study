import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1976_여행가자>
 * 여행 경로가 같은 집합이기만 하면 어떻게든 들릴 수 있다.
 * 즉, 유니온 파인드로 같은 집합인지 판별하는 문제이다.
 * 
 * DFS 같은걸로 생각해보면 원하는 도시를 찾은 후에 다시 되돌아가서 다른 도시를 찾고 할 수도 있으므로 매우 복잡하다.
 * 그리고 N도 최대 200까지라 DFS는 안하는게 맞다고 생각한다.
 * 
 * @author YooSejin
 *
 */

public class BJ_1976_여행가자 {
	
	static int N; // 도시의 수
	static int M; // 여행 계획에 속한 도시들의 수
	static int[][] arr; // 도시의 연결 정보
	static int[] city; // 방문해야 하는 도시들
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        parents = new int[N+1];
        for(int i=0; i<=N; i++) {
        	parents[i] = i;
        }
		
        arr = new int[N+1][N+1];
        
        for(int i=1; i<=N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=1; j<=N; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        for(int i=1; i<=N; i++) {
        	for(int j=i; j<=N; j++) {
        		if(arr[i][j] == 1) {
        			union(i, j);
        		}
        	}
        }
        
        city = new int[M];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
        	city[i] = Integer.parseInt(st.nextToken());
        }
        
        String answer = "YES";
        
        for(int i=0; i<M-1; i++) {
        	if(isUnion(city[i], city[i+1])) continue;
        	else answer = "NO";
        }
        
        System.out.println(answer);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return;
		
		if(a <= b) parents[b] = a;
		else parents[a] = b;
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean isUnion(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return true;
		return false;
	}

}
