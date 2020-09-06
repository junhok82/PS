#include<bits/stdc++.h>
using namespace std;

#define FOR(x,n) for(int x = 0; x < n; ++x)
typedef pair<int, int> pii;

int row, col, G, R, siz, result;
int m[51][51];
bool visited[11];
vector<pii> throw_pos;
pii green[5];
pii red[5];
int dy[] = { 0,1,0,-1 };
int dx[] = { 1,0,-1,0 };

int bfs()
{
	queue<pii> rq;
	queue<pii> gq;
	int checked[51][51] = { 0, };	// 0 : 빈곳 1 : green, 2 : red, 3 : flower, 4 : 변질된 green

	FOR(i,G)
	{
		const pii& p = green[i];
		gq.push(p);
		checked[p.first][p.second] = 1;
	}
	FOR(i,R)
	{
		const pii& p = red[i];
		rq.push(p);
		checked[p.first][p.second] = 2;
	}

	int temp = 0;
	while (gq.size() && rq.size())
	{
		int Gsz = gq.size();
		int Rsz = rq.size();

		while (Gsz--)
		{
			const pii& p = gq.front();
			int y = p.first;
			int x = p.second;
			gq.pop();
			if (checked[y][x] == 3) continue;
			checked[y][x] = 4;
			FOR(k, 4)
			{
				int ny = y + dy[k];
				int nx = x + dx[k];
				if (ny < 0 || ny >= row || nx < 0 || nx >= col || !m[ny][nx] || checked[ny][nx]) continue;
				checked[ny][nx] = 1;
				gq.push(pii(ny, nx));
			}
		}

		while (Rsz--)
		{
			const pii& p = rq.front();
			int y = p.first;
			int x = p.second;
			rq.pop();
			FOR(k, 4)
			{
				int ny = y + dy[k];
				int nx = x + dx[k];
				if (ny < 0 || ny >= row || nx < 0 || nx >= col || !m[ny][nx] || checked[ny][nx] >= 2) continue;
				if (checked[ny][nx] == 1)
				{
					temp++;
					checked[ny][nx] = 3;
					continue;
				}
				checked[ny][nx] = 2;
				rq.push(pii(ny, nx));
			}
		}
	}
	return temp;
}

void Rcomb(int idx, int curr)
{
	if (idx == R)
	{
		result = max(result, bfs());
		return;
	}
	for (int i = curr; i < siz; ++i)
	{
		if (visited[i]) continue;
		visited[i] = true;
		red[idx] = throw_pos[i];
		Rcomb(idx+1, ++curr);
		visited[i] = false;
	}
}

void Gcomb(int idx,int curr)
{
	if (idx == G)
	{
		Rcomb(0,0);
		return;
	}
	for (int i = curr; i < siz; ++i)
	{
		visited[i] = true;
		green[idx] = throw_pos[i];
		Gcomb(idx+1,++curr);
		visited[i] = false;
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> row >> col >> G >> R;

	FOR(i, row) FOR(j, col)
	{
		cin >> m[i][j];
		if (m[i][j] == 2)
			throw_pos.push_back(pii(i, j));
	}
	siz = throw_pos.size();
	result = 0;
	Gcomb(0,0);
	cout << result;
}
