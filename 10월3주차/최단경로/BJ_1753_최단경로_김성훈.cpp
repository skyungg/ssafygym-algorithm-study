// 그래프가 주어지면 시작점에서 모든 정점으로의 최단경로
// 간선의 가중치는 10이하의 자연수
// 벨만포드 이용하여 최단경로 구하기? 음수가 없으면 굳이 쓸 필요 없다함.
// 시작점은 랜덤으로 주어짐

// 음수가 아니므로 다익스트라를 해야하는데 연습해야함.

#include <iostream>
#include <vector>
#include <queue>
#include <climits>
using namespace std;

struct Edge {
    int to, cost; //도착점, 비용만 입력
};

int main() {
    int V, E, K; // 정점, 엣지 개수, 시작정점 번호호
    cin >> V >> E >> K; // 입력

    vector<vector<Edge>> graph(V + 1); //2차원 벡터로 v+1개의 행의의 셀에다가가 {to, cost} 입력
    for (int i = 0; i < E; i++) { // 간선 개수만큼 순회
        int u, v, w; cin >> u >> v >> w; // 입력
        graph[u].push_back({ v, w }); // from, to, cost 입력력
    }

    vector<int> dist(V + 1, INT_MAX); // v+1 행의 거리 테이블 생성, v+1개의 셀에 큰수로 초기화. 
    dist[K] = 0; // 시작점 거리는 0으로 초기화

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    // pq 생성, int, int로 페어 {int, int} 1개, vector{int,int} 하나로 pq에 들어갈 자료형
    // greater<pair<int, int>로 정렬될 자료형 및 정렬 조건 선언언
    pq.push({ 0, K });
    // 시작거리 0와 시작지점 K를 입력하고 시작작

    while (!pq.empty()) { // pq가 다 빠질 때까지
        int cost = pq.top().first;
        // cost = 0
        int here = pq.top().second;
        // 시작지점 = k
        pq.pop(); // 삭제

        if (dist[here] < cost) continue; 
        // 만약 dist[k] < 0이면 패스함. 그러나 같음

        for (auto& e : graph[here]) {
            // graph[k]가 가진 간선 개수만큼 순회함.
            int next = e.to;
            // e.to 다음 행선지
            int ncost = cost + e.cost;
            // 다음 행선지의 cost는 현재 코스트 0에 다음 행선지 cost
            if (ncost < dist[next]) {
                dist[next] = ncost;
                // 비교하여 다음 행선지 거리가 더 작으면 dist테이블을 갱신하고
                pq.push({ ncost, next });
                // pq에 입력함.
            }
        }
    }

    for (int i = 1; i <= V; i++) {
        // 다익스트라 후 모든 지역 순회
        if (dist[i] == INT_MAX) cout << "INF\n";
        // 갱신이 안된 곳이 있으면 inf라 도달하지 못함.
        else cout << dist[i] << "\n";
        // 갱신 된 곳은 출력
    }
}







// #include <iostream>
// #include <vector>
// #include <climits>
// #include <algorithm>

// using namespace std;

// struct Edge {
//     int from, to, cost;
// };

// // 그래프가 주어지면 시작점에서 모든 정점으로의 최단경로
// // 간선의 가중치는 10이하의 자연수
// // 벨만포드 이용하여 최단경로 구하기? 음수가 없으면 굳이 쓸 필요 없다함.
// // 시작점은 랜덤으로 주어짐

// int n, e; // 1 <= n <= 20000, 1 <= e <= 300000

// int k; // 시작정점 번호

// int u, v, w; // u에서 v로 가는 가중치 w, u와 v는 다름. 

// vector<Edge> edges;
// vector<int> dist;

// bool bellmanFord()
// {
//     dist.assign(v + 1, INT_MAX);
//     dist[k] = 0;

//     for(int i = 0; i < v - 1; i++)
//     {
//         for(const Edge& e : edges)
//         {
//             if(dist[e.from] != INT_MAX && dist[e.from] + e.cost < dist[e.to])
//             {
//                 dist[e.to] = dist[e.from] + e.cost;
//             }
//         }
//     }
//     for(const Edge& e : edges)
//     {
//         if(dist[e.from] != INT_MAX && dist[e.from] + e.cost < dist[e.to])
//         {
//             return false;
//         }
//     }
//     return true;
// }

// int main()
// {
//     cin >> n >> e;
//     cin >> k;
//     for (int i = 0; i < e; i++)
//     {
//         cin >> u >> v >> w;
//         edges.push_back({u, v, w});
//     }
//     if(bellmanFord())
//     {
//         for(int i = 1; i <= v; i++)
//         {
//             if(dist[i] == INT_MAX)
//             {
//                 cout << "INF" << endl;
//             }
//             else
//             {
//                 cout << dist[i] << endl;
//             }
//         }
//     }
// }