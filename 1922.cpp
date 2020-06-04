#include <bits/stdc++.h>
using namespace std;

const int MAX_V = 1001;
const int MAX_E = 100001;

int uf[MAX_V] = { 0, };

int find(int f)
{
	if (uf[f] < 0) return f;
	return uf[f] = find(uf[f]);
}

bool merge(int a, int b)
{
	a = find(a);
	b = find(b);
	if (a == b)	return false;
	uf[b] = a;
	return true;
}

struct Edge {
	int u, v, w;
	Edge() : Edge(-1, -1, 0) {}
	Edge(const int& u, const int& v, const int& w) : u(u), v(v), w(w) {}
	bool operator <(const Edge& O) { return w < O.w; }
};
Edge e[MAX_E];

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	int V, E, u, v, w;
	cin >> V >> E;

	for (int i = 0; i < E; ++i)
	{
		cin >> u >> v >> w;
		e[i] = Edge(u, v, w);
	}
	sort(e, e + E);
	fill(uf, uf + V+1, -1);
	int result = 0, cnt = 0;

	for (int i = 0;; ++i)
		if (merge(e[i].u, e[i].v))
		{
			result += e[i].w;
			if (++cnt == V - 1)
				break;
		}
	cout << result;
}
