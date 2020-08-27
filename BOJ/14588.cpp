#include<bits/stdc++.h>
using namespace std;

typedef pair<int, int> pii;
const int MAX_N = 301;
const int INF = 87654321;

int N;
vector<pii> line;
int dist[MAX_N][MAX_N] = { 0, };

bool isConnect(int a, int b)
{
	if (line[b].second < line[a].first || line[b].first > line[a].second)	return false;
	else return true;
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N;
	int a, b;
	for (int i = 0; i < N; ++i)
	{
		cin >> a >> b;
		line.push_back(pii(a, b));
		fill(dist[i], dist[i] + N + 1, INF);
	}

	for (int i = 0; i < N - 1; ++i)
		for (int j = i + 1; j < N; ++j)
			if (isConnect(i, j))
			{
				dist[i][j] = 1;
				dist[j][i] = 1;
			}

	for (int k = 0; k < N; ++k)
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j)
				dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);

	int num, u, v;
	cin >> num;
	for (int i = 0; i < num; ++i)
	{
		cin >> u >> v;
		if (dist[u-1][v-1] != INF)
			cout << dist[u-1][v-1] << '\n';
		else
			cout << -1 << '\n';
	}
}
