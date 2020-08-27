#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;
const int MAX_N = 10001;

bool flag;
int N, L, R, result;
int m[51][51];
bool visited[51][51];
int dy[] = { 0,1,0,-1 };
int dx[] = { 1,0,-1,0 };

void bfs(int r, int c)
{
	queue<pii> q;
	vector<pii> temp;
	q.push(pii(r, c));
	visited[r][c] = true;
	int cnt = 0, sum = 0;
	while (q.size())
	{
		const pii& p = q.front();
		int y = p.first;
		int x = p.second;
		int d = m[y][x];
		q.pop();
		cnt++;
		sum += d;

		f(i, 0, 4)
		{
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) continue;
			int nd = m[ny][nx];
			if (abs(nd - d) < L || abs(nd - d) > R) continue;
			visited[ny][nx] = true;
			q.push(pii(ny, nx));
			temp.push_back(pii(ny, nx));
		}
	}
	int data = sum / cnt;
	if (temp.size())
	{
		m[r][c] = data;
		flag = false;
	}
	f(i, 0, temp.size())
		m[temp[i].first][temp[i].second] = data;
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N >> L >> R;
	f(i, 0, N) f(j, 0, N)
		cin >> m[i][j];
	
	result = 0;
	while (true)
	{
		flag = true;
		f(i, 0, N) f(j, 0, N) if (!visited[i][j]) bfs(i, j);
		if (flag) break;
		result++;
		f(i, 0, N) fill(visited[i], visited[i] + N, false);
	}
	cout << result;
}