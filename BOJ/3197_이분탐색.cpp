#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;
const int MAX_N = 2250001;
const int INF = 0x07fffffff;

int R, C;
char m[1501][1501];
int water[1501][1501];
bool visited[1501][1501];
pii s;
queue<pii> wq;
int dy[] = { 0,1,0,-1 };
int dx[] = { 1,0,-1,0 };

int water_bfs()
{
	int cnt = 0, sz;
	while (sz = wq.size())
	{
		f(i, 0, sz)
		{
			const pii& p = wq.front();
			int y = p.first;
			int x = p.second;
			wq.pop();

			f(k, 0, 4)
			{
				int ny = y + dy[k];
				int nx = x + dx[k];
				if (ny < 0 || ny >= R || nx < 0 || nx >= C || visited[ny][nx] || m[ny][nx] != 'X') continue;
				visited[ny][nx] = true;
				water[ny][nx] = cnt + 1;
				wq.push(pii(ny, nx));
			}
		}
		cnt++;
	}
	return cnt - 1;
}

bool bfs(int depth)
{
	queue<pii> q;
	q.push(s);
	visited[s.first][s.second] = true;
	
	while (q.size())
	{
		const pii& p = q.front();
		int y = p.first;
		int x = p.second;
		q.pop();

		f(k, 0, 4)
		{
			int ny = y + dy[k];
			int nx = x + dx[k];
			if (ny < 0 || ny >= R || nx < 0 || nx >= C || water[ny][nx] > depth || visited[ny][nx]) continue;
			if (m[ny][nx] == 'L') return true;
			visited[ny][nx] = true;
			q.push(pii(ny, nx));
		}
	}
	return false;
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> R >> C;
	f(i, 0, R) f(j, 0, C)
	{
		cin >> m[i][j];
		if (m[i][j] != 'X')
		{
			if (m[i][j] == 'L') s = pii(i, j);
			wq.push(pii(i, j));
		}
	}
	
	int s = 0, e = water_bfs();
	while (s <= e)
	{
		f(i, 0, R) fill(visited[i], visited[i] + C, false);
		int mid = (s + e) / 2;
		if (bfs(mid))
			e = mid - 1;
		else
			s = mid + 1;
	}
	cout << s;
}