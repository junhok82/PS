#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;
typedef long long ll;
const int MAX_N = 100001;
const int INF = 0x07fffffff;
int N,M, cnt, mx = 1;
int arr[MAX_N];
ll s,e,mid,sum = 0;

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N >> M;
	f(i, 0, N)
	{
		cin >> arr[i];
		sum += arr[i];
		if (mx < arr[i]) mx = arr[i];
	}
	e = sum;
	s = 1;
	int ans = INF;
	while (s <= e)
	{
		sum = 0, cnt = 1;
		int mid = (s + e) / 2;
		if (mid < mx) 
		{
			s = mid + 1;
			continue;
		}
		f(i, 0, N)
		{
			sum += arr[i];
			if (sum > mid)
			{
				sum = arr[i];
				cnt++;
				if (cnt > M)
					break;
			}
		}
		if (cnt <= M)
		{
			ans = min(ans, mid);
			e = mid - 1;
		}
		else
			s = mid + 1;
	}
	cout << ans;
}