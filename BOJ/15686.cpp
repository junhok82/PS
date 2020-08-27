#include <bits/stdc++.h>
using namespace std;

typedef pair<int, int> pii;
const int INF = 0x07fffffff;
const int MAX_N = 51;

int N, M, result;
int dist[251][251] = { 0, };
int m[MAX_N][MAX_N] = { 0, };
bool visited[MAX_N][MAX_N] = { false, };
int dy[] = { 0,1,0,-1 };
int dx[] = { 1,0,-1,0 };
vector<pii> vec;
vector<pii> arr;

int bfs(int r, int c)
{
	for (int i = 0; i < N; ++i)
		fill(visited[i], visited[i] + N, false);
	queue<pii> q;
	q.push(pii(r, c));
	visited[r][c] = true;
	int cnt = 0;

	while (q.size())
	{
		int sz = q.size();
		for (int i = 0; i < sz; ++i)
		{
			pii p = q.front();
			int y = p.first;
			int x = p.second;
			if (m[y][x] == 2)
			{
				dist[(r * N) + (c % N)][(y * N) + (x % N)] = cnt;
				return cnt;
			}
			q.pop();
			for (int j = 0; j < 4; ++j)
			{
				int ny = y + dy[j];
				int nx = x + dx[j];
				if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) continue;
				visited[ny][nx] = true;
				q.push(pii(ny, nx));
			}
		}
		cnt++;
	}
}

void comb(int idx, int before)
{
	if (idx == M)
	{
		int temp = 0;
		
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j)
				if (m[i][j] == 1)
				{
					int comp = INF;
					for (int k = 0; k < arr.size(); ++k)
						comp = min(comp, abs(i - arr[k].first) + abs(j - arr[k].second));
					temp += comp;
				}

		if (result > temp)
			result = temp;
		return;
	}

	for (int i = before; i < vec.size(); ++i)
	{
		pii p = vec[i];
		int y = p.first;
		int x = p.second;

		m[y][x] = 2;
		arr.push_back(pii(y, x));
		comb(idx + 1, ++before);
		arr.pop_back();
		m[y][x] = 0;

	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N >> M;
	for (int i = 0; i < N; ++i)
		for (int j = 0; j < N; ++j)
		{
			cin >> m[i][j];
			if (m[i][j] == 2)
			{
				vec.push_back(pii(i, j));
				m[i][j] = 0;
			}
		}
	result = INF;
	comb(0, 0);

	cout << result;
}
