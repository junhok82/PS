#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<int, pii> pi3;

int N, M, K;
int A[11][11];
vector < vector<int>> m;
int dy[] = { 0,1,0,-1,1,1,-1,-1 };
int dx[] = { 1,0,-1,0,1,-1,1,-1 };
vector<int> tree[100][100];

void solve()
{
	f(kk, 0, K)
	{
		vector<vector<int>> map(m);
		vector<pii> fall;

		f(i, 0, N) f(j, 0, N)
		{
			if (tree[i][j].size())
			{
				int sum = 0;
				for(int ii = tree[i][j].size()-1; ii >= 0; --ii)
				{
					int w = tree[i][j][ii];
					if (map[i][j] >= w)
					{
						map[i][j] -= w;
						m[i][j] -= w;
						tree[i][j][ii]++;				
						if (((w + 1) % 5) == 0)
							fall.push_back(pii(i, j));
					}
					else
					{
						tree[i][j].erase(tree[i][j].begin() + ii);
						sum += (w / 2);
					}
				}
				map[i][j] += sum;
				m[i][j] += sum;
			}
			m[i][j] += A[i][j];
		}

		for (const pii& f : fall)
		{
			int y = f.first;
			int x = f.second;
			f(k, 0, 8)
			{
				int ny = dy[k] + y;
				int nx = dx[k] + x;
				if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
				tree[ny][nx].push_back(1);
			}
		}
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N >> M >> K;
	m.resize(100);
	f(i, 0, N)
	{
		m[i].resize(100);
		f(j, 0, N)
		{
			m[i][j] = 5;
			cin >> A[i][j];
		}
	}
	f(i, 0, M)
	{
		int r, c, w;
		cin >> r >> c >> w;
		tree[r - 1][c - 1].push_back(w);
	}
	solve();
	int result = 0;
	f(i, 0, N) f(j, 0, N) result += tree[i][j].size();
	cout << result;
}