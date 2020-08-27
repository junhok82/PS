#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;
typedef long long ll;
const int MAX_K = 10001;
const int INF = 0x07fffffff;

int K, N;
ll len[MAX_K];
ll sum = 0, mx = 1, cnt;

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> K >> N;
	f(i, 0, K)
	{
		cin >> len[i];
		if (mx < len[i])
			mx = len[i];
	}
	ll s = 1, e = mx;
	while (s <= e)
	{
		cnt = 0;
		ll mid = (s + e) / 2;
		f(i, 0, K)
		{
			cnt += (len[i] / mid);
			if (cnt >= N)
				break;
		}
		if (cnt >= N)
			s = mid + 1;
		else
			e = mid - 1;
	}
	cout << s - 1;
}