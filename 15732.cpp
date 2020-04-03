#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<int, pii> pi3;
typedef long long ll;
const int MAX_N = 1000001;
const int INF = 0x07fffffff;
const ll INF_LL = 0x07f7f7f7f7f7f7f7f;

int N, K, a, b, c;
ll D, cnt, mid, s = MAX_N, e = 1,ans = INF_LL;
pi3 box[MAX_N];

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N >> K >> D;
	f(i, 0, K)
	{
		cin >> a >> b >> c;
		if (b > e) e = b;
		if (a < s) s = a;
		box[i] = pi3(c, pii(a, b));
	}
	while (s <= e)
	{
		cnt = 0;
		mid = (s + e) / 2;
		f(i, 0, K)
		{
			const pi3& p = box[i];
			ll ss = p.second.first;
			ll ee = p.second.second;
			ll d = p.first;
			ll temp = min(mid, ee);
			if (temp <= ee && temp >= ss)
				cnt += (((temp - ss) /d) + 1);
			if (cnt >= D) break;
		}

		if (cnt >= D)
		{
			ans = mid;
			e = mid - 1;
		}
		else
			s = mid + 1;
	}
		cout << ans;
	return 0;
}