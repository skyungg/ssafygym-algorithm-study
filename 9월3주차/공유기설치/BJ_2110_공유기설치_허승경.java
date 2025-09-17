import java.util.*;
import java.io.*;

public class Main {
	static int N, C, mid;
	static int [] arr;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];

		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 정렬하기
		Arrays.sort(arr);
		int left = 1;		// 최소 떨어진 거리
		int right = arr[N-1] - arr[0];		// 최대 떨어진 거리
		int max_dis = 0;	// 정답
		
		while(left <= right) {
			mid = (left+right)/2;
			
			if(install()) {
				// mid로 설치 가능함
				max_dis = mid;
				left = mid+1;
			}else {
				//mid 거리 줄여야함
				right = mid-1;
			}
		}
		
		System.out.println(max_dis);

	}
	
	static boolean install() {
		int cnt = 1;	// 첫 번째 집 설치
		int end = arr[0];		// 마지막으로 설치한 집 위치
		
		for(int i = 1; i < N; i++) {
			if(arr[i] - end >= mid) {	//  마지막 설치 집으로부터 mid내에 위치 하지 않음 -> 현재 위치에 공유기 설치해야함
				end = arr[i];		//  위치 갱신
				cnt++;
			}
		}
		
		if(cnt >= C) return true;		//  mid이내에 설치 가능함.
		else return false;				// 부족 -> 현재 dis거리가 너무 멀다.
	}

}
