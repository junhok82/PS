#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;
const int MAX_N = 4000001;
const int INF = 0x07fffffff;

int N, K, r, c;
int m[2001][2001];
int uf[MAX_N];
int dy[] = { 0,1,0,-1 };
int dx[] = { 1,0,-1,0 };
queue<pii> q_merge;
queue<pii> q_spread;

int find(int a)
{
	if (uf[a] < 0) return a;
	return uf[a] = find(uf[a]);
}

bool merge(int a, int b)
{
	a = find(a);
	b = find(b);
	if (a == b) return false;
	if (uf[a] > uf[b])
		uf[b] = a;
	else
		uf[a] = b;
	return true;
}

void bfs_merge()
{
	while (q_merge.size())
	{
		const pii& p = q_merge.front();
		int y = p.first;
		int x = p.second;
		q_merge.pop();
		q_spread.push(pii(y, x));

		f(k, 0, 4)
		{
			int ny = y + dy[k];
			int nx = x + dx[k];
			if (ny < 0 || ny >= N || nx < 0 || nx >= N || !m[ny][nx]) continue;
			if (merge(m[ny][nx], m[y][x])) K--;
		}
		if (K <= 1) return;
	}
}

void bfs_spread()
{
	while (q_spread.size())
	{
		const pii& p = q_spread.front();
		int y = p.first;
		int x = p.second;
		q_spread.pop();

		f(k, 0, 4)
		{
			int ny = y + dy[k];
			int nx = x + dx[k];
			if (ny < 0 || ny >= N || nx < 0 || nx >= N || m[ny][nx]) continue;
			m[ny][nx] = m[y][x];
			q_merge.push(pii(ny, nx));
		}
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N >> K;
	f(i, 0, K)
	{
		cin >> r >> c;
		q_merge.push(pii(r - 1, c - 1));
		m[r - 1][c - 1] = i + 1;
	}
	fill(uf, uf + MAX_N, -1);
	int ans = 0;

	while (1)
	{
		bfs_merge();
		if (K <= 1)
		{
			cout << ans;
			return 0;
		}
		bfs_spread();
		ans++;
	}
}