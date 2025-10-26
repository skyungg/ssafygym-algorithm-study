#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// 마을 n개, 집은 m개의 길로 연결 양방향
// 각 길에는 비용이 있음. from to cost
// 적어도 한 쌍을 이룬 집까진 있음.

// 마을을 두개로 분할하고 싶음
// 분할할때는 분리된 마을 안에 집들이 다 연결되어야함.
// 마을에는 집도 하나 있어야함.

// 근데 집이 너무 많아
// 더 없애야됨.

// 길을 없애서 유지비의 합을 최소로 하고 싶음.

// 걍 모두 연결한 상태에서 가장 작은 거 고르고, 
// 가장 비용 큰거 삭제하면 되는거 아님? -> 분명 빠지는 곳이 있을듯.


int n, m; // 집은 n, 길은 m;





struct Edge
{
    int from, to, cost;
}

vector<int> parent;
vector<Edge> edges;

int find(int x)
{
    // return parent[x] == x ? x : find(parent[x]);
    if (parent[x] == x) return x;
    return parent[x] = find(parent[x]);
}

void Union(int x, int y)
{
    x = find(x);
    y = find(y);
    if (x == y) return;
    else if (x < y) parent[y] = x;
    else parent[x] = y;
}

void init()
{
    edges.resize(m);
    for (int i = 0; i < m; i++) cin >> edges[i].from >> edges[i].to >> edges[i].cost;
    parent.resize(n + 1);
    for (int i = 0; i <= n; i++) parent[i] = i;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> m;
    init();

    sort(edges.begin(), edges.end(), [](const Edge& a, const Edge& b) {
        return a.cost < b.cost;
    });

    int sum = 0;
    int max = 0;
    int cnt = 0;

    for (int i = 0; i < m; i++)
    {
        if (find(edges[i].from) == find(edges[i].to)) continue;
        Union(edges[i].from, edges[i].to);
        sum += edges[i].cost;
        max = max(max, edges[i].cost);
        cnt++;
        if (cnt == n - 1) break;
    }
    cout << sum - max << "\n";
    return 0;
}