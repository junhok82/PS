#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;
typedef long long ll;
const int MAX_N = 10000;
const int INF = 0x07fffffff;
int N,M, mx = 1;
int arr[MAX_N];
ll s,e,mid,sum = 0;

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N;
	f(i, 0, N)
	{
		cin >> arr[i];
		sum += arr[i];
		if (mx < arr[i])
			mx = arr[i];
	}
	cin >> M;
	e = mx, s = 1;
	if (sum <= M)
	{
		cout << mx;
		return 0;
	}
	while (s + 1 < e)
	{
		sum = 0;
		mid = (s + e) / 2;
		f(i, 0, N)
		{
			if (arr[i] >= mid)
				sum += mid;
			else sum += arr[i];
		}
		if (sum <= M)
			s = mid;
		else
			e = mid;
	}
	cout << s;
}