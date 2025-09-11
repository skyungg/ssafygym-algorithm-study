import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_10703_유성>
 * 처음에는 모든 유성 조각의 정보를 큐에 넣어 한칸씩 내리고 다시 큐에 위치 정보를 넣는 것을 반복했음
 * 하지만 이 방식은 3,000 x 3,000 에 (배열) 최대 3,000번 내려갈 수 있으므로 최악의 경우 27억번의 연산을 수행함
 * 따라서 시간 초과가 났음
 * 
 * 이 문제는 유성을 한칸씩 내리는 작업을 반복할 필요가 없음
 * 즉, 큐에 넣고 빼고 하는 작업을 반복할 필요가 없음
 * 유성 조각의 위치 정보를 list에 한번만 저장하고, 내리는 칸 수를 하나씩 증가시키며 몇 칸까지 내려갈 수 있는가를 확인만 하면 됨
 * 내려갈 수 있는 칸 수를 구한 다음, 한꺼번에 내린 후 출력하면 됨
 * 
 * @author YooSejin
 *
 */

public class BJ_10703_유성 {
	
	static class Node {
		int x;
		int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 세로
		int M = Integer.parseInt(st.nextToken()); // 가로
		
		char[][] arr = new char[N][M];
		List<Node> list = new ArrayList<>(); // 유성의 위치 정보 저장
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				arr[i][j] = s.charAt(j);
				if(arr[i][j] == 'X') {
					list.add(new Node(i, j));
				}
			}
		}
		
		boolean flag = true;
		int cnt = 1; // 내려갈 수 있는 칸 수
		
		while(flag) {
			for(int i=0; i<list.size(); i++) {
				Node now = list.get(i);
				
				int nx = now.x + cnt;
				int ny = now.y;
				
				if(arr[nx][ny] == '#') {
					flag = false;
					break;
				}
			}
			
			if(!flag) break; // 유성이 땅에 닿으면 while문 종료
			cnt++;
		}
		
		cnt -= 1;
		
		// 유성 수직 이동 (밑에서부터 해야함. 안그러면 내려온 유성 조각이 없어질 수 있음.)
		for(int i=list.size()-1; i>=0; i--) {
			Node now = list.get(i);
			arr[now.x][now.y] = '.';
			arr[now.x + cnt][now.y] = 'X';
		}
		
		// 유성 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
