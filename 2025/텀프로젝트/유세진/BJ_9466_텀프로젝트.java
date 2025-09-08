import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_9466_텀프로젝트> - DFS (dfs를 작성하는게 어려웠다. 순환 사이클 찾는 것이 어려웠음.)
 * 순환그래프이면 한팀이다.
 * 따라서 dfs를 돌려서 사이클을 찾는다.
 * 여기서 필요한 것은 한번이라도 방문했는지를 체크할 visited 방문 배열과
 * 이 노드의 탐색이 끝났는지, 사이클 판정이 끝났는지를 확인할 finished 배열 두 개이다.
 * 
 * dfs를 실행하는 도중 이미 방문했는데(visited=true) 사이클 판정은 끝나지 않은(finished=false) 노드를 다시 만난다면
 * 사이클이 발생한 것으로 해당 구간의 팀원들을 전부 카운트한다.
 * 
 * 이후 전체 학생수에서 팀에 속하는 학생 수를 빼주면 된다.
 * 
 * @author YooSejin
 *
 */

public class BJ_9466_텀프로젝트 {
	
	static int T;
	static int N;
	static int[] arr;
	static boolean[] visited; // 한번이라도 방문했는지
	static boolean[] finished; // 이 노드의 탐색이 끝났는지. 사이클 판정이 끝났는지.
	static int team; // 팀에 속하는 학생 수
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        
        for(int t=0; t<T; t++) {
        	N = Integer.parseInt(br.readLine());
        	
        	arr = new int[N+1];
        	visited = new boolean[N+1];
        	finished = new boolean[N+1];
        	
        	st = new StringTokenizer(br.readLine());
        	for(int i=1; i<=N; i++) {
        		arr[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	team = 0;
        	for(int i=1; i<=N; i++) {
        		// 이미 한번이라도 방문했으면 사이클 탐색이 끝났으니 dfs 실행하지 않는다.
        		// 예를 들어 1->3->3 탐색하는 과정에서 3번 학생을 사이클로 카운트했으니 굳이 dfs 다시 실행하지 않아도 된다.
        		if(!visited[i]) {
        			dfs(i);
        		}
        	}
        	
        	sb.append(N - team + "\n");
        }
		
		System.out.println(sb);
	}
	
	// now : 현재 학생 번호
	static void dfs(int now) {
		visited[now] = true;
		int next = arr[now]; // 다음 학생 번호
		
		if(!visited[next]) { // 다음 학생을 방문하지 않은 경우에만 dfs 호출
			dfs(next);
		} else if(!finished[next]) { // 이미 다음 학생을 방문했으나, 사이클 처리가 완료되지 않은 경우
			// 사이클이 생겼으니 팀이 확정됨. 팀원 수를 세야한다.
			team++; // 현재 학생 카운트
			for(int i=next; i!=now; i=arr[i]) { // 팀에 속하는 학생들 카운트
				team++;
			}
		}
		
		finished[now] = true; // 사이클 확정되는 과정에서 거친 학생들은 모두 true 처리
	}

}
