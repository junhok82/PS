#include <bits/stdc++.h>
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

int N,result;
bool m[16][16];

void dfs(int y, int x, int ny, int nx,int state) // state 0 : 가로 1 : 세로 2 : 대각선
{
	if (ny == N - 1 && nx == N - 1) { result++; return; }
	if (state == 0 || state == 2)if (nx + 1 < N) if (!m[ny][nx + 1]) dfs(ny, nx, ny, nx + 1,0);
	if (state == 1 || state == 2) if (ny + 1 < N) if (!m[ny + 1][nx]) dfs(ny, nx, ny + 1, nx, 1);	
	if (ny + 1 < N && nx + 1 < N) if (!m[ny + 1][nx] && !m[ny][nx + 1] && !m[ny + 1][nx + 1]) dfs(ny, nx, ny + 1, nx + 1, 2);
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N;
	result = 0;
	f(i, 0, N) f(j, 0, N) cin >> m[i][j];
	dfs(0,0,0,1,0);
	cout << result;
}