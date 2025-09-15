import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_17779_게리맨더링2>
 * 1. 기준점 (x,y)와 경계선의 길이 d1, d2 정하기
 * 2. 각 경우에 대해 선거구 나누기
 * 		- 1번부터 4번까지의 경계선을 따로 표시해준다.
 * 		- 각 선거구의 인구수 합을 구한다.
 * 		- 5번 선거구의 인구수는 전체 인구수의 합에서 1~4번 인구수를 빼면 된다.
 * 		- 각 선거구 중 인구수의 최대/최소를 구하고 인구 차이의 최솟값을 갱신한다.
 * 
 * @author YooSejin
 *
 */

public class BJ_17779_게리맨더링2 {
	
	static int N; // 재현시의 크기
	static int[][] arr; // 각 구역의 인구수 저장
	static int[][] area; // 선거구의 번호를 저장
	static int totalSum = 0; // 전체 인구수의 합
	static int answer = Integer.MAX_VALUE; // 인구 차이의 최솟값
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
		
        arr = new int[N][N];
        area = new int[N][N];
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        		totalSum += arr[i][j];
        	}
        }
        
        // 1. 기준점 (x,y)와 경계선 길이 d1, d2 정하기
        for(int x=0; x<N; x++) {
        	for(int y=0; y<N; y++) {
        		
        		for(int d1=1; d1<N; d1++) {
        			for(int d2=1; d2<N; d2++) {
        				
        				// 경계선의 모서리가 NxN 배열 크기를 벗어나면 안됨
        				if(x + d1 + d2 >= N) continue;
        				if(y - d1 < 0 || y + d2 >= N) continue;
        				
        				devide(x, y, d1, d2);
        			}
        		}
        	}
        }
		
		System.out.println(answer);
	}
	
	// 선거구 나누기
	static void devide(int x, int y, int d1, int d2) {
		boolean[][] border = new boolean[N][N]; // 경계선을 저장하는 배열
		
		for(int i=0; i<=d1; i++) {
			border[x+i][y-i] = true; // 1번 경계선
			border[x+d2+i][y+d2-i] = true; // 4번 경계선
		}
		
		for(int i=0; i<=d2; i++) {
			border[x+i][y+i] = true; // 2번 경계선
			border[x+d1+i][y-d1+i] = true; // 3번 경계선
		}
		
		int[] sum = new int[6]; // 1~5번 선거구의 인구수의 합을 저장
		
		// 1번 선거구
		for(int i=0; i<x+d1; i++) {
			for(int j=0; j<=y; j++) {
				if(border[i][j]) break;
				sum[1] += arr[i][j];
			}
		}
		
		// 2번 선거구
		for(int i=0; i<=x+d2; i++) {
			for(int j=N-1; j>y; j--) {
				if(border[i][j]) break;
				sum[2] += arr[i][j];
			}
		}
		
		// 3번 선거구
		for(int i=x+d1; i<N; i++) {
			for(int j=0; j<y-d1+d2; j++) {
				if(border[i][j]) break;
				sum[3] += arr[i][j];
			}
		}
		
		// 4번 선거구
		for(int i=x+d2+1; i<N; i++) {
			for(int j=N-1; j>=y-d1+d2; j--) {
				if(border[i][j]) break;
				sum[4] += arr[i][j];
			}
		}
		
		// 5번 선거구
		sum[5] = totalSum;
		for(int i=1; i<=4; i++) {
			sum[5] -= sum[i];
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		// 인구가 가장 많은 선거구와 적은 선거구 구하기
		for(int i=1; i<=5; i++) {
			max = Math.max(max, sum[i]);
			min = Math.min(min, sum[i]);
		}
		
		// 인구 차이의 최솟값 갱신
		answer = Math.min(answer, max - min);
	}

}
