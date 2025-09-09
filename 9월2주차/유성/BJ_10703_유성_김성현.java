import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static char[][] arr;
	public static int r;
	public static int s;

	public static void printResult(){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<r; i++){
			for(int j=0; j<s; j++){
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void dropStone(int distance){
		// 돌을 어떻게 떨구냐..

		for(int j=0; j<s; j++){
			for(int i=r -distance -1; i>=0; i--){
				if(arr[i][j] == 'X' && arr[i+distance][j] != '#'){
					arr[i + distance][j] = 'X';
					arr[i][j] = '.';
				}
			}
		}
	}

	public static int getDistance(){
		int minValue = Integer.MAX_VALUE;
		for(int j=0; j<s; j++){
			int landIndex = -1;
			int meteorIndex = -1;
			for(int i=r -1; i>=0; i--){

				if(arr[i][j] == 'X'){

					meteorIndex = i;
					break;
				}else if(arr[i][j] == '#'){
					landIndex = i;
				}
			}
			if(meteorIndex != -1){
				int count = landIndex - meteorIndex -1;
				minValue = Math.min(count,minValue);
			}

		}

		return minValue;
	}
	public static void main(String[] args) throws IOException {
		// 유성 조각의 가장자리가 바닥에 가장 빨리 닿는 시간을 구한다.
			// 적어도 한줄 이상의 공기가 존재한다.

		// 그 시간만큼 유성 덩어리를 떨군다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		r = Integer.parseInt(input[0]);
		s = Integer.parseInt(input[1]);
		arr = new char[r][s];
		for(int i=0; i<r; i++){
			String tmp = br.readLine();
			for(int j=0; j<s; j++){
				arr[i][j] = tmp.charAt(j);
			}
		}

		int minValue = getDistance();
		// 유성은 적어도 한칸 이상 떨어진다. 따라서 minValue가 -1인 경우는 없다

		dropStone(minValue);
		printResult();


	}

}
