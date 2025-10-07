import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		int [] day = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			day[i] = Integer.parseInt(st.nextToken());
		}
		
		// 구현
		int sum = 0;
		for(int i = 0; i < X; i++) {
			sum += day[i];		// 초기 X일까지 합
		}
		
		int result = sum;
		int count = 1;
		
		for(int i = X; i < N; i++) {
			sum += day[i] - day[i-X];	 // + 그 다음 값 - 가장 첫 날값
			
			if(sum > result) {
				result = sum;
				count = 1;
			}else if(sum == result) {
				count++;
			}
		}
		
		// 출력
		if(result == 0) {
			System.out.println("SAD");
		}else {
			System.out.println(result);
			System.out.println(count);
		}	
	}

}
