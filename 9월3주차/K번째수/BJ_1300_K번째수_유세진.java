import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_1300_K번째수> - 이분탐색
 * N의 범위가 최대 10만까지로 이중for문을 돌리면 시간초과가 난다.
 * 그렇다면 어떻게 해야 할까?
 * 먼저 B[K] = x 의 뜻을 살펴보면 -> x보다 작거나 같은 원소가 최소 K개 있다는 뜻이다.
 * 따라서 x의 값, 즉, 배열의 원소의 값을 이분탐색으로 조정하여 해당 x보다 작거나 같은 원소의 개수를 세어 K개인 것을 찾으면 된다.
 * 이 때, 원소의 범위는 쉽게 1 ~ N*N 까지 가능하다.
 * 하지만 B배열을 살펴보면 원소의 값 x는 반드시 K보다 작거나 같다.
 * 따라서 원소의 범위를 좁히면 1 ~ K까지로 설정할 수 있다.
 * 
 * @author YooSejin
 *
 */

public class BJ_1300_K번째수 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 배열의 크기
        int K = Integer.parseInt(br.readLine()); // B[K]의 값 구하기
        
        // 범위 기준 : 배열의 원소의 값
        int left = 1;
        int right = K;
        int answer = 0;
        
        while(left <= right) {
        	int mid = (left + right) / 2; // 배열의 원소의 값(x)
        	
        	// mid(x)보다 작거나 같은 원소의 개수 구하기
        	int cnt = 0;
        	for(int i=1; i<=N; i++) {
        		cnt += Math.min(mid/i, N); // N개까지만 더할 수 있음에 주의
        	}
        	
        	if(cnt >= K) { // K가 더 작아야하므로 x의 값 줄이기
        		answer = mid;
        		right = mid - 1;
        	} else { // K가 더 커야하므로 x의 값 늘리기
        		left = mid + 1;
        	}
        }
        
		System.out.println(answer);
	}

}
