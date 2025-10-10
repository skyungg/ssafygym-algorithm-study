import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력
		int N = Integer.parseInt(st.nextToken());		// 접시 수
		int d = Integer.parseInt(st.nextToken());		// 초밥 종류
		int k = Integer.parseInt(st.nextToken());		// 연속 접시 수
		int c = Integer.parseInt(st.nextToken());		// 쿠폰 번호
		
		int [] arr = new int[2*N];	
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			arr[i+N] = arr[i];
		}
		
		// 구현
		int [] sushi_type = new int[d+1];	// 초밥 종류
		sushi_type[c]++;		// 쿠폰 초밥 종류 1증가
		int maxCount = 1;		//  초밥 종류 수
		
		for(int i = 0; i < k; i++) {
			if(sushi_type[arr[i]] == 0) maxCount++;
			sushi_type[arr[i]]++;
		}
		
		int tmpCount = maxCount;
		for(int i = k; i < N+k; i++) {
			int pre_type = arr[i-k];		// 이전 연속 접시에서 가장 첫번째 접시의 초밥 종류
			sushi_type[pre_type]--;		//  이전 연속에서 가장 첫 번째 접시의 초밥 종류 수 감소
			
			if(sushi_type[pre_type] == 0) {		// 해당 초밥 종류가 존재 X ->  count 감소
				tmpCount--;
			}
			
			sushi_type[arr[i]]++;		// 해당 초밥 종류 증가 
			if(sushi_type[arr[i]] == 1) tmpCount++;		// 새로운 초밥 종류 추가
			
			maxCount = Math.max(maxCount, tmpCount);
		}
		
		System.out.println(maxCount);

	}

}
