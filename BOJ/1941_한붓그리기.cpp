#include <bits/stdc++.h>
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

char m[25];
int dy[] = { 0,1, 0, -1 };
int dx[] = { 1,0, -1, 0 };
int result = 0, check = 0;
bool visited[1 << 25];

void dfs(int S, int Y, int route)
{
	if (Y == 4 || visited[route]) return;
	visited[route] = true;
	if (Y + S == 7)
	{
		result++;
		return;
	}
	f(curr, 0, 25)
	{
		if (!(check & (1 << curr))) continue;
		f(k, 0, 4)
		{
			int ny = curr / 5 + dy[k];
			int nx = curr % 5 + dx[k];
			int next = ny * 5 + nx;
			if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5 || (route & (1 << next))) continue;
			check |= (1 << next);
			dfs(S + (m[next] == 'S'), Y + (m[next] == 'Y'), route | (1 << next));
			check ^= (1 << next);
		}
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	f(i, 0, 25) cin >> m[i];
	f(i, 0, 25)
	{
		check= (1 << i);
		dfs((m[i] == 'S'), (m[i] == 'Y'), 1 << i);
		check ^= (1 << i);
	}
	cout << result;
	return 0;
}