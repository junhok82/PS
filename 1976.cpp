#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;
const int MAX_N = 201;

int N, M, u ,v, f;
int uf[MAX_N];

int find(int a)
{
	if (uf[a] == -1) return a;
	return uf[a] = find(uf[a]);
}

void merge(int a, int b)
{
	a = find(a);
	b = find(b);
	if (a == b) return;
	uf[a] = b;
	return;
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N >> M;
	fill(uf, uf + MAX_N, -1);
	f(i, 0, N) f(j, 0, N)
	{
		cin >> f;
		if (f & 1) merge(i, j);
	}
	bool pos = true;
	f(i, 0, M)
	{
		cin >> f;
		v = find(f-1);
		if(i > 0)
			if (v != u)
			{
				pos = false;
				break;
			}
		u = v;
	}
	if (pos) cout << "YES";
	else cout << "NO";
}