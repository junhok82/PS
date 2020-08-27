#include <bits/stdc++.h>
using namespace std;

typedef pair<int, int> pii;
const int INF = 0x07fffffff;

int m[9][9] = { 0, };
bool row[9][10] = { false, };	// 가로
bool col[9][10] = { false, };	// 세로
bool square[9][10] = { false, };
vector<pii> vec;

void dfs()
{
	if (!vec.size())
	{
		for (int i = 0; i < 9; ++i)
		{
			for (int j = 0; j < 9; ++j)
				cout << m[i][j] << " ";
			cout << '\n';
		}
		exit(0);
	}
	else
	{
		pii p = vec.back();
		int i = p.first;
		int j = p.second;

		int idx = (i / 3 * 3) + (j / 3);
		for (int k = 1; k < 10; ++k)
		{
			if (row[i][k] || col[j][k] || square[idx][k])	continue;
			row[i][k] = true;
			col[j][k] = true;
			square[idx][k] = true;
			m[i][j] = k;
			vec.pop_back();
			dfs();
			vec.push_back(pii(i, j));
			m[i][j] = 0;
			square[idx][k] = false;
			row[i][k] = false;
			col[j][k] = false;
		}
	}
}
int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	for (int i = 0; i < 9; ++i)
		for (int j = 0; j < 9; ++j)
		{
			cin >> m[i][j];
			if (!m[i][j])
				vec.push_back(pii(i, j));
			else
			{
				row[i][m[i][j]] = true;
				col[j][m[i][j]] = true;
				square[(i / 3 * 3) + (j / 3)][m[i][j]] = true;
			}
		}
	dfs();
}
