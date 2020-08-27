#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;
const int MAX_N = 200001;

int N, T;
string s1, s2;
int uf[MAX_N], cnt[MAX_N];

int find(int a)
{
	if (uf[a] == -1) return a;
	return uf[a] = find(uf[a]);
}

int merge(int a, int b)
{
	a = find(a);
	b = find(b);
	if (a == b) return cnt[a];
	uf[b] = a;
	cnt[a] += cnt[b];
	return cnt[a];
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> T;
	f(t, 0, T)
	{
		cin >> N;
		map<string, int> m;
		fill(uf, uf + MAX_N, -1);
		fill(cnt, cnt + MAX_N, 1);
		int idx = 0;
		f(i, 0, N)
		{
			cin >> s1 >> s2;
			if (m.find(s1) == m.end()) m[s1] = idx++;
			if (m.find(s2) == m.end()) m[s2] = idx++;
			cout << merge(m[s1], m[s2]) << '\n';
		}
	}
}