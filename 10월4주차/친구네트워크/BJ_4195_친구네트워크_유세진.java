import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <BJ_4195_친구네트워크>
 * 두 사람의 친구 네트워크에 몇 명이 있느냐는 말은 두 사람이 포함된 집합에 몇 명이 있느냐는 말이다.
 * 따라서 유니온 파인드를 이용해 합집합을 만들고 루트 노드가 같은 사람을 세어주면 되는 문제이다.
 * 다만 친구 사이가 하나 주어질 때마다 for문을 돌리면 시간초과가 날 수도 있고, 집합의 루트 노드로 변경되지 않은 parent 값도 있을 것이다.
 * 따라서 level 배열을 하나 만들어 집합을 합칠 때마다 루트 노드에 층의 개수를 바꿔 저장해준다.
 * 예를 들어 1과 2 노드를 합친다고 하면 parent[1]=1, parent[2]=1로 바뀌고
 * level[1] += level[2]를 해준다. 그러면 level[1]=2 이고 루트 노드가 1일 때 총 2개의 노드가 있다는 것이다.
 * 따라서 두 친구 노드를 합칠 때마다 루트 노드의 level 값을 출력해주면 된다.
 * 
 * @author YooSejin
 *
 */

public class BJ_4195_친구네트워크 {
	
	static int T;
	static int F;
	static Map<String, Integer> map;
	static int[] parent;
	static int[] level; // 각 노드마다 층의 개수 저장
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        T = Integer.parseInt(br.readLine());
		
        StringBuilder sb = new StringBuilder();
        
        for(int t=0; t<T; t++) {
        	F = Integer.parseInt(br.readLine());
        	
        	parent = new int[F*2 + 1];
        	level = new int[F*2 + 1];
        	
        	// 초기화
        	for(int i=0; i<=F*2; i++) {
        		parent[i] = i;
        		level[i] = 1;
        	}
        	
        	map = new HashMap<>();
        	int idx = 1; // 노드 번호로 사용
        	
        	for(int i=0; i<F; i++) {
        		st = new StringTokenizer(br.readLine());
        		String a = st.nextToken();
        		String b = st.nextToken();
        		
        		if(!map.containsKey(a)) { // 같은 사람을 중복해서 넣지 않음
        			map.put(a, idx++);
        		}
        		
        		if(!map.containsKey(b)) {
        			map.put(b, idx++);
        		}
        		
        		union(map.get(a), map.get(b));
        		sb.append(level[find(map.get(a))] + "\n");
        	}
        }
		
        System.out.println(sb);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return;
		
		if(a <= b) {
			parent[b] = a;
			level[a] += level[b];
		} else {
			parent[a] = b;
			level[b] += level[a];
		}
	}
	
	static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}

}
