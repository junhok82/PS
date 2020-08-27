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

__inline int imax(int a, int b) { return a > b ? a : b; }

int R, C, num = 0, result = 1;
int m[1000][1000];
bool checked[1000][1000] = { 0, };
int uf[1000 * 1000];
int dy[] = { 0,1,0,-1 };
int dx[] = { 1,0,-1,0 };

int find(int a)
{
	if (uf[a] < 0) return a;
	return uf[a] = find(uf[a]);
}

void Union(int a, int b)
{
	a = find(a);
	b = find(b);
	if (a != b)
	{
		uf[a] += uf[b];
		uf[b] = a;
	}
}

void group(int r, int c)
{
	queue<pii> q;
	q.push(pii(r, c));
	checked[r][c] = true;
	int cnt = 0;
	while (q.size())
	{
		const pii& curr = q.front();
		int y = curr.first;
		int x = curr.second;
		cnt++;
		q.pop();
		f(i, 0, 4)
		{
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || ny >= R || nx < 0 || nx >= C || checked[ny][nx] || !m[ny][nx]) continue;
			checked[ny][nx] = true;
			Union(y * C + x, ny * C + nx);
			q.push(pii(ny, nx));
		}
	}
}

int merge(int r, int c)
{
	int cnt = 1;
	int ck[4] = { INF, INF, INF, INF };
	f(i, 0, 4)
	{
		int ny = r + dy[i];
		int nx = c + dx[i];
		bool pos = false;
		if (ny < 0 || ny >= R || nx < 0 || nx >= C || !m[ny][nx]) continue;
		int f = find(ny * C + nx);
		f(j, 0, 4) if (ck[j] == f)
		{
			pos = true;
			break;
		}
		if (!pos) cnt += (-uf[f]);
		ck[i] = f;
	}
	return cnt;
}

bool check(int r, int c)
{
	f(k, 0, 4)
	{
		int ny = r + dy[k];
		int nx = c + dx[k];
		if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
		if (m[ny][nx]) return true;
	}
	return false;
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> R >> C;
	memset(uf, -1, sizeof uf);
	f(i, 0, R) f(j, 0, C) cin >> m[i][j];
	f(i, 0, R) f(j, 0, C) if (m[i][j] && !checked[i][j]) group(i, j);
	f(i, 0, R) f(j, 0, C) if (!m[i][j]) if (check(i, j)) result = imax(result, merge(i, j));
	cout << result;
}