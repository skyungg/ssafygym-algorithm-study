import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int n = cards.length;
        boolean [] visited = new boolean[n];
        List<Integer> list = new ArrayList<>();
        
        // 모든 상자 탐색하기
        for(int i = 0; i < n; i++){
            if(visited[i]) continue;    //  이미 방문
            
            int cnt = 0;
            int cur_idx = i;
            
            // 사이클 탐색하기
            while(!visited[cur_idx]){
                visited[cur_idx] = true;    // 방문 처리
                cnt++;
                cur_idx = cards[cur_idx]-1;
            }
            
            list.add(cnt);
        }
        
        if(list.size() < 2) return 0;   // 그룹이 두 개 미만
        
        Collections.sort(list, Collections.reverseOrder());
        return list.get(0)*list.get(1); // 첫 번째 그룹에서 얻은 상자의 수 * 두 번째 그룹에서 얻은 상자의 수
    }
}
