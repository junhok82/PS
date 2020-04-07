#include<bits/stdc++.h>
using namespace std;

#define FOR(i,n) for(int i = 0; i < n; ++i)
typedef pair<int, pair<int, int>> piii;

const int MAX_N = 20;

int N, result, sy, sx, level, cnt;
int m[MAX_N][MAX_N];
int dy[] = { -1,0,1,0 };
int dx[] = { 0,-1,0,1 };
int num[7] = { 0, };

bool bfs(int r, int c)
{
	FOR(i, 7)
	{
		if (num[i + 1])
			break;
		else if (i == 6)
			return false;
	}
	priority_queue<piii, vector<piii>, greater<piii>> pq;
	bool visited[MAX_N][MAX_N] = { false, };
	pq.push({ 0,{ r,c } });
	visited[r][c] = true;
	while (pq.size())
	{
		const piii& p = pq.top();
		int y = p.second.first;
		int x = p.second.second;
		int w = p.first;
		if (m[y][x] < level && m[y][x] && m[y][x] != 9)
		{
			result += w;
			if (level == ++cnt)
			{
				level++;
				cnt = 0;
			}
			num[m[y][x]]--;
			m[r][c] = 0;
			m[y][x] = 9;
			sy = y; sx = x;
			return true;
		}
		pq.pop();
		FOR(k, 4)
		{
			int ny = y + dy[k];
			int nx = x + dx[k];
			if (ny < 0 || ny >= N || nx < 0 || nx >= N || m[ny][nx] > level || visited[ny][nx])	continue;
			visited[ny][nx] = true;
			pq.push({ w + 1,{ ny,nx } });
		}
	}
	return false;
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N;
	result = 0;
	level = 2;
	cnt = 0;
	FOR(i, N) FOR(j, N)
	{
		cin >> m[i][j];
		if (m[i][j] == 9) { sy = i; sx = j; }
		else if (m[i][j])
			num[m[i][j]]++;
	}
	while (bfs(sy, sx))
		continue;
	cout << result;
}