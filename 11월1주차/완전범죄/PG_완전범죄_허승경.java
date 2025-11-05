import java.util.*;

/*
키워드: 흔적 최소화
아이디어: 정렬, 그리디..? -> DP
dp[i][j] = 앞에서 i개 일 처리 했을 때,  b흔적값이 j일대, a의 최솟값 
*/
class Solution {
    public int solution(int[][] info, int n, int m) {
        int INF = 100000;
        int size = info.length;
        
        int [][] dp = new int [size+1][m];
        
        for(int i = 0; i <= size; i++){
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;
        for(int i = 1; i <= size; i++){
            int a = info[i-1][0];
            int b = info[i-1][1];
            for(int j = 0; j < m; j++){
                // a가 이번 차례 훔침
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a);
                
                // b가 이번 차례 훔침
                if(j + b < m){
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i-1][j]);
                }
            }    
        }
        
        int min = INF;
        for(int j = 0; j < m; j++){
            min = Math.min(dp[size][j], min);
        }
        
        if(min >= n) return -1;
        else return min;
    }
}
