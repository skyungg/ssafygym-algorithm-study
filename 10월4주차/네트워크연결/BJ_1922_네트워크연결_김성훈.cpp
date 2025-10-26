#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// 컴터와 컴퓨터 연결
// p2p로 연결
// 연결 비용 최소
// 모든 컴퓨터 연결 최소비용
// 모든 컴터는 모두 연결 가능능

// union find로 모두 연결
// kruskal 알고리즘 사용

vector<int> parent;

int n, m;

struct Edge
{
    int from, to, cost;
}

int find(int x)
{
    return parent[x] == x ? x : find(parent[x]);
}

void Union(int x, int y)
{
    x = find(x);
    y = find(y);
    if (x == y) retrun;
    else if (x < y ) parent[y] = x;
    else parent[x] = y;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    cin >> n >> m;
    parent.resize(n + 1);
    for (int i = 0; i <= n; i++) parent[i] = i;
    while(m--)
    {
        int a, b, c;
        cin >> a >> b >> c;
        edges.push_back({a, b, c});
    }
    sort(edges.begin(), edges.end(), [](const Edge& a, const Edge& b) {
        return a.cost < b.cost;
    });
    int sum = 0;
    int cnt = 0;
    for (const auto& edge : edges)
    {
        if (find(edge.from) == find(edge.to)) continue;
        Union(edge.from, edge.to);
        sum += edge.cost;
        cnt++;
        if (cnt == n - 1) break;
    }
    cout << sum << "\n";
    return 0;
}