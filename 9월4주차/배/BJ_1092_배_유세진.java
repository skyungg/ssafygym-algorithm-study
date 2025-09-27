import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_1092_배>
 * 크레인의 무게 제한과 박스의 무게를 내림차순 정렬해 무거운 것부터 이동시켜 제거하면서 최소 시간을 구하면 된다.
 * 현재 선택이 최적의 해를 만드므로 그리디 문제이다.
 * 
 * 먼저 박스를 다 옮기지 못하는 경우를 처리해준다.
 * 그렇지 않으면 while문을 계속 돌아 시간초과 난다.
 * 
 * @author YooSejin
 *
 */

public class BJ_1092_배 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine()); // 크레인 개수
        
        Integer[] limit = new Integer[N]; // 각 크레인의 무게 제한
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	limit[i] = Integer.parseInt(st.nextToken());
        }
        
        int M = Integer.parseInt(br.readLine()); // 박스의 수
        
        List<Integer> box = new ArrayList<>(); // 각 박스의 무게
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
        	box.add(Integer.parseInt(st.nextToken()));
        }
        
        Arrays.sort(limit, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());
        
        // 모든 박스를 배로 옮길 수 없으면 -1 출력
        if(box.get(0) > limit[0]) {
        	System.out.println(-1);
        	return;
        }
        
        int time = 0;
        
        // 가장 무거운 박스도 무게 제한을 넘지 않으므로 모든 박스를 다 이동시킬 수는 있음
        while(!box.isEmpty()) {
        	for(int i=0; i<N; i++) {
        		for(int j=0; j<box.size(); j++) {
        			if(box.get(j) <= limit[i]) { // 크레인에 박스를 실을 수 있는 경우
        				box.remove(j);
        				break;
        			} else { // 박스가 더 무거워 크레인에 실을 수 없으면 다음 타임에 확인
        				continue;
        			}
        		}
        	}
        	
        	time++;
        }
        
        System.out.println(time);
	}

}
