#include <bits/stdc++.h>
using namespace std;

#define FOR(i,n) for(int i = 0; i < n; ++i)
typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;

const int INF = 0x07fffffff;

int row, col, br, bc, rr, rc;
char m[11][11];
int dy[] = { 0,1,0,-1 };
int dx[] = { 1,0,-1,0 };
bool visited[11][11][11][11];

int bfs()
{
	queue<pi4> q;
	q.push(pi4(pii(rr, rc), pii(br, bc)));
	visited[rr][rc][br][bc] = true;
	int cnt = 0;
	while (q.size())
	{
		int sz = q.size();
		FOR(i, sz)
		{
			const pi4& p4 = q.front();
			const pii& p1 = p4.first;
			const pii& p2 = p4.second;
			int ry = p1.first, rx = p1.second, by = p2.first, bx = p2.second;
			q.pop();
			if (m[ry][rx] == 'O')
				return cnt;
			else if (cnt == 10)
				continue;
			FOR(k, 4)
			{
				int rry = ry + dy[k];
				int rrx = rx + dx[k];
				int bby = by + dy[k];
				int bbx = bx + dx[k];
				while (bby >= 0 && bby < row && bbx >= 0 && bbx < col)
				{
					if (m[bby][bbx] == 'O') break;
					else if (m[bby][bbx] == '#') { bby -= dy[k]; bbx -= dx[k]; break; }
					bby += dy[k];
					bbx += dx[k];
				}
				if (m[bby][bbx] == 'O') continue;

				while (rry >= 0 && rry < row && rrx >= 0 && rrx < col)
				{
					if (m[rry][rrx] == 'O') break;
					else if (m[rry][rrx] == '#') { rry -= dy[k]; rrx -= dx[k]; break; }
					rry += dy[k];
					rrx += dx[k];
				}

				if (rry == bby && rrx == bbx)
				{
					if (abs(ry - rry) + abs(rx - rrx) > abs(by - bby) + abs(bx - bbx))
					{
						rry -= dy[k]; rrx -= dx[k];
					}
					else
					{
						bby -= dy[k]; bbx -= dx[k];
					}
				}
				if (visited[rry][rrx][bby][bbx]) continue;
				visited[rry][rrx][bby][bbx] = true;
				q.push(pi4(pii(rry, rrx), pii(bby, bbx)));
			}
		}
		cnt++;
	}
	return -1;
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> row >> col;
	FOR(i, row) FOR(j, col)
	{
		cin >> m[i][j];
		if (m[i][j] == 'B')
		{
			br = i;
			bc = j;
		}
		else if (m[i][j] == 'R')
		{
			rr = i;
			rc = j;
		}
	}
	cout << bfs();
}