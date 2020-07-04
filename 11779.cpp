#include <iostream>
#include <queue>
#include <stack>
#include <functional>
using namespace std;

typedef pair<int, int> pii;
typedef long long ll;
const int MAX_V = 1001;
const int INF = 0x07fffffff;

int V, E;
bool visited[MAX_V] = { 0, };
int path[MAX_V] = { 0, };
ll dist[MAX_V] = { 0, };
vector<pii> adj[MAX_V];
stack<int> result;

void bfs(int s, int e)
{
	priority_queue<pii, vector<pii>, greater<pii> > pq;
	fill(dist, dist + MAX_V, INF);
	pq.push({0,s});
	dist[s] = 0;
	while (pq.size())
	{
		int sz = pq.size();
		for (int n = 0; n < sz; ++n)
		{
			int curr;
			do {
				curr = pq.top().second;
				pq.pop();
			} while (pq.size() && visited[curr]);
			visited[curr] = true;
			if (visited[e])	return;

			for (auto& p : adj[curr])
			{
				int next = p.first, w = p.second;
				if (dist[next] > dist[curr] + w)
				{
					dist[next] = dist[curr] + w;
					path[next] = curr;
					pq.push({ dist[next], next });
				}
			}
		}
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> V;
	cin >> E;
	int u, v, d, s, e;
	for (int i = 0; i < E; ++i)
	{
		cin >> u >> v >> d;
		adj[u].push_back(pii(v, d));
	}
	cin >> s >> e;
	bfs(s, e);
	cout << dist[e] << '\n';
	int temp = e;
	int count = 0;
	while (1)
	{
		result.push(temp);
		count++;
		if (temp == s)	break;
		temp = path[temp];
	}
	cout << count << '\n';
	while (result.size())
	{
		cout << result.top() << " ";
		result.pop();
	}
	return 0;
}
