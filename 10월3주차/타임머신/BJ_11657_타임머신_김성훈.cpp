// 밸만포드를 사용해야함
// 그냥 작은거 고르려는 안일한 생각으로는 못품
// 가볍게 생각하지 말고 기법을알아야함.
// 음수가 가중치에 포함되어있으므로 밸만포드 = 음수 밸만포드 또는 플로이드마셜


#include <iostream>
#include <vector>
#include <climits>
using namespace std;

// 시간과 거리는 비슷하게 cost이다.
// edge는 cost를 담는다.

struct Edge {
    int from, to, cost;
}; // 버스노선 정보를 저장하는 구조체 from은 vertex, cost는 edge

int N, M; // N은 도시, M은 버스노선 수
vector<Edge> edges; // 버스노선별 걸리는 시간 저장할 자료구조
vector<long long> dist; // 도시까지의 최단기간 저장할 자료구조

bool bellmanFord() {
    // 1. 거리 배열 초기화 -> 최단기간 저장할 자료구조 초기화
    dist.assign(N + 1, LLONG_MAX); // 0번도시는 존재하지 않으므로 N+1개의 공간을 무한대로 초기화함.
    dist[1] = 0; // 시작점은 0으로 초기화 
    // 벨만포드 알고리즘은 시작점을 0으로, 나머지 모든 vertex를 무한대(여기서는 LLONG_MAX)로 초기화해서 시작함.
    // 시작점은 1번 도시로 고정되어있으므로 dist[1]은 0으로 초기화함.
    
    // 2. N-1번 반복하여 최단거리 갱신(시작점을 제외한 모든 정점을 돌아나가야 하므로 N-1번 반복하며 간선을 확인)
    for (int i = 0; i < N - 1; i++) {
        for (const Edge& e : edges) {
            if (dist[e.from] != LLONG_MAX && // 만약 시작점이 무한대가 아니고,
                dist[e.from] + e.cost < dist[e.to]) { // 시작점의 시간 + 걸리는 시간이 도착지점의 시간보다 작으면
                dist[e.to] = dist[e.from] + e.cost; // 도착지점의 시간을 시작점의 시간 + 걸리는 시간으로 갱신
            }
        }
    }
    
    // 3. 음의 사이클 검출 (N번째 반복) 갱신을 완료한 후에 모든 간선을 돌면서 음의 사이클을 검출함.
    for (const Edge& e : edges) { // 한번더 돌려봤는데 갱신이 됐음.
        if (dist[e.from] != LLONG_MAX && // 버스 시작도시가 무한대가 아닌데
            dist[e.from] + e.cost < dist[e.to]) { // 시작점의 시간 + 걸리는 시간이 도착지점의 시간보다 작으면
            return false; // 음의 사이클 존재
        }
    }
    
    return true; // 음의 사이클 없음
}

int main() {
    cin >> N >> M; // 도시와 버스노선 수 입력
    
    for (int i = 0; i < M; i++) { // 버스노선 정보 입력
        int A, B, C; // A는 시작도시, B는 도착도시, C는 걸리는 시간
        cin >> A >> B >> C;
        edges.push_back({A, B, C});
    }
    
    if (!bellmanFord()) { // false 결과면 -1 출력, 음수사이클클
        cout << -1 << endl;
        return 0;
    }
    
    for (int i = 2; i <= N; i++) { // 2번 도시부터 마지막 도시까지 최단기간 출력
        if (dist[i] == LLONG_MAX) {
            cout << -1 << endl; //llong_max ==갱신이 안됐음. 도달 불가능하다는 뜻.
        } else {
            cout << dist[i] << endl; // 최단기간 출력
        }
    }
    
    return 0;
}
