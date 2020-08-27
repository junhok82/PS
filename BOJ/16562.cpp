#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;
const int MAX_N = 10001;

int N, M, K;
int A[MAX_N];
int uf[MAX_N];

int find(int a)
{
	if (uf[a] == -1) return a;
	return uf[a] = find(uf[a]);
}

bool merge(int a, int b)
{
	a = find(a);
	b = find(b);
	if (a == b) return false;
	uf[a] = b;
	return true;
}

struct Edge {
	int u, v, w;
	Edge() : u(0), v(0), w(0) {}
	Edge(int u, int v, int w) : u(u), v(v), w(w) {}
	bool operator <(const Edge& o) const { return w < o.w; }
};

vector<Edge> e;

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N >> M >> K;
	int u, v, result = 0, cnt = 0;
	fill(uf, uf + MAX_N, -1);
	f(i, 0, N)
	{
		cin >> A[i];
		e.push_back(Edge(0, i+1, A[i]));
	}
	f(i, 0, M)
	{
		cin >> u >> v;
		if (merge(u, v)) cnt++;
	}
	sort(all(e));
	for(int i = 0;;++i)
	{
		if (merge(e[i].u, e[i].v))
		{
			result += e[i].w;
			if (++cnt == N || result > K) break;
		}
	}
	if(result > K) cout << "Oh no";
	else cout << result;
}