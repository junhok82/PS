#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,j,r,c) for(int i = 0; i < r; ++i) for(int j = 0; j < c; ++j)
using namespace std;

int R, C, T;
int m[51][51];
int dy[] = { 1,0,-1,0 };
int dx[] = { 0,1,0,-1 };
vector<int> clean;

void dust_spread()
{
	int temp[51][51] = { 0, };
	f(i, j, R, C) if (m[i][j] > 0)
	{
		int w = m[i][j];
		int cnt = 0;

		for (int k = 0; k < 4; ++k)
		{
			int ny = i + dy[k];
			int nx = j + dx[k];
			if (ny < 0 || ny >= R || nx < 0 || nx >= C || m[ny][nx] == -1) continue;
			temp[ny][nx] += (w / 5);
			cnt++;
		}
		m[i][j] -= ((w / 5) * cnt);
	}

	f(i, j, R, C) if (temp[i][j]) m[i][j] += temp[i][j];
}

void dust_clear()
{
	int y = clean[0] - 1, x = 0;
	if (y >= 0) m[y][x] = 0;

	y--;
	for (; y >= 0; --y) m[y + 1][x] = m[y][x];
	y++;
	x++;
	for (; x < C; ++x) m[y][x - 1] = m[y][x];
	x--;
	y++;
	for (; y <= clean[0]; ++y) 	m[y - 1][x] = m[y][x];
	y--;
	x--;
	for (; x > 0; --x) m[y][x + 1] = m[y][x];
	m[clean[0]][1] = 0;

	y = clean[1] + 1, x = 0;
	if (y < R) m[y][x] = 0;

	y++;
	for (; y < R; ++y)  m[y - 1][x] = m[y][x];
	y--;
	x++;
	for (; x < C; ++x) m[y][x - 1] = m[y][x];
	x--;
	y--;
	for (; y >= clean[1]; --y) m[y + 1][x] = m[y][x];
	y++;
	x--;
	for (; x > 0; --x) m[y][x + 1] = m[y][x];
	m[clean[1]][1] = 0;
}

void solution()
{
	for (int t = 0; t < T; ++t)
	{
		dust_spread();
		dust_clear();
	}
}

int dust_remain()
{
	int sum = 0;
	f(i, j, R, C) if (m[i][j] > 0) sum += m[i][j];
	return sum;
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> R >> C >> T;
	f(i, j, R, C)
	{
			cin >> m[i][j];
			if (m[i][j] == -1)
				clean.push_back(i);
	}
	if (clean[0] > clean[1]) swap(clean[0], clean[1]);
	solution();
	cout << dust_remain();
}