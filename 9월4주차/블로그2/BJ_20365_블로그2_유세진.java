import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_20365_블로그2> - 그리디
 * 문제를 칠하는 작업 횟수가 가장 적으려면
 * 전체를 한가지 색으로 칠하고, 파랑과 빨강 중 그룹 개수가 적은 것을 새로 칠하면 된다.
 * 
 * 처음 한번 통일 된 색으로 칠하고 -> 반대 색 구간만 덧칠한다.
 * 이 전략이 항상 최적의 전략으로
 * 매 순간 최적의 선택이 최종적으로 최적의 해를 보장한다는 개념이므로 그리디 문제이다.
 * 
 * @author YooSejin
 *
 */

public class BJ_20365_블로그2 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 색을 칠해야 하는 문제의 수
		
        int bCnt = 0; // 파란 그룹의 개수
        int rCnt = 0; // 빨간 그룹의 개수
        
        String s = br.readLine();
        char prev = 'X'; // 임의의 값 설정
        
        for(int i=0; i<N; i++) {
        	char now = s.charAt(i);
        	
        	if(now != prev) {
        		if(now == 'B') bCnt++;
        		else rCnt++;
        	}
        	
        	prev = now;
        }
        
        System.out.println(Math.min(bCnt, rCnt) + 1);
	}

}
