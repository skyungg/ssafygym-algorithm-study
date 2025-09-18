import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_2805_나무자르기> - 이분탐색
 * 입력값의 범위만 봐도 모든 경우의 수를 돌리면 안되는 것을 바로 알 수 있다.
 * 나무는 최대 100만개 있을 수 있고, 절단기의 높이는 최대 10억까지 설정할 수 있으므로
 * 100만 x 10억 -> 시간초과
 * 
 * 그렇다면 어떻게?
 * 적어도 M만큼 나무를 가져가되 높이를 최대로 해야하므로 이분탐색이 적합하다.
 * 절단기의 높이를 임의로 설정해 범위를 조절하면 된다.
 * 이분탐색을 이용하면 O(NlogH)의 시간복잡도를 가지므로 ok
 * 
 * @author YooSejin
 *
 */

public class BJ_2805_나무자르기 {
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 나무의 수
        int M = Integer.parseInt(st.nextToken()); // 필요한 나무 길이
		
        int[] tree = new int[N]; // 나무의 높이 저장
		
        int max = Integer.MIN_VALUE; // 가장 큰 나무의 높이
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	tree[i] = Integer.parseInt(st.nextToken());
        	max = Math.max(max, tree[i]);
        }
		
        // 절단기의 높이를 임의로 설정할 것임
        int low = 0;
        int high = max;
        
        while(low <= high) {
        	int mid = (low + high) / 2;
        	
        	long sum = 0; // 타입 주의 -> 절단기의 높이가 0이면 NxH = 100만x10억
        	for(int i=0; i<N; i++) {
        		if(tree[i] <= mid) continue;
        		else sum += tree[i] - mid;
        	}
        	
        	if(sum >= M) { // 필요한 나무보다 많이 잘리면 절단기의 높이를 높임. 절단기 높이의 최댓값을 구해야하므로 sum==M인 경우에도 높이를 높여본다.
        		low = mid + 1;
        	} else {
        		high = mid - 1;
        	}
        }
        
        System.out.println(low - 1);
	}

}
