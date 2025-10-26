#include <iostream>
#include <vector>

using namespace std;

// 초기에 n + 1개의 집합 {0}, {1}, {2}, ... {n}이 있다.
// 합집합 연산, 같은 집합 확인 연산을 수행해야함.
// 첫줄 n, m;
// m은 입력으로 주어지는 연산의 개수
// m개의 줄에 각각이 연산이 주어짐
// 0 a b : a가 포함된 집합과 b가 포함된 집합을 합친다.
// 1 a b : a와 b가 같은 집합에 포함되어 있는지 확인하는 연산
// 1로 시작되는 입력에 대해 a, b가 같은 집합이면 yes, 그렇지 않으면 no

// n = 5 일 경우
// {0}, {1}, {2}, {3}, {4}, {5} 의 초기집합임
// 0 1 2 : {1}과 {2}를 합침
// 1 1 2 : no 인지 yes인지 확인
// 1 <= n <= 1,000,000
// 1 <= m <= 100,000

// a와 b는 같을 수 있음.

int n, m, op, a, b;
vector<int> parent;


void init() {
    parent.resize(n + 1); // 벡터 초기화
    for (int i = 0; i <= n; i++) { 
        parent[i] = i; // 부모 노드 초기화
    }
}

int find(int x) { // 부모노드 찾기
    if (parent[x] == x) return x; // 자기자신이 부모면 끝
    return parent[x] = find(parent[x]); // 아니면 재귀로 한번더
}

void Union(int x, int y) {
    int rootx = find(x); // x의 부모노드
    int rooty = find(y); // y의 부모노드
    if (rootx == rooty) return; // 부모노드가 같으면 끝
    if (rootx < rooty) parent[rooty] = rootx; // rootx가 rooty보다 작으면 rooty의 부모노드를 rootx로 설정
    else parent[rootx] = rooty; // rootx가 rooty보다 크면 rootx의 부모노드를 rooty로 설정
}

int main() {
    cin >> n >> m;
    init(); // 초기화
    for (int i = 0; i < m; i++) {
        cin >> op >> a >> b;
        if (op == 0)
        {
            Union(a, b); // 트리로 연결은 합집합과 유사
        }
        else if (op == 1) {
            if ( a == b) cout << "YES" << endl;
            else if (find(a) == find(b)) cout << "YES" << endl; // 부모노드가 같으면 같은 집합
            else cout << "NO" << endl; // 부모노드가 다르면 다른 집합
        }
    }
}