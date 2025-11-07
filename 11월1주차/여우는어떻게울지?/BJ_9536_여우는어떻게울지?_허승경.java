import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		String [] word;
		StringBuilder sb = new StringBuilder();
		
		for(int t = 0; t < T; t++) {
			word = br.readLine().split(" ");
			List<String> list = new ArrayList<>();	// 각 동물 울음소리 저장
			
			while(true) {
				String [] str = br.readLine().split(" ");
				
				// 마무리 질문 판단
				if(str[0].equals("what")) break;
				
				list.add(str[2]);		// 동물 울음소리 같은 경우
			}
			
			// 여우 울음소리 
			for(int i = 0; i < word.length; i++) {
				if(!list.contains(word[i])) {
					sb.append(word[i]+" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
