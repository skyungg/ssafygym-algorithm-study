/*
 * 아이디어: 구현 -> 거꾸로 타구 올라가기 
 * 			  -> ? 줄을 기준으로 (위쪽 -> 물음표)+ (아래 -> 물음표) 이렇게 
 * 1. ?줄을 기준으로 진행
 * 	(1) 위쪽에서 물음표까지 타고 내려가기
 * `(2) 아래에서 물음표 행까지 타고 올라가기
 * 2. 각각 구현을 끝냈을 때 현재 어느 열에 있는지 판단
 * 	-> 같은 열 -> 그냥 쭉 내려오면 됨.
 *  -> 다른 열 -> 이어줘야 함. (이때, 1차이어야 이어줄 수 있음)
 *  -> 둘 다 안 됨 -> 이 경우는 불가능
 *  
 *  가록 막대 없음 -> ‘*’(별)문자
 *  가로 막대 있음 -> ‘-’(빼기)문자
 *  감추어진 특정 가로 줄은 길이 k-1 -> ‘?'
 * */

import java.io.*;
import java.util.*;

public class Main {
	static int k, n;
	static char [] order;
	static char [][] map;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		n = Integer.parseInt(br.readLine());
		
		order = br.readLine().toCharArray();	// 도착순서
		
        map = new char[n][n];
        int target = 0;	// ? 행
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
            if (line.charAt(0) == '?') target = i; // ? 줄 위치 기록
        }
        
        
        // 1. 위쪽에서 -> '?' 행까지 내려오기
        char[] upper = new char[k];
        
        for (int i = 0; i < k; i++) upper[i] = (char)('A' + i);
        for (int i = 0; i < target; i++) {
            for (int j = 0; j < k - 1; j++) {
                if (map[i][j] == '-') {		// 가로 막대 
                    char tmp = upper[j];
                    upper[j] = upper[j + 1];
                    upper[j + 1] = tmp;
                }
            }
        }

        // 2. 가장 아래 -> '?' 까지 거꾸로 올라가기
        char[] lower = order.clone();
        
        for (int i = n - 1; i > target; i--) {
            for (int j = 0; j < k - 1; j++) {
                if (map[i][j] == '-') {
                    char tmp = lower[j];
                    lower[j] = lower[j + 1];
                    lower[j + 1] = tmp;
                }
            }
        }
        
        // 3. 청산하기
        char [] result = new char[k-1];
        Arrays.fill(result, '*');
        boolean flag = true;
        
        for (int j = 0; j < k - 1; j++) {
            if (upper[j] == lower[j]) {		// 그냥 내려오면 됨
                continue;
            } else if (upper[j] == lower[j + 1] && upper[j + 1] == lower[j]) {	// 하나 어긋남
            	result[j] = '-';
                j++; // 현재 j해결하니까, j+1도 자동적으로 해결함
            } else {
            	flag = false;		// 노답 -> 얘는 어떻게 해도 불가능함 
                break;
            }
        }

        if (!flag) {
            for (int j = 0; j < k - 1; j++) System.out.print("x");
        } else {
            for (int j = 0; j < k - 1; j++) System.out.print(result[j]);		// 하나라도 어긋나면 지금까지 한 거 다 써야함
        }
	}

}
