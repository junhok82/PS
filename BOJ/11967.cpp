#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <utility>
using namespace std;

#define INF 0x7FFFFFFF
#define pi pair<int,int>
int siz, num;
bool visited[101][101];
bool finished[101][101];
bool check[101][101];
bool adj[101][101][101][101];
int diry[] = { 0,1,0,-1 };
int dirx[] = { 1,0,-1,0 };

int BFS()
{
	queue<pi> Q;
	Q.push(make_pair(1, 1));
	visited[1][1] = true;
	check[1][1] = true;
	int cnt = 1;

	while (!Q.empty())
	{
		pi p = Q.front();
		int y = p.first;
		int x = p.second;
		Q.pop();

		
		for (int i = 1; i <= siz; ++i)
			for (int j = 1; j <= siz; ++j)
				if (adj[y][x][i][j] && !visited[i][j])
				{
					cnt++;			
					visited[i][j] = true;
				}
	
		queue<pi> Q2;
		Q2.push(make_pair(1, 1));
		finished[1][1] = true;
		while (!Q2.empty())
		{
			pi p2 = Q2.front();
			int py = p2.first;
			int px = p2.second;
			Q2.pop();

			for (int k = 0; k < 4; ++k)
			{
				int ny = py + diry[k];
				int nx = px + dirx[k];
				//cout << "ny : " << ny << " nx : " << nx << endl;
				if (ny >= 1 && ny <= siz && nx >= 1 && nx <= siz)
					if (!finished[ny][nx] && visited[ny][nx])
					{
						finished[ny][nx] = true;
						Q2.push(make_pair(ny, nx));
						if (!check[ny][nx])
						{
							Q.push(make_pair(ny, nx));
							check[ny][nx] = true;
						}
					}
			}
		}

		for (int i = 1; i <= siz; ++i)
			for (int j = 1; j <= siz; ++j)
				finished[i][j] = false;

	}
	return cnt;
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> siz >> num;
	int a, b, c, d;
	for (int i = 0; i < num; ++i)
	{
		cin >> a >> b >> c >> d;
		adj[a][b][c][d] = true;
	}
	cout << BFS();
}


