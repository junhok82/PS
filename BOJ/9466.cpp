#include <iostream>
using namespace std;
bool visited[100001] = { false, };
bool finished[100001] = { false, };
int adj[100001] = { 0, };
int cnt;

void DFS(const int& curr)
{
	visited[curr] = true;
	const int next = adj[curr];
	
	if (visited[next])
	{	
		if (!finished[next])
		{
			for (int i = next; i != curr; i = adj[i])
				cnt++;
			cnt++;
		}
	}
	else
		DFS(next);
	finished[curr] = true;
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	int T;
	cin >> T;
	
	for (int t = 0; t < T; ++t)
	{
		int size;
		cnt = 0;
		cin >> size;
		for (int i = 1; i <= size; ++i)
		{
			int input;
			cin >> input;
			adj[i] = input;
		}

		for(int i = 1; i <= size; ++i)
			if(!visited[adj[i]])
				DFS(adj[i]);

		cout << size-cnt << '\n';

		for (int i = 1; i <= size; ++i)
		{
			visited[i] = false;
			finished[i] = false;
		}
	}
	return 0;
}
