import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_2469_사다리타기>
 * 처음에는 재귀 함수를 만들어서 사다리를 타다가 감춰진 사다리가 나오면 사다리 있/없 두가지 경우의 수를 돌려서 원하는 순서를 만들 수 있는지 판단해야 하나 했다.
 * 하지만 그렇게 구현하면 함수를 만들기가 어렵고 리턴값도 애매해서 이 방법이 아닌 것 같다고 판단했다.
 * 
 * 이 문제는 위, 아래에서 감춰진 사다리까지 사다리를 타고 오고
 * 두 지점 사이의 거리를 비교해 사다리를 놓을 수 있는지 없는지 판단하면 되는 문제였다.
 * 만약 두 지점 사이의 거리가 1보다 크면 아무리 사다리를 놓아도 갈 수가 없는 곳이므로 x를 출력하면 된다.
 * 
 * 다만 사람(알파벳)을 한명씩 돌아가며 위, 아래에서 오도록 하는 코드를 구현하면
 * 양 끝의 사다리는 예외처리를 해줘야 하고 (맨 왼쪽 사다리는 왼쪽 못감. 오른쪽은 오른쪽 못감. ArrayIndexOutOfBoundsException 발생)
 * 코드가 복잡해진다.
 * 따라서 한 줄씩 내려올 때마다 모든 사람들의 위치를 결정하도록 코드를 짜는게 효율적이다.
 * 
 * @author YooSejin
 *
 */

public class BJ_2469_사다리타기 {
	
	static int K; // 참가자 수
	static int N; // 가로 막대가 놓일 가로줄 수
	static char[] start; // 사다리 위에 차례대로 놓인 참가자
	static char[] destination; // 사다리를 타고 난 후 결정된 참가자들의 순서
	static char[][] ladder; // 사다리 가로줄 정보 (*:없음 / -:있음 / ?:감춘 사다리)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		start = new char[K];
		destination = new char[K];
		ladder = new char[N][K-1];
		
		// 참가자를 A부터 차례대로 저장 (사다리 위)
		for(int i=0; i<K; i++) {
			start[i] = (char)('A' + i);
		}
		
		// 도착한 후 참가자들의 순서 저장 (사다리 아래)
		String s = br.readLine();
		for(int i=0; i<K; i++) {
			destination[i] = s.charAt(i);
		}
		
		int lineIdx = 0; // 감춰진 사다리 번호
		
		// 사다리 정보 입력받기
		for(int i=0; i<N; i++) {
			s = br.readLine();
			for(int j=0; j<K-1; j++) {
				ladder[i][j] = s.charAt(j);
			}
			
			if(ladder[i][0] == '?') lineIdx = i;
		}
		
		// 위에서부터 감춰진 곳까지 사다리 타기
		for(int i=0; i<lineIdx; i++) {
			for(int j=0; j<K-1; j++) {
				if(ladder[i][j] == '-') { // 사다리가 있으면 양쪽의 위치 바꾸기
					char tmp = start[j];
					start[j] = start[j+1];
					start[j+1] = tmp;
				}
			}
		}
		
		// 아래에서부터 감춰진 곳까지 사다리 타기
		for(int i=N-1; i>lineIdx; i--) {
			for(int j=0; j<K-1; j++) {
				if(ladder[i][j] == '-') { // 사다리가 있으면 양쪽의 위치 바꾸기
					char tmp = destination[j];
					destination[j] = destination[j+1];
					destination[j+1] = tmp;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 감춰진 사다리 구하기
		for(int i=0; i<K-1; i++) {
			// 위에서 올 때랑 아래에서 올 때랑 같은 열에 있으면 사다리를 놓을 필요 없음
			if(start[i] == destination[i]) {
				sb.append("*");
				continue;
			}
			
			// 다른 열에 있다면
			if(start[i] == destination[i+1] || start[i+1] == destination[i]) { // 칸이 한칸 차이나면 사다리를 놓을 수 있음
				sb.append("-");
				
				// 이미 사다리를 놓았는데 중복으로 놓는 것을 방지하기 위해 순서 바꿔주기
				char tmp = start[i];
				start[i] = start[i+1];
				start[i+1] = tmp;
			}
			
			else { // 2칸 이상 차이나면 사다리를 놓아도 원하는 순서를 얻을 수 없음
				sb = new StringBuilder();
				for(int j=0; j<K-1; j++) {
					sb.append("x");
				}
				break;
			}
		}
		
		System.out.println(sb);
	}
	
}
