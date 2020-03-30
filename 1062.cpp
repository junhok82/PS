#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;

int N, K, result;
string str[50];
bool alpha[26];

void dfs(int idx, int curr)
{
	if (result == N) return;
	if (idx == K)
	{
		int temp = 0;
		f(i, 0, N) 
		{
			bool pos = true;
			int sz = str[i].size() - 4;
			f(j, 4, sz)
			{
				if (!alpha[str[i][j] - 97])
				{
					pos = false;
					break;
				}
			}
			if (pos) temp++;
		}
		if (temp > result) result = temp;
		return;
	}
	f(i, curr, 26)
	{
		if (alpha[i]) continue;
		alpha[i] = true;
		dfs(idx+1, i+1);
		alpha[i] = false;
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N >> K;
	f(i, 0, N) cin >> str[i];
	if (K < 5) { cout << 0; return 0; }
	K -= 5, result = 0;
	alpha['a' - 97] = true;
	alpha['n' - 97] = true;
	alpha['t' - 97] = true;
	alpha['i' - 97] = true;
	alpha['c' - 97] = true;
	dfs(0,1);
	cout << result;
}