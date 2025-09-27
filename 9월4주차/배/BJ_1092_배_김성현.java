import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
	public static ArrayList<Integer> cranes = new ArrayList<>();
	public static ArrayList<Integer> boxes = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");

		// 크레인, 박스 입력 받기
		for(int i=0; i<n; i++){
			cranes.add(Integer.parseInt(input[i]));
		}
		int m = Integer.parseInt(br.readLine());
		input = br.readLine().split(" ");
		for(int i=0; i<m; i++){
			boxes.add(Integer.parseInt(input[i]));
		}



		// 크레인, 박스 내림차순 정렬
		cranes.sort(Comparator.reverseOrder());
		boxes.sort(Comparator.reverseOrder());

		int time = 0;
		// 시간 측정
		A:while(true){
			time++;
			// 크레인 순회
			for(int i=0; i<n; i++){
				int cranePower = cranes.get(i);
				int firstBoxWeight = boxes.get(0);
				// 첫째 크레인이 첫번째 박스를 들 수 없다 -> return -1
				if(i==0 && cranePower < firstBoxWeight){
					System.out.println(-1);
					return;
				}
				// 박스 순회
				for(int j=0; j<boxes.size(); j++){
					int boxWeight = boxes.get(j);

					// 현재 크레인이 지금 박스를 들 수 있다. -> 박스 삭제, break;
					if(cranePower >= boxWeight){

						boxes.remove(j);

						if(boxes.isEmpty()){
							System.out.println(time);
							return;
						}
						break;
					}


				}
			}
		}
	}
}
