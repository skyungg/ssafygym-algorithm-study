import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * <BJ_1931_회의실배정>
 * DFS를 돌리며 가능한 회의의 최대 횟수를 갱신하기에는 주어지는 N(회의 수)의 최대값이 너무 크다.
 * 10만까지이므로 DFS 돌리면 시간초과 날 것.
 * 
 * 그렇다면 가장 많이 회의실을 배정하기 위해 그리디를 활용한다.
 * 언제 시작했는지와 상관없이 빨리 끝나면 그 뒤에 최대한 많은 시간을 확보할 수 있다.
 * 그러면 최대한 회의를 많이 배정할 수 있다.
 * 따라서 회의를 종료시간 기준으로 정렬해 이전 회의와 겹치지 않는 것을 찾아 카운트하면 최대 회의 가능 횟수가 나온다.
 * 주의할 점은 종료시간이 같으면 시작시간 기준으로 오름차순 정렬도 해줘야 한다.
 * 
 * @author YooSejin
 *
 */

public class BJ_1931_회의실배정 {
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine()); // 회의 수
        
        int[][] arr = new int[N][2]; // 회의 시간 저장
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	arr[i][0] = Integer.parseInt(st.nextToken());
        	arr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        // 회의 종료시간 기준으로 오름차순 정렬
        // 종료시간이 같으면 시작 시간 기준으로 오름차순 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
        	@Override
        	public int compare(int[] o1, int[] o2) {
        		if(o1[1] == o2[1]) {
        			return o1[0] - o2[0]; // 시작시간
        		}
        		return o1[1] - o2[1]; // 종료시간
        	}
		});
        
        int prevEnd = -1;
        int count = 0;
        
        for(int i=0; i<N; i++) {
        	int start = arr[i][0];
        	int end = arr[i][1];
        	
        	if(prevEnd <= start) { // 현재 진행 중인 회의의 끝나는 시간보다 시작 시간이 늦어야 회의실 배정 가능
        		count++;
        		prevEnd = end;
        	}
        }
        
        System.out.println(count);
	}

}
