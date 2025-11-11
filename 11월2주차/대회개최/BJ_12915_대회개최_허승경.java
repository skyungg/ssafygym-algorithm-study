/*
 *  아이디어: 그리디
 * */
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int [] arr = new int[5];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 5; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0;
		
		while(true) {
			boolean isLower = false;
			boolean isMiddle = false;
			boolean isUpper = false;
			
			// 1. 쉬운 문제
			if(arr[0] > 0) {
				arr[0]--;
				isLower = true;
			}else if(arr[1] > 0) {	// 쉬운문제 단독 X -> 호환 가능한 em 확인
				arr[1]--;
				isLower = true;
			}
			
			// 2. 중간 문제
			if(arr[2] > 0) {
				arr[2]--;
				isMiddle = true;
			}else if(arr[1] > 0 || arr[3] > 0) {	// 단독 해결 X -> 호환 가능한 em, mh 확인하기
				if(arr[1] >= arr[3]) {
					arr[1]--;
					isMiddle = true;
				}else {
					arr[3]--;
					isMiddle = true;
				}
			}
			
			// 3. 어려운 문제
			if(arr[4] > 0) {
				arr[4]--;
				isUpper = true;
			}else if(arr[3] > 0) {	// 어려운 문제 단독 X -> 호환 가능한 mh 확인
				arr[3]--;
				isUpper = true;
			}
			
			// 현재 대회 가능한지 확인(모든 문제 출제 가능 여부 판단)
			if(!isLower || ! isMiddle || !isUpper) break;	// 하나라도 못 함 -> 더이상 대회 개최X
			result++;
		}
		
		// 정답 출력 
		System.out.println(result);

	}

}
