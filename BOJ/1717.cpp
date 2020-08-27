#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;

int N, M, u, v, f;
int uf[1000001];

int find(int a)
{
	if (uf[a] == -1) return a;
	return uf[a] = find(uf[a]);
}

void merge(int a, int b)
{
	a = find(a);
	b = find(b);
	if (a == b) return;
	uf[a] = b;
	return;
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N >> M;
	fill(uf, uf + 1000001, -1);
	f(i, 0, M)
	{
		cin >> f >> u >> v;
		if ((f & 1))
		{
			if (find(u) == find(v))
				cout << "YES" << '\n';
			else
				cout << "NO" << '\n';
		}
		else
			merge(u, v);
	}
}