#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<pii, pii> pi4;
typedef long long ll;
const int MAX_N = 100001;
const int INF = 0x07fffffff;

int N, M, mid, s = 0, e = 0;
ll temp;
int arr[1000001];

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N >> M;
	f(i, 0, N)
	{
		cin >> arr[i];
		if (s > arr[i]) s = arr[i];
		if (e < arr[i]) e = arr[i];
	}

	while (s + 1 < e)
	{
		temp = 0;
		mid = (s + e) / 2;
		f(i, 0, N) if (mid < arr[i]) temp += (arr[i] - mid);
		if (temp < M)
			e = mid;
		else
			s = mid;
	}
	cout << s;
}