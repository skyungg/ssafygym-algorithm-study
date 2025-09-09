import java.io.*;
import java.util.*;

public class Main {
	static int R, S;
	static char [][] map;
	static int max_row;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		map = new char[R][S];
		
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j  = 0 ; j < S; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '#') {
					max_row = Math.max(max_row, i);		// 가장 밑 행과 가까이 위치한 행 위치 찾기
				}
			}
		}
		
		int dis = drop();
		
		// 유성 움직이기
		for(int r = R-1; r >= 0; r--) {
			for(int j = 0; j < S; j++) {
				if(map[r][j] == 'X') {
					if(r+dis < R) {
						map[r+dis][j] = 'X';
						map[r][j] = '.';
					}
				}
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < S; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	static int drop() {
		// 한줄 씩 이동하면서 하기 
		int min_dis = R+1;	//  가장 작은 길이차
		
		for(int c = 0; c < S; c++) {
			int ground = R+1;
			int metor = -1;

			for(int r = 0; r < R; r++) {
				if(map[r][c] == '#') {		// 가장 가까운 땅의 높이
					ground = r;
					break;
				}
				if(map[r][c] == 'X') metor = r;	// 현재 열에서 가장 마지막까지 있는 유성의 행 위치 
			}
			
			if(metor == -1) continue;	// 현재 열에 유성 존재X
			
			if(ground == R+1) ground = R-1;	//  땅 존재X -> 마지막 행으로 위치하기
			
			min_dis = Math.min(min_dis,  ground - metor-1);		// 가장 차이 적은 값 구하기
			
		}
		
		return min_dis;
	}

}
