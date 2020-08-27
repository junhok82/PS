#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<int, pii> pi3;
typedef long long ll;
typedef unsigned long long ull;
const int MAX_N = 123457;
const int INF = 0x07fffffff;

int N, t, a, h, atk;
pi3 room[MAX_N];

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N >> atk;
	int c = 0;
	ll temp_atk = atk;
	ll s = 1, e = 0;
	f(i, 0, N)
	{
		cin >> t >> a >> h;
		if (t == 1)
			e += (a * (h / temp_atk));
		else
			temp_atk += a;
		room[i] = pi3(t, pii(a, h));
	}

	while (s <= e)
	{
		ll mid = (s + e) / 2;
		ll curr_hp = mid;
		ll curr_atk = atk;
		f(i, 0, N)
		{
			const pi3& temp = room[i];
			int tt = temp.first;
			ll d_atk = temp.second.first;
			ll d_hp = temp.second.second;
			int cnt = 0;
			if (tt == 1)
			{
				if (d_hp % curr_atk)
					cnt = d_hp / curr_atk;
				else
					cnt = (d_hp / curr_atk) - 1;
				curr_hp -= (d_atk * cnt);
				if (curr_hp <= 0)
					break;
			}
			else
			{
				curr_hp += d_hp;
				if (curr_hp > mid) curr_hp = mid;
				curr_atk += d_atk;
			}
		}

		if (curr_hp <= 0)
			s = mid + 1;
		else
			e = mid - 1;
	}
	cout << s;
}