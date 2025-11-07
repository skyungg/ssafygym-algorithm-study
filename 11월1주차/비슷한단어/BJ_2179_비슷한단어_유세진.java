import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_2179_비슷한단어>
 * 처음에는 입력 순서대로 단어에 인덱스를 부여해 map에 저장하고 단어는 정렬를 해서 비교했는데
 * 그러면 공통 접두사의 길이가 같은 경우에 문제가 생겼다.
 * 예를 들어 aaaa, aaab, aabd, aabc가 있으면
 * aaa의 공통 접두사의 길이가 3이라 두 단어의 인덱스 번호를 저장하고 이후 aab의 공통 접두사의 길이도 3이라 두 단어를 저장하게 되는 것이다.
 * 이런 상황 등을 고려하고 입력 순서를 유지하기 위해서는 그냥 주어진 순서대로 비교하는 것이 가장 안전하다.
 * 따라서 i번째 단어와 j번째 단어(j=i+1부터 N까지)를 비교하며 공통 접두사의 길이가 가장 크다면 가장 입력이 빠른 단어이므로 두 단어를 저장해 출력하면 된다.
 * 
 * Tip) 완탐이기 때문에 시간복잡도가 우려된다. 이는 가지치기를 통해 시간을 최적화할 수 있다!
 * 1308ms -> 200ms
 * 
 * @author YooSejin
 *
 */

public class BJ_2179_비슷한단어 {
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        String[] arr = new String[N];
        
        for(int i=0; i<N; i++) {
        	arr[i] = br.readLine();
        }
        
        String S = "";
        String T = "";
        int max = 0; // 가장 긴 공통 접두사의 길이
        
        for(int i=0; i<N; i++) {
        	String now = arr[i];
        	if(now.length() <= max) continue; // 시간 최적화 : 가지치기 -> 어차피 최대 공통 접두사의 길이보다 단어의 길이가 짧거나 같으면 새로 갱신하지 못한다.
        	
        	for(int j=i+1; j<N; j++) {
        		String next = arr[j];
        		if(next.length() <= max) continue; // 시간 최적화 : 가지치기 -> 어차피 최대 공통 접두사의 길이보다 단어의 길이가 짧거나 같으면 새로 갱신하지 못한다.
        		int len = getPrefixLength(now, next); // 공통 접두사의 길이 구하기
        		
        		if(max < len) {
        			max = len;
        			S = now;
        			T = next;
        		}
        	}
        }
        
        System.out.println(S);
        System.out.println(T);
	}
	
	static int getPrefixLength(String now, String next) {
    	int cnt = 0; // 공통 접두사의 길이
    	int len = Math.min(now.length(), next.length());
    	
    	for(int j=0; j<len; j++) {
    		if(now.charAt(j) != next.charAt(j)) break;
    		cnt++;
    	}
		
    	return cnt;
	}

}
