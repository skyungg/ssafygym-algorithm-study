// n개의 지점
// m개의 도로
// w개의 웜홀
// 도로는 방향이 없음(양방향), 웜홀은 방향이 있음
// 시작위치에서 도착위치로 가는 하나의 경로인데 웜홀은 시간이 뒤로감
// 한지점에서 출발, 시간여행을 하기 시작해서 다시 출발을 했던 위치로 돌아왔을 때
// 출발을 했을 때보다 시간이 되돌아가 있는 경우가 있는지 없는지
// 음수로 더 커질때?
// 음수가 있으니 벨만포드를 써야함.

// 잘못된 접근 ->
// 음수는 괜찮은데 웜홀이랑 길이랑 나눌 이유가 없음.
// 웜홀을 그냥 음수로 받으면 그만임.
// 한지점에서 출발하기에 플로이드 워셜은 아님.


#include <iostream>
#include <vector>
#include <climits>
using namespace std;

struct Edge {
    int from, to, cost;
}; // 간선 구조체

int tc, n, m, w; // 테스크 케이스, 지역, 도로, 가중치치
vector<Edge> edges; // 간선 테이블
vector<int> dist; // 거리 테이블

bool bellmanFord(int n) {
    dist.assign(n + 1, 0); 
    // 거리테이블블 n+1개의 행, 셀은 모두 0으로 초기화, 거리는 0인 상태
    // 모든 지역이 시작될 수 있으므로 0으로 초기화화
    for (int i = 0; i < n; i++) {
        // n번 i를 순회= 모든 지역을 확인한다.
        for (const auto& e : edges) { 
            // const auto&는 구조체인 edge를 받기 위해 사용하는 자료형
            // e : edges 는 e개수 만큼 순회한다는 뜻.
            if (dist[e.to] > dist[e.from] + e.cost) {
                dist[e.to] = dist[e.from] + e.cost;
                // dist[e.from] 간선 시작지점에 갱신된 거리 + 간선의 비용이 
                // 간선 도착지점의 거리보다 작다.
                // => 도착지점의 거리를 갱신할필요가 있다. 
                // 왜냐면 기존에 갱신한 거리보다 더 비용이 덜든다는 뜻.
                if (i == n - 1) return true; // 음수 사이클 존재
                // i == n-1(모든 지역을 돌았다는 뜻)
                // 모든 지역을 돌았다면 n-1이 아니라 n번 돌았다는 뜻임
                // 플로이드 마셜은 n-1번 돌면 최단경로가 나와야하는 알고리즘임
                // n번째 돌았다는 뜻은 사이클이 돌려졌다는 뜻임.
                // n번째에서도 to가 갱신되었다는 뜻.
            }
        }
    }
    return false;
    // n번째에서 갱신이 이뤄지지 않았고. n-1번째에서 갱신을 마지막으로
    // for문이 완료되었다는 뜻.
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    // 입력 출력 속도 향상
    // 벨만포드는 느려서 쓰는듯
    cin >> tc;
    // 테케 개수 출력
    while(tc--) {
        // 반복복 0이되면 끝남.
        edges.clear();
        // 간선 테이블 초기화
        cin >> n >> m >> w;
        // 지역, 도로, 웜홀 개수 입력
        for (int i = 0; i < m; i++) {
            int s, e, t; cin >> s >> e >> t;
            edges.push_back({s, e, t});
            edges.push_back({e, s, t});
            // 도로는 양방향으로 움직이기 때문에 from과 to를 바꿔서 한번더 입력력
        }
        for (int i = 0; i < w; i++) {
            int s, e, t; cin >> s >> e >> t;
            edges.push_back({s, e, -t});
            // 웜홀은 시간이 반대로 흐르기 때문에 음수로 입력력
        }

        if (bellmanFord(n)) cout << "YES\n"; // true라면 음수사이클을 돌리면서 시간이 되돌아감
        else cout << "NO\n"; // false라면 음수사이클을 돌리지 못해서 시간이 되돌아가지 못함
    }
    return 0;
}








// #include <iostream>
// #include <vector>
// #include <climits>
// using namespace std;

// // n개의 지점
// // m개의 도로
// // w개의 웜홀
// // 도로는 방향이 없음(양방향), 웜홀은 방향이 있음
// // 시작위치에서 도착위치로 가는 하나의 경로인데 웜홀은 시간이 뒤로감
// // 한지점에서 출발, 시간여행을 하기 시작해서 다시 출발을 했던 위치로 돌아왔을 때
// // 출발을 했을 때보다 시간이 되돌아가 있는 경우가 있는지 없는지
// // 음수로 더 커질때?
// // 음수가 있으니 벨만포드를 써야함.

// struct Edge {
//     int from, to, cost;
// };

// struct Worm {
//     int from, to, cost;
// };

// int tc, n, m, w;
// int s, e, t;
// vector<Edge> loads;
// vector<Worm> worms;
// vector<int> dist;
// bool bellmanFordLoads()
// {
//     dist.assign(n + 1, INT_MAX);
//     dist[1] = 0;
//     for(int i = 0; i < n - 1; i++)
//     {
//         for(const Edge& e : loads)
//     }
//     for(const Edge& e : loads)
//     {
//         if(dist[e.from] != INT_MAX && dist[e.from] + e.cost < dist[e.to])
//         {
//             return false;
//         }
//     }
//     return true;
// }
// bool bellmanFordWorms()
// {
//     dist.assign(n + 1, INT_MAX);
//     dist[1] = 0;
//     for(int i = 0; i < n - 1; i++)
//     {
//         for(const Worm& w : worms)
//     }
//     for(const Worm& w : worms)
//     {
//         if(dist[w.from] != INT_MAX && dist[w.from] + w.cost < dist[w.to])
//         {
//             return false;
//         }
//     }
//     return true;
// }
// bool bellmanFordAll()
// {
//     if(!bellmanFordWorms())
//     {
//         return false;
//     }
//     return bellmanFord();
// }
// int main() 
// {
//     for(int i = 0; i < tc; i++)
//     {
//         cin >> n >> m >> w;
//         for(int j = 0; j < m; j++)
//         {
//             cin >> s >> e >> t;
//             loads.push_back({{s, e, t}, {e, s, t}});
//         }
//         for(int j = 0; j < w; j++)
//         {
//             cin >> s >> e >> t;
//             worms.push_back({s, e, t});
//         }
//     }
//     if(bellmanFordAll())
//     {
//         cout << "NO" << endl;
//     }
//     else
//     {
//         cout << "YES" << endl;
//     }
// }