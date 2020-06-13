#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
const int MAX_M = 200001;
const int MAX_N = 200001;

int M, N, result,cnt,total;
int uf[MAX_M] = { 0, };

struct Edge {
	int u, v, w;
	Edge() :Edge(-1, -1, 0) {}
	Edge(int u, int v, int w) : u(u), v(v), w(w) {}
	bool operator<(const Edge& O) { return w < O.w; }
};

int find(int f)
{
	if (uf[f] < 0) return f;
	return uf[f] = find(uf[f]);
}

bool merge(int a, int b)
{
	a = find(a);
	b = find(b);
	if (a == b) return false;
	uf[b] = a;
	return true;
}

Edge e[MAX_N];
int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	int u, v, w;
	while (1)
	{
		cin >> M >> N;
		if (!(M | N))
			return 0;
		result = 0, cnt = 0, total = 0;
		for (int i = 0; i < N; ++i)
		{
			cin >> u >> v >> w;
			e[i] = Edge(u, v, w);
			total += w;
		}
		sort(e, e + N);
		fill(uf, uf + M+1, -1);
		for (int i = 0; ; ++i)
		{
			if (merge(e[i].u, e[i].v))
			{
				result += e[i].w;
				if (++cnt == M-1) break;
			}
		}
		cout << total - result << '\n';
	}
}
