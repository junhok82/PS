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

char m[12][6];
int dy[] = { 0,1,0,-1 };
int dx[] = { 1,0,-1,0 };

bool bfs(int r, int c)
{
	queue<pii> q;
	vector<pii> v;
	v.push_back(pii(r, c));
	q.push(pii(r, c));
	char data = m[r][c];
	bool visited[12][6] = { 0, };
	visited[r][c] = true;
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
			if (ny < 0 || ny >= 12 || nx < 0 || nx >= 6 || visited[ny][nx] || m[ny][nx] != data) continue;
			visited[ny][nx] = true;
			q.push(pii(ny, nx));
			v.push_back(pii(ny, nx));
		}
	}
	if (cnt >= 4) for (const pii& p : v) m[p.first][p.second] = '.';
	return (cnt >= 4) ? true : false;
}

void down()
{
	int sidx;
	f(j, 0, 6) 
	{
		int h = 11;
		bool flag = true;
		while (h >= 0)
		{
			if (m[h][j] == '.' && flag)
			{
				if (flag)
					sidx = h;
				flag = false;
			}
			else if (m[h][j] != '.' && !flag)
			{
				int i = h;
				while (i >= 0 && m[i][j] != '.')
				{
					m[i][j] ^= m[sidx][j];
					m[sidx][j] ^= m[i][j];
					m[i][j] ^= m[sidx][j];
					sidx--, i--;
				}
				h = i + 2;
				flag = true;
			}
			h--;
		}
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	int ans = 0;
	f(i, 0, 12)f(j, 0, 6) cin >> m[i][j];
	while(1)
	{
		bool pos = false;
		f(i, 0, 12)f(j, 0, 6) if (m[i][j] != '.') if (bfs(i, j)) pos = true;
		if (pos) down();
		else break;
		ans++;
	}
	cout << ans;
}