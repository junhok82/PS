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
bool visited[1501][1501];
queue<pii> q2;
pii s;
int dy[] = { 0,1,0,-1 };
int dx[] = { 1,0,-1,0 };

int bfs()
{
	queue<pii> q1;
	q1.push(s);
	visited[s.first][s.second] = true;
	int cnt = 0, y, x, ny, nx;

	while (true)
	{
		queue<pii> temp;
		while (q1.size())
		{
			const pii& p = q1.front();
			y = p.first;
			x = p.second;
			q1.pop();

			f(k, 0, 4)
			{
				ny = y + dy[k];
				nx = x + dx[k];
				if (ny < 0 || ny >= R || nx < 0 || nx >= C || visited[ny][nx]) continue;
				if (m[ny][nx] == 'L') return cnt++;
				else if (m[ny][nx] == 'X') temp.push(pii(ny, nx));
				else q1.push(pii(ny, nx));
				visited[ny][nx] = true;
			}
		}
		q1 = temp;
		int sz = q2.size();
		f(i, 0, sz)
		{
			const pii& p = q2.front();
			y = p.first;
			x = p.second;
			q2.pop();

			f(k, 0, 4)
			{
				ny = y + dy[k];
				nx = x + dx[k];
				if (ny < 0 || ny >= R || nx < 0 || nx >= C || m[ny][nx] != 'X') continue;
				m[ny][nx] = '.';
				q2.push(pii(ny, nx));
			}
		}
		cnt++;
	}
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
			q2.push(pii(i, j));
		}
	}
	cout << bfs();
}