#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <string>

using namespace std;

// 친구네트워크가 생길 때마다 몇명의 친구가 있는지 구하는 프로그램

// 첫째줄 테스트 케이스 개수
// F개 줄에 친구 관계 개수
// 친구관계는 두 사용자의 아이디

// 핵심 1) 언오더드맵을 이용해 중복되지 않은 인덱스를 부여하는 테크닉
// 2) 유일한 인덱스가 구성되었다면 유니온파인드가 안전하게 돌아갈 수 있음을 확신
// 3) 친구 관계는 유니온이 될때마다 더하면 된다는 발상이 필요.
// 4) 사이즈는 친구 명수 만큼 해야한다. 친구 숫자가 얼마나 있을지 대강 생각해야함.

vector<int> parent, sz; // sz[i] = i번 집합의 친구 수

int find(int x) {
    return parent[x] == x ? x : parent[x] = find(parent[x]);
}

void Union(int x, int y) {
    x = find(x);
    y = find(y);
    if (x == y) return;
    parent[y] = x;
    sz[x] += sz[y]; // 두 집합 친구 수를 합침
}


int main() {
    int t;
    cin >> t;
    while (t--) {
        int f;
        cin >> f;
        parent.clear(); sz.clear();
        unordered_map<string, int> idx;
        int cnt = 0;
        parent.resize(f*2); // 2명의 이름이 모두 다르면 2f만큼 필요
        sz.resize(f*2); // 2명의 이름이 모두 다르면 2f만큼 필요
        for (int i = 0; i < f*2; i++) parent[i] = i, sz[i] = 1; // 가장 작은 친구는 나 자신 1명명

        for (int i = 0; i < f; i++) {
            string name1, name2; cin >> name1 >> name2;
            if (idx.count(name1) == 0) idx[name1] = cnt++; // 사람 이름에 달린 인덱스가 없으면 새로운 cnt 부여
            if (idx.count(name2) == 0) idx[name2] = cnt++; // 사람 이름에 달린 인덱스가 없으면 새로운 cnt 부여
            int idx1 = idx[name1], idx2 = idx[name2]; // 사람 이름에 달린 인덱스 가져오기
            Union(idx1, idx2);
            cout << sz[find(idx1)] << "\n";
        }
    }
}