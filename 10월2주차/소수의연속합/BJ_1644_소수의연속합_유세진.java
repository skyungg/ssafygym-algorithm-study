import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <BJ_1644_소수의연속합>
 * 소수 알고리즘(에라토스테네스의 체) + 투 포인터
 * 
 * 1. 연속된 소수의 합으로 N을 만들어야 하기 때문에 N까지의 수 중에서만 소수를 뽑으면 된다.
 * 2. 소수를 어떻게 구별하느냐? -> 에라토스테네스의 체 활용(시간초과 방지)
 * 		- 소수인지 아닌지를 저장하는 boolean 타입의 배열 생성
 * 		- i의 배수는 소수가 될 수 없으므로 false로 바꾸기 위해 true로 초기화
 * 		- 0과 1은 소수가 될 수 없으므로 false로 초기화
 * 		- 2부터 N까지의 수를 소수인지 판별
 * 		- 단, 2부터 루트N(Math.sqrt(N))까지만 for문을 돌리면 된다. (루트N 포함)
 * 		- 예를 들어 N=120이라면 11^2 > 120 이므로 11보다 작은 수의 배수들만 지워도 충분하다. 11보다 큰 수의 배수는 이미 false 처리가 되어있다.
 * 3. 소수만 뽑아서 투 포인터를 활용해 N을 만들 수 있는 경우의 수를 구한다.
 * 
 * 주의할 점 : 입력값이 1일 때 -> 소수가 들은 list에서 값을 불러올 때 인덱스 에러 발생
 * 다른 방법은 list에 소수를 저장할 때 임의로 0을 하나 더 저장해서 인덱스 에러를 방지하는 것이다.
 * 
 * @author YooSejin
 *
 */

public class BJ_1644_소수의연속합 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 입력값의 범위가 1부터 시작. 하지만 어떤 소수를 합해도 1은 만들 수 없으므로 빠르게 답 출력 가능.
        // 또 예외 처리를 안하면 1까지는 소수가 없으므로 list가 비게 되는데 조회하는 과정에서 인덱스 에러가 생긴다.
        if(N == 1) {
			System.out.println(0);
			return;
		}
		
		boolean[] isPrime = new boolean[N+1];
		Arrays.fill(isPrime, true); // 임의의 수의 배수라면 소수가 될 수 없고, 이를 false로 바꾸기 위해 true로 초기화
		isPrime[0] = false;
		isPrime[1] = false;
		
		// 2부터 N까지의 수를 소수인지 판별
		for(int i=2; i<=Math.sqrt(N); i++) { // 루트 N까지만 확인하면 됨. 왜냐하면 그 이상은 이미 앞에서 배수로 제거가 됨.
			if(isPrime[i]) { // 이미 소수가 아니라고 판정된 수는 배수도 소수가 아니라고 판별되어 있음. 따라서 소수인 수만 확인하면 됨.
				for(int j=i*i; j<=N; j+=i) { // i*i 전의 수 중 배수인 수는 이미 소수가 아니라고 판별났기 때문에 두 번 확인할 필요가 없음. 왜냐면 이미 i-1번째 수까지 배수들을 다 확인했기 때문.
					isPrime[j] = false; // i의 배수는 소수가 될 수 없음
				}
			}
		}
		
		// 소수만 리스트에 저장
		List<Integer> list = new ArrayList<>();
		for(int i=2; i<=N; i++) {
			if(isPrime[i]) {
				list.add(i);
			}
		}
		
		int left = 0;
		int right = 0;
		int sum = list.get(0);
		int cnt = 0; // 연속된 소수의 합으로 N을 만들 수 있는 경우의 수
		
		while(left <= right && right < list.size()) {
			if(sum < N) {
				right++;
				if(right >= list.size()) break; // 인덱스 에러 방지
				sum += list.get(right);
			}
			
			else {
				if(sum == N) cnt++;
				sum -= list.get(left);
				left++;
			}
		}
		
		System.out.println(cnt);
	}
	
}
