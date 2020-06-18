#include<bits/stdc++.h>
using namespace std;

#define l(i,n) for(int i = 1; i <= n; ++i)

int V, E, u, v, num;
bool c[401][401] = { false, };

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> V >> E;
	for (int i = 0; i < E; ++i)
	{
		cin >> u >> v;
		c[u][v] = true;
	}
	l(k,V) l(i,V) l (j,V) 
		if (c[i][k] && c[k][j])
			c[i][j] = true;			
	cin >> num;
	for (int i = 0; i < num; ++i)
	{
		cin >> u >> v;
		cout << (c[u][v] ? -1 : (c[v][u] ? 1 : 0)) << '\n';
	}
}
