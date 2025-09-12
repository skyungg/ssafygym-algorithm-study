import java.io.*;
import java.util.*;

/* 풀이 시간: 1h 30m ~ 2h
 * 아이디어: 구현, 시뮬레이션
 * [평상시 이동]
 * 1. 다음 위치로 머리 우선 옮기기
 *   -> 이때, 다음 칸이 범위 밖 or 자기자신 인지 확인
 * 2. 다음 위치에 사과 O -> 머리 옮기기 확정 및 꼬리 그대로
 * 3. 다음 위치 사과 X -> 머리 옭기고, 꼬리 그  앞의 위치로
 * (1: 몸 표시, 2:사과)
 * [방향 지시]
 * 1. 현재 위치에서, C방향으로 머리 방향 바꾸기
 * */
public class Main {
	
	static int N;
	static int [][] map;
	static Deque<int []> deque = new LinkedList<>();
	static int [] dx = {0, 1, 0, -1};	// 우, 하, 좌, 상
	static int [] dy = {1, 0, -1, 0};
	static int direction = 0;
	static int time = 0;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		int K = Integer.parseInt(br.readLine());
		for(int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			map[a][b] = 2;	// 사과 위치 표시
		}
		
		direction = 0;		// 초기는 오른쪽 방향
		deque.add(new int [] {0, 0});	// 초기 위치 삽입
		map[0][0] = 1;
		
		int L = Integer.parseInt(br.readLine());
		for(int l = 0; l < L; l++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());		// 초
			String dir = st.nextToken();					// 바꾸는 방향
		
			if(move(s)) {
				// s초까지 이동 성공 -> 방향전환하기
				if(dir.equals("D")) {	// 오른쪽 방향으로 90도 회전
					direction = (direction+1)%4;
				}else {					// 왼쪽 방향으로 90도 회전
//					direction = (direction-1)%4;
					if(direction == 0) direction = 3;
					else if(direction == 1) direction = 0;
					else if(direction == 2) direction = 1;
					else if(direction == 3) direction = 2;
				}
			}else {
				// 방향 전에 이미 이동 끝 -> 
				System.out.println(time);
				return;
			}
		}
		
		//  아직 안 부딪힌 경우 ->  마지막까지 이동하기
		while(true) {
			time++;
			
			int [] point = deque.peekFirst();
			int tx = point[0] + dx[direction];
			int ty = point[1] + dy[direction];
			
			if(check_range(tx, ty) && check_body(tx, ty)) {
				
				// (1) 해당 칸 사과 여부 확인하기
				if(map[tx][ty] == 2) {
					// 다음 칸이 사과인 경우 -> 사과 먹고 표시하기
					map[tx][ty] = 0;
				}else if(map[tx][ty] == 0){
					// 다음 칸이 사과가 아닌 경우 -> 꼬리 한 칸 앞으로
					int [] last = deque.removeLast();		// 현재 마지막  요소 -> 꼬리 삭제하기
					map[last[0]][last[1]] = 0;	//  바디 정보 초기화
				}
				
				// (2) 머리 위치 한 칸 앞으로 이동
				deque.addFirst(new int [] {tx, ty}); 	// 머리 삽입
				map[tx][ty] = 1;		//  이동한 머리 위치 표시
				
			}else break;			
		}
		
		
		
		
		System.out.println(time);
	}
	
	static boolean move(int limit) {
		boolean flag = true;
		
		while(time < limit) {
			time++;		// 먼저 시간 증가
			int [] head = deque.peekFirst();
			int tx = head[0] + dx[direction];		// 머리 앞 방향으로 빼기
			int ty = head[1] + dy[direction];
			
			
			// 1. 이동할 다음 위치의 범위 체크 및 자기 몸 체크
			if(check_range(tx, ty) && check_body(tx, ty)) {
				
				// (1) 해당 칸 사과 여부 확인하기
				if(map[tx][ty] == 2) {
					// 다음 칸이 사과인 경우 -> 사과 먹고 표시하기
					map[tx][ty] = 0;
				}else if(map[tx][ty] == 0){
					// 다음 칸이 사과가 아닌 경우 -> 꼬리 한 칸 앞으로
					int [] last = deque.removeLast();		// 현재 마지막  요소 -> 꼬리 삭제하기
					map[last[0]][last[1]] = 0;	//  바디 정보 초기화
				}
				
				// (2) 머리 위치 한 칸 앞으로 이동
				deque.addFirst(new int [] {tx, ty}); 	// 머리 삽입
				map[tx][ty] = 1;		//  이동한 머리 위치 표시
				
			}else return false;
		}
	
		return flag;
		
	}
	
	static boolean check_range(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	static boolean check_body(int tx, int ty) {
		if(map[tx][ty] == 1) return false;
		
		return true;
	}

}
