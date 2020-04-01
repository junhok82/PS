#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;
const int MAX_N = 100001;
const int INF = 0x07fffffff;

int G, P, g;
int uf[MAX_N];

int find(int a)
{
	if (uf[a] < 0) return a;
	return uf[a] = find(uf[a]);
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> G >> P;
	fill(uf, uf + MAX_N, -1);
	int cnt = 0;
	f(i, 0, P)
	{
		cin >> g;
		g = find(g);
		if (g == 0) break;
		uf[g] = g - 1;
		cnt++;
	}
	cout << cnt;
}