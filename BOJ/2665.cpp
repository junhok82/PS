#include <iostream>
#include <queue>
#include <functional>
using namespace std;

typedef pair<int, int> pii;
const int MAX_V = 51;
const int INF = 0x07fffffff;

int siz;
bool visited[MAX_V][MAX_V] = { false, };
bool map[MAX_V][MAX_V] = { false, };
int dist[MAX_V][MAX_V] = { 0, };
int dy[] = { 0,1,0,-1 };
int dx[] = { 1,0,-1,0 };

void bfs()
{
	priority_queue< pair<int,pii>, vector<pair<int,pii>>, greater<pair<int,pii>> > pq;
	for(int i = 0; i < MAX_V; ++i)
		fill(dist[i], dist[i] + MAX_V, INF);
	pq.push({ 0,{0,0} });
	dist[0][0] = 0;
	while (pq.size())
	{
		int sz = pq.size();
		for (int n = 0; n < sz; ++n)
		{
			int y, x;
			do {
				y = pq.top().second.first;
				x = pq.top().second.second;
				pq.pop();
			} while (pq.size() && visited[y][x]);
			visited[y][x] = true;
			if (visited[siz - 1][siz - 1])	return;

			for (int i = 0; i < 4; ++i)
			{
				int ny = y + dy[i];
				int nx = x + dx[i];
				int d = 1;
				if (ny < 0 || ny >= siz || nx < 0 || nx >= siz)	continue;
				if (map[ny][nx]) d = 0;
				if (dist[ny][nx] > dist[y][x] + d)
				{
					dist[ny][nx] = dist[y][x] + d;
					pq.push({ dist[ny][nx],{ny,nx} });
				}
			}
		}
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> siz;
	char ch;
	for (int i = 0; i < siz; ++i)
		for (int j = 0; j < siz; ++j)
		{
			cin >> ch;
			map[i][j] = ch - '0';
		}
	bfs();
	cout << dist[siz - 1][siz - 1];

	return 0;
}
