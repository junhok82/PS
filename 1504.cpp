#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<utility>
#include<functional>
using namespace std;

typedef pair<int, int> pii;
const int INF = 0x07fffffff;
const int MAX_NODE = 801;
int node, edge;

vector<pii> adj[MAX_NODE];
int dist[MAX_NODE] = { 0, };
bool visited[MAX_NODE] = { false, };

bool BFS(int s, int e)
{
	priority_queue<pii, vector<pii>, greater<pii>> pq;
	pq.push(pii(0, s));
	fill(visited, visited + MAX_NODE, false);
	fill(dist, dist + MAX_NODE, INF);
	dist[s] = 0;

	while (pq.size() > 0)
	{
		int curr;
		do {
			curr = pq.top().second;
			pq.pop();
		} while (pq.size() > 0 && visited[curr]);
		if (visited[curr])	return false;
		visited[curr] = true;
		if (visited[e])	return true;
		for (pii &p : adj[curr])
		{
			int next = p.first, d = p.second;
			if (dist[next] > dist[curr] + d)
			{
				dist[next] = dist[curr] + d;
				pq.push(pii(dist[next], next));
			}
		}
	}
	return false;
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);

	fill(dist, dist + MAX_NODE, INF);
	cin >> node >> edge;

	int u,v,w,t1,t2;
	for (int i = 0; i < edge; ++i)
	{
		cin >> u >> v >> w;
		adj[u].push_back(pii(v, w));
		adj[v].push_back(pii(u, w));
	}
	cin >> t1 >> t2;
	int result = INF;
	bool c1 = BFS(1, t1); int d1 = dist[t1];
	bool c2 = BFS(t1, t2); int d2 = dist[t2];
	bool c3 = BFS(t2, node); int d3 = dist[node];
	if (c1 && c2 && c3)
		result = d1 + d2 + d3;

	c1 = BFS(1, t2); d1 = dist[t2];
	c2 = BFS(t2, t1); d2 = dist[t1];
	c3 = BFS(t1, node); d3 = dist[node];
	if (c1 && c2 && c3)
		result = min(result, d1 + d2 + d3);

	cout << (result == INF ? -1 : result);
}
