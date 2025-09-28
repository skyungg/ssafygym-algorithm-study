import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
class Meeting {
	int s;
	int e;

	public Meeting(int s, int e) {
		this.s = s;
		this.e = e;
	}

	public int getS() {
		return s;
	}

	public int getE() {
		return e;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		ArrayList<Meeting> arr = new ArrayList<>();
		for(int i=0; i<n; i++){
			String[] tmp = br.readLine().split(" ");
			arr.add(new Meeting(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])));
		}

		arr.sort(new Comparator<Meeting>() {
			@Override
			public int compare(Meeting o1, Meeting o2) {
				if(o1.e != o2.e){
					return o1.e - o2.e;
				}
				return o1.s - o2.s;
			}
		});
		int count = 0;
		int prevStart = -1;
		int prevEnd = -2;
		for(Meeting m: arr){
			int currentStart = m.getS();
			int currentEnd = m.getE();
			if(currentStart >= prevEnd && currentEnd >= prevEnd){
				prevEnd = m.getE();

				count++;
			}
		}
		System.out.println(count);
	}
}
