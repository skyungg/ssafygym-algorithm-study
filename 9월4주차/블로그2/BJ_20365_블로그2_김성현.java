import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String input = br.readLine();

		//한번씩 쭉 그음
		int result1 = 1;
		int result2 = 1;

		//파란색을 쭉 긋는 경우
		char prev = ' ';
		for(int i=0; i<n; i++){
			char current = input.charAt(i);
			if((prev == ' ' || prev == 'B') && current == 'R'){
				result1++;
			}
			prev = current;
		}

		prev = ' ';
		for(int i=0; i<n; i++){
			char current = input.charAt(i);
			if((prev == ' ' || prev == 'R') && current == 'B'){

				result2++;
			}
			prev = current;
		}

		System.out.println(Math.min(result1, result2));


	}
}
