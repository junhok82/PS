#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;
typedef long long ll;
const int MAX_N = 200001;
const int INF = 0x07fffffff;

int N, C;
ll arr[MAX_N];
ll s, e;

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N >> C;
	f(i, 0, N)
		cin >> arr[i];
	sort(arr, arr + N);
	s = 1, e = arr[N - 1] - arr[0];

	while (s <= e)
	{
		int cnt = 1;
		ll temp = arr[0];
		ll mid = (s + e) / 2;
		f(i, 1, N+1)
		{
			if (arr[i-1] - temp > mid)
			{
				temp = arr[i-1];
				cnt++;
			}
		}
		if (cnt >= C)
			s = mid + 1;
		else
			e = mid - 1;
	}
	cout << s;
}