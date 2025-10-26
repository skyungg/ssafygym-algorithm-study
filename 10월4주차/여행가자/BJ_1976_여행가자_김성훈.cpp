#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// 한국 도시 N개
// 도시 사이 길이 있을지도 없을지도
// 연결 여부가 주어지고, 속한 도시들을 여행가능 여부 판별
// 같은도시 여러번 방문도 가능
// 연결만 되어있는지만 판단

vector<int> parent;
vector<int> plan;
vector<vector<int>> graph;
int n, m;

void parent_init()
{
    parent.resize(n + 1);
    for(int i = 0; i <= n; i++) parent[i] = i;
    graph.resize(n + 1, vector<int>(n + 1));
    plan.resize(m + 1, 0);
}

int find(int x)
{
    if(parent[x] == x) return x;
    return parent[x] = find(parent[x]);
}

void Union(int x, int y)
{
    x = find(x);
    y = find(y);
    if(x == y) return;
    else if ( x < y ) parent[y] = x;
    else parent[x] = y;
}

int main()
{
    cin >> n >> m;
    parent_init();
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            cin >> graph[i][j];
            if (graph[i][j] == 1) Union(i, j);
        }
    }
    for (int i = 1; i <= m; i++)
    {
        cin >> plan[i];
    }
    for (int i = 1; i < m; i++)
    {
        if (find(plan[i]) != find(plan[i+1]))
        {
            cout << "NO\n";
            return 0;
        }
    }
    cout << "YES\n";
    return 0;
}