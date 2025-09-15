import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_12100_2048_Easy> - 브루트포스, 백트래킹, 시뮬레이션
 * 문제 정리
 * - 최대 5번 이동해서 가장 큰 블록의 값을 구하는 문제.
 * - 한 번 이동할 때 상하좌우 중 한 방향으로 이동할 수 있다. 모든 블록이 해당 방향으로 이동.
 * - 한 번 이동할 때 이미 한번 합쳐지면 다시 합쳐질 수 없다.
 * - 같은 값의 블록이 3개 이상이면 이동하려고 하는 쪽의 칸이 먼저 합쳐진다.
 * 
 * 어떻게 풀 수 있을까?
 * 5번 이동하는 모든 방향의 경우의 수를 브루트포스 탐색.
 * DFS로 재귀 호출하고 5번 이동하면 리턴해서 다음 방향으로 이동시키기.
 * 		- 리턴했을 때 원래 배열의 상태로 되돌리기 위해 원본 배열을 미리 복사해두고 사용한다. (중요)
 * 최대 5번 이동했을 때 보드를 전체 탐색해 가장 큰 블록의 값을 구하고 매번 갱신한다.
 * 
 * 이동은 어떻게 구현?
 * 방향에 따라 각각 구현한다.
 * 이동한 후 어느 위치에 넣을지 확인하기 위해 index 변수를 사용하고, 가장 최근의 블록값을 저장하기 위해 block 변수를 사용한다.
 * 1) 값이 같은 두 블록이 충돌하면
 * 		- 원래 위치의 블록은 없애고
 * 		- index-1 혹은 index+1 위치에 있는 블록값은 2배 해주고
 * 		- 다음에 또 같은 값의 블록이 왔을 때 합쳐지는 것을 방지하기 위해 block값을 0 처리해준다.
 * 2) 두 블록이 충돌하지 않는다면
 * 		- 이동시킬 블록의 값을 block에 따로 저장하고
 * 		- 현재 위치의 블록은 없애고
 * 		- index 위치에 블록을 넣어준다.
 * 		- 그리고 index 증가
 * 
 * @author YooSejin
 *
 */

public class BJ_12100_2048_Easy {
	
	static int N; // 보드의 크기
	static int[][] arr;
	static int answer = Integer.MIN_VALUE; // 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		game(0);
		
		System.out.println(answer);
	}
	
	// 게임 한번 실행 : 한번의 이동
	// cnt : 현재까지 게임한(이동한) 횟수
	static void game(int cnt) {
		if(cnt == 5) {
			int max = findMax();
			answer = Math.max(answer, max);
			return;
		}
		
		// 보드를 이동 전으로 돌리기 위해 원본 배열 복사
		int[][] copy = new int[N][N];
		for(int i=0; i<N; i++) {
			copy[i] = arr[i].clone();
		}
		
		for(int dir=0; dir<4; dir++) {
			move(dir);
			game(cnt+1);
			
			// 보드를 블록 이동 전으로 다시 돌려놔야 함
			for(int i=0; i<N; i++) {
				arr[i] = copy[i].clone();
			}
		}
	}
	
	// dir 방향으로 블록 이동
	static void move(int dir) {
		switch(dir) {
		case 0: // 위
			for(int i=0; i<N; i++) { // 열
				int index = 0; // 블록을 넣을 인덱스 번호
				int block = 0; // 이동한 후의 배열에서 가장 최근 블록 값
				
				for(int j=0; j<N; j++) { // 행
					if(arr[j][i] == 0) continue; // 블록의 값이 없으면 다음 블록 확인
					
					if(arr[j][i] == block) { // 두 블록의 값이 같으면 합치기
						arr[j][i] = 0; // 원래 자리는 블록 없애고
						arr[index-1][i] *= 2; // 블록 합쳐서 값 두배로 만들고
						block = 0; // 한번 합치면 다시 합칠 수 없으니 현재 블록의 값 0 처리
					} else { // 두 블록의 값이 다르면 
						block = arr[j][i]; // 현재 블록의 값
						arr[j][i] = 0; // 원래 위치는 블록 없애고
						arr[index][i] = block; // 새로운 자리에 블록 넣기
						index++;
					}
				}
			}
			
			break;
		
		case 1: // 오른쪽
			for(int i=0; i<N; i++) {
				int index = N-1;
				int block = 0;
				
				for(int j=N-1; j>=0; j--) {
					if(arr[i][j] == 0) continue;
					
					if(arr[i][j] == block) {
						arr[i][j] = 0;
						arr[i][index+1] *= 2;
						block = 0;
					} else {
						block = arr[i][j];
						arr[i][j] = 0;
						arr[i][index] = block;
						index--;
					}
				}
			}
			
			break;
			
		case 2: // 아래
			for(int i=0; i<N; i++) { // 열
				int index = N-1;
				int block = 0;
				
				for(int j=N-1; j>=0; j--) { // 행
					if(arr[j][i] == 0) continue;
					
					if(arr[j][i] == block) {
						arr[j][i] = 0;
						arr[index+1][i] *= 2;
						block = 0;
					} else {
						block = arr[j][i];
						arr[j][i] = 0;
						arr[index][i] = block;
						index--;
					}
				}
			}
			
			break;
			
		case 3: // 왼쪽
			for(int i=0; i<N; i++) {
				int index = 0;
				int block = 0;
				
				for(int j=0; j<N; j++) {
					if(arr[i][j] == 0) continue;
					
					if(arr[i][j] == block) {
						arr[i][j] = 0;
						arr[i][index-1] *= 2;
						block = 0;
					} else {
						block = arr[i][j];
						arr[i][j] = 0;
						arr[i][index] = block;
						index++;
					}
				}
			}
			
			break;
		}
	}
	
	// 보드에서 가장 큰 블록의 값 구하기
	static int findMax() {
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				max = Math.max(max, arr[i][j]);
			}
		}
		
		return max;
	}

}
