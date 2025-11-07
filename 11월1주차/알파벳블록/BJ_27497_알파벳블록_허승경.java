import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Deque<String> dq = new LinkedList<>();
		Stack<Integer> stack = new Stack<>();
		StringTokenizer st;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());	// 번호
			
			if(op == 1) {
				String str = st.nextToken();
				// 맨 뒤에 블록 추가
				dq.add(str);
				stack.push(1);
				
			}else if(op == 2) {
				String str = st.nextToken();
				// 맨 앞에 블록 추가
				dq.addFirst(str);
				stack.push(2);
			}else {
				if(!stack.isEmpty()) {
					int idx = stack.pop();
					if(idx == 1 && !dq.isEmpty()) dq.pollLast();
					else if(idx == 2 && !dq.isEmpty()) dq.pollFirst();
				}
			}
		}
		
		// 출력
		if(dq.isEmpty()) {
			System.out.println(0);
		}else {
			StringBuilder sb = new StringBuilder();
			
			for(String s : dq) sb.append(s);
			System.out.println(sb);
		}

	}

}
