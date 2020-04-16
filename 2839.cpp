#include <bits/stdc++.h>
using namespace std;
const int INF = 987654;

int dp[5001];
int di[2] = {3,5};

int main()
{
  ios::sync_with_stdio(0),cin.tie(0);
  int N;
  cin >> N;

  memset(dp, INF, sizeof dp);
  dp[0] = 0;

  for(int n = 0; n < 2; ++n)
    for(int k = di[n]; k <= N; ++k)
      dp[k] = min(dp[k], dp[k-di[n]] + 1);

  cout << (dp[N] >= INF ? -1 : dp[N]);
}
