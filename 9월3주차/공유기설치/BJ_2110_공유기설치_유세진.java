import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_2110_공유기설치> - 이분탐색
 * 입력값의 범위를 보면 완전탐색은 시간초과난다.
 * 따라서 두 공유기 사이의 거리를 기준으로 이분탐색을 해야 한다.
 * 두 공유기 사이의 거리를 임의로 설정하고, 그랬을 때 공유기를 몇 개 설치할 수 있는지 센 다음,
 * 공유기를 주어진 개수인 C개만큼 설치할 수 있다면 그 중에서 최대 거리를 구하면 된다.
 * 
 * 주의할 점!
 * 주어진 공유기의 개수(C)가 3일 때
 * 공유기를 4개를 설치할 수 있어도 3개만 설치하면 되니까 설치할 수 있는 공유기의 개수(cnt)가 C보다 클 때에도 두 공유기 사이의 거리를 최대로 갱신시켜야 한다.
 * 즉, cnt<C / cnt>C / cnt==C 세 가지 경우로 나누는게 아니라 cnt<C / cnt>=C 두 가지 경우로만 나누면 된다.
 * 
 * @author YooSejin
 *
 */

public class BJ_2110_공유기설치 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 집의 개수
        int C = Integer.parseInt(st.nextToken()); // 공유기의 개수
        
        int[] arr = new int[N]; // 집의 위치 저장
        
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(arr);
		
        // 두 공유기 사이의 거리를 기준으로 설정함
        int left = 1;
        int right = arr[N-1] - arr[0];
        long answer = Integer.MIN_VALUE; // 인접한 두 공유기 사이의 최대 거리
		
        while(left <= right) {
        	int mid = (left + right) / 2;
        	
        	int cnt = 1; // 설치할 수 있는 공유기 개수
        	int position = arr[0]; // 현재 공유기 위치
        	
        	for(int i=1; i<N; i++) {
        		if(arr[i] >= position + mid) {
        			cnt++;
        			position = arr[i];
        		}
        	}
        	
        	if(cnt < C) { // 설치할 수 있는 공유기 개수가 주어진 개수보다 작으면 거리를 줄여서 공유기 수를 늘려야 함
        		right = mid - 1;
        	} else {
        		// 설치할 수 있는 공유기 개수가 주어진 개수보다 크면 거리를 늘려서 공유기 수를 줄인다. 단, 더 많이 설치할 수 있는 경우도 C개만 설치하면 되므로 두 공유기 사이의 거리를 최대로 갱신시킨다.
        		// 개수가 같은 경우에도 거리를 최대로 구해야하므로 거리를 늘린다.
        		answer = Math.max(answer, mid);
        		left = mid + 1;
        	}
        }
        
        System.out.println(answer);
	}

}
