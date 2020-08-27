#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;
typedef long long ll;
const int MAX_N = 100000;
const int INF = 0x07fffffff;

int N,cnt=0;
bool left_cross[MAX_N * 2];
bool right_cross[MAX_N * 2];
bool col[MAX_N];
int result[MAX_N];

void dfs(int y)
{
	if (y == -1)
	{
		cnt++;
		return;
	}
	f(x, 0, N)
	{
		if (col[x] || right_cross[y + x] || left_cross[y + N - 1 - x]) continue;

		result[y] = x + 1;
		right_cross[y + x] = true;
		left_cross[y + N - 1 - x] = true;
		col[x] = true;
		dfs(y - 1);
		col[x] = false;
		right_cross[y + x] = false;
		left_cross[y + N - 1 - x] = false;
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N;
	dfs(N-1);
	cout << cnt;
}