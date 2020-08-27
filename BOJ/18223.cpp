#include <iostream>
#include <queue>
#include <functional>
using namespace std;

typedef pair<int, int> pii;
const int MAX_V = 5001;
const int INF = 0x07fffffff;

int V, E, P;
bool visited[MAX_V] = { false, };
int dist[MAX_V] = { 0, };
vector<pii> adj[MAX_V];

int bfs(int s, int e)
{
	priority_queue< pii, vector<pii>, greater<pii> > pq;
	fill(dist, dist + MAX_V, INF);
	fill(visited, visited + MAX_V, false);
	pq.push(pii(0,s));
	dist[s] = 0;
	while (pq.size())
	{
		int curr;
		int dd;
		do {
			curr = pq.top().second;
			dd = pq.top().first;
			pq.pop();
		} while (pq.size() && visited[curr]);
		visited[curr] = true;
		if (visited[e])	return dd;
		for (auto& p : adj[curr])
		{
			int next = p.second, d = p.first;
			if (dist[next] > dist[curr] + d)
			{
				dist[next] = dist[curr] + d;
				pq.push(pii(dist[next], next));
			}
		}
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> V >> E >> P;
	int u, v, d;
	for (int i = 0; i < E; ++i)
	{
		cin >> u >> v >> d;
		adj[u].push_back(pii(d, v));
		adj[v].push_back(pii(d, u));
	}

	int comp1 = 0, comp2 = 0;
	comp1 = bfs(1,V);
	comp2 = bfs(1, P) + bfs(P, V);
	if (comp1 == comp2)
		cout << "SAVE HIM";
	else
		cout << "GOOD BYE";
	return 0;
}
