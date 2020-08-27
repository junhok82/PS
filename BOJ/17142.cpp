#include <bits/stdc++.h>
#define f(i,x,n) for(int i = x; i < n; ++i)
#define Y first
#define X second
using namespace std;
typedef pair<int, int> pii;

const int INF = 0x07fffffff;
int dy[] = { 1,0,-1,0 };
int dx[] = { 0,1,0,-1 };
int N, M, zero, result;
int m[51][51];
int selected[2501];
vector<pii> virus;
deque<pii> dq;

int bfs()
{
	deque<pii> q = dq;
	bool visited[51][51] = { 0, };
	int sz, cnt = 0, curr_zero = 0;
	while (sz = q.size())
	{
		if (zero == curr_zero) return cnt;
		f(i, 0, sz)
		{
			const pii& p = q.front();
			int y = p.Y;
			int x = p.X;
			visited[y][x] = true;
			q.pop_front();

			f(k, 0, 4)
			{
				int ny = y + dy[k];
				int nx = x + dx[k];
				if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] || m[ny][nx] == 1) continue;

				if (m[ny][nx] == 0)
					curr_zero++;
				visited[ny][nx] = 1;
				q.push_back(pii(ny, nx));
			}
		}
		cnt++;
		if (cnt >= result) return result;
	}
	return result;
}

void comb(int idx, int curr)
{
	if (idx == M)
	{
		result = min(result, bfs());
		return;
	}
	f(i, curr, virus.size())
	{
		dq.push_back(pii(virus[i]));
		comb(idx + 1, i + 1);
		dq.pop_back();
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N >> M;
	result = INF;
	virus.reserve(2500);
	f(i, 0, N) f(j, 0, N)
	{
		cin >> m[i][j];
		if (m[i][j] == 2)
			virus.push_back(pii(i, j));
		else if (m[i][j] == 0)
			zero++;
	}
	comb(0, 0);
	cout << (result == INF ? -1 : result);
}