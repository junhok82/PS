#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<int, pii> pi3;
typedef long long ll;
const int MAX_N = 1000001;
const int INF = 0x07fffffff;
const ll INF_LL = 0x07f7f7f7f7f7f7f7f;


char m[25];
int dy[] = { 0,1, 0, -1 };
int dx[] = { 1,0, -1, 0 };
bool visited[5][5];
int result;

int bfs(int temp)
{
	bool checked[5][5] = { false, };
	queue<pii> q;
	q.push(pii(temp / 5, temp % 5));
	checked[temp / 5][temp % 5] = true;
	int cnt = 0;

	while (q.size())
	{
		const pii& p = q.front();
		int y = p.first;
		int x = p.second;
		q.pop();
		cnt++;
		f(i, 0, 4)
		{
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5 || checked[ny][nx] || !visited[ny][nx]) continue;
			checked[ny][nx] = true;
			q.push(pii(ny, nx));
		}
	}
	return cnt;
}

void dfs(int idx, int cnt, int curr)
{
	if (cnt == 4) return;
	if (idx == 7)
	{
		if (bfs(curr - 1) == 7) result++;
		return;
	}
	f(i, curr, 25)
	{
		visited[i / 5][i % 5] = true;
		dfs(idx + 1, cnt + (m[i] == 'Y'), i + 1);
		visited[i / 5][i % 5] = false;
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	f(i, 0, 25) cin >> m[i];

	result = 0;
	dfs(0, 0, 0);
	cout << result;
	return 0;
}