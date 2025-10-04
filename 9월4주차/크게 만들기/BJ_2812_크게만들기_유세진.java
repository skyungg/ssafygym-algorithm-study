import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * <BJ_2812_크게만들기> - 그리디 + 스택
 * 힌트 : i번째 숫자의 앞에는 i번째 숫자보다 큰 수만 있어야 가장 큰 수를 만들 수 있다.
 * 그리고 i번째 숫자보다 작은 앞에있는 수는 바로바로 지우는게 가장 최적의 해를 만드므로 그리디 문제이다.
 * 앞에 있는 수 확인과 제거는 스택 자료구조를 사용한다.
 * 
 * 주의할 점 !
 * K개만큼 숫자를 지우지 못하는 경우가 있을 수 있다. (ex. 1944 -> 944가 남음)
 * 즉, 스택의 크기가 N-K가 아닐 수 있으므로 크기가 N-K가 될 때까지 뒤의 작은 수는 제거한 후, StringBuilder를 구한다.
 * 
 * @author YooSejin
 *
 */

public class BJ_2812_크게만들기 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
		
        String s = br.readLine();
        
		Stack<Integer> stack = new Stack<>();
		int rmCnt = 0; // 제거 횟수
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(s.substring(i, i+1));
			
			while(!stack.isEmpty()) {
				if(rmCnt == K) break;
				
				if(stack.peek() < num) {
					stack.pop();
					rmCnt++;
				} else {
					break;
				}
			}
			
			stack.push(num);
		}
		
		// K개만큼 숫자를 지우지 못하는 경우가 있을 수 있음 -> ex. 1944
		// 즉, 스택의 크기가 N-K가 아닐 수 있으므로 크기가 N-K가 될 때까지 뒤의 작은 수는 제거한다.
		while(true) {
			if(stack.size() == N-K) break;
			stack.pop();
		}
		
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb.reverse());
	}

}
