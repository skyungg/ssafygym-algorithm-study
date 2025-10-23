import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1717_집합의표현>
 * 집합이 주어지고 문제 자체에서 집합을 합하고(union)
 * 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행한다고 하니
 * 유니온 파인드 문제이다.
 * find 메서드를 통해 두 원소의 루트 노드를 확인하고 루트 노드가 같다면 같은 집합인 것이다.
 * 
 * @author YooSejin
 *
 */

public class BJ_1717_집합의표현 {
	
	static int N; // 집합의 개수 N+1
	static int M; // 연산의 개수
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
		
        parents = new int[N+1];
        
        for(int i=0; i<=N; i++) {
        	parents[i] = i; // 초기화
        }
        
        StringBuilder sb = new StringBuilder();
        
        // 연산 수행
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int op = Integer.parseInt(st.nextToken());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	if(op == 0) {
        		union(a, b);
        	} else {
        		if(isUnion(a, b)) sb.append("YES\n");
        		else sb.append("NO\n");
        	}
        }
		
    	System.out.println(sb);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return; // 이미 합쳐있으면 리턴
		
		if(a < b) parents[b] = a;
		else parents[a] = b;
	}
	
	// 루트 노드를 찾는 메서드
	static int find(int a) {
		if(parents[a] == a) return a; // 자기 자신이 루트 노드인 경우
		
		return parents[a] = find(parents[a]);
	}
	
	// 같은 집합인지 확인하는 메서드
	static boolean isUnion(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return true;
		else return false;
	}

}
