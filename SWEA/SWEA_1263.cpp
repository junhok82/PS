#include <bits/stdc++.h>
using namespace std;

#define FOR(i,n) for(int i = 0; i < n; ++i)

const int MAX_V = 1001;
const int INF = 1000001;

int T, V, a, result;
int dist[MAX_V][MAX_V];

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> T;
	FOR(t, T)
	{
		cin >> V;
		result = INF;
		FOR(i, V) fill(dist[i], dist[i] + V, INF);
		FOR(i, V)  FOR(j, V)
		{
			cin >> a;
			if (a) dist[i][j] = 1;
		}
		FOR(k, V) FOR(i, V)
		{
			if (i == k)continue;
			FOR(j, V)
				if (dist[i][j] > dist[i][k] + dist[k][j])
					dist[i][j] = dist[i][k] + dist[k][j];
		}
		FOR(i, V)
		{
			int temp = 0;
			FOR(j, V)
			{
				if (i == j)	continue;
				temp += dist[i][j];
			}
			if(result > temp) result = temp;
		}
		cout << "#" << (t+1) << " " <<  result << '\n';
	}
}
