#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;
const int MAX_N = 2250001;
const int INF = 0x07fffffff;

int R, C, sy1, sx1, sy2, sx2;
char m[1501][1501];
bool visited[1501][1501];
int uf[MAX_N];
queue<pii> q_water, q_union;
int dy[] = { 0,1,0,-1 };
int dx[] = { 1,0,-1,0 };

int find(int a)
{
	if (uf[a] < 0) return a;
	return uf[a] = find(uf[a]);
}

void merge(int a, int b)
{
	a = find(a);
	b = find(b);
	if (a == b) return;
	if (uf[a] > uf[b])
		uf[b] = a;
	else
		uf[a] = b;
}

void bfs_merge()
{
	while (q_union.size())
	{
		const pii& p = q_union.front();
		int y = p.first;
		int x = p.second;
		q_union.pop();
		q_water.push(pii(y, x));

		f(k, 0, 4)
		{
			int ny = y + dy[k];
			int nx = x + dx[k];
			if (ny < 0 || ny >= R || nx < 0 || nx >= C || m[ny][nx] == 'X') continue;
			merge(y * C + x, ny * C + nx);
		}
	}
}

void bfs_water()
{
	while (q_water.size())
	{
		const pii& p = q_water.front();
		int y = p.first;
		int x = p.second;
		q_water.pop();

		f(k, 0, 4)
		{
			int ny = y + dy[k];
			int nx = x + dx[k];
			if (ny < 0 || ny >= R || nx < 0 || nx >= C || m[ny][nx] != 'X') continue;
			m[ny][nx] = '.';
			q_union.push(pii(ny, nx));
		}
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> R >> C;
	bool flag = false;
	f(i, 0, R) f(j, 0, C)
	{
		cin >> m[i][j];
		if (m[i][j] != 'X')
		{
			if (m[i][j] == 'L')
			{
				if (flag) sy1 = i, sx1 = j;
				else sy2 = i, sx2 = j, flag = true;
			}
			q_union.push(pii(i, j));
		}
	}
	fill(uf, uf + MAX_N, -1);
	int ans = 0;
	while (true)
	{
		bfs_merge();
		if (find(sy1 * C + sx1) == find(sy2 * C + sx2))
		{
			cout << ans;
			return 0;
		}
		bfs_water();
		ans++;
	}
}