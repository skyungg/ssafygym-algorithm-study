import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <BJ_15961_회전초밥>
 * 슬라이딩 윈도우 + HashMap
 * 
 * 이 문제는 k개의 접시를 연속해서 먹기 때문에 길이가 고정되어 있으므로 슬라이딩 윈도우로 풀 수 있다.
 * 1. 먼저 연속해서 k개의 접시를 먹을 때 몇 가지 종류의 초밥을 먹는가 -> 이를 어떻게 구할 것인가?
 * 		- 이는 HashMap을 사용하면 쉽게 구할 수 있다.
 * 		- 중복되는 초밥은 map의 크기가 바뀌지 않기 때문이다.
 * 		- 또 x번 초밥의 개수를 map에 같이 저장한다. 처음엔 set을 생각했지만 그러면 연속된 초밥 중 맨 앞의 초밥을 제거할 때 중복되어 있는 초밥까지 제거될 수 있다.
 * 2. 그러면 쿠폰 번호의 초밥은 어떻게 확인할 것인가?
 * 		- 이는 map.containsKey() 메서드를 활용하면 쉽게 구할 수 있다.
 * 		- 이미 쿠폰 초밥을 포함하고 있다면 max값 갱신에 포함하지 않고, 쿠폰 초밥을 안먹었다면 +1 해주고 갱신하면 된다.
 * 
 * + 배열이 회전 형태로 되어있다고 생각해야 함에 주의할 것!!
 * 
 * @author YooSejin
 *
 */

public class BJ_15961_회전초밥 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 종류 개수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
        
        int[] arr = new int[N];
        
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0; // 초밥 종류의 최대 개수
        
        // 초기화
        for(int i=0; i<k; i++) {
        	map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
		
        max = map.size();
		if(!map.containsKey(c)) max++; // 쿠폰 초밥 추가
		
		for(int i=0; i<N; i++) {
			int remove = arr[i];
			map.put(remove, map.get(remove) - 1);
			if(map.get(remove) == 0) map.remove(remove);
			
			int add = arr[(i+k) % N]; // 회전 형태임에 주의
			map.put(add, map.getOrDefault(add, 0) + 1);
			
			if(!map.containsKey(c)) {
				max = Math.max(max, map.size() + 1);
			} else {
				max = Math.max(max, map.size());
			}
		}
		
		System.out.println(max);
	}

}
