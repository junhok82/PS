#include<iostream>
#include<queue>
using namespace std;
#define pii pair<int,int>

int n, k;
queue<int> q;
bool visited[200100001] = { false, };

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> n >> k;
	int a;

	for (int i = 0; i < n; ++i)
	{
		cin >> a;
		q.push(a + 100000000);
		visited[a + 100000000] = true;
	}

	long long cnt = 0;
	long long v = 0;
	int house = -n;
	//cout << "house : " << house << endl;
	while (!q.empty())
	{
		int sz = q.size();
		for (int i = 0; i < sz; ++i)
		{
			int curr = q.front();
			v += cnt;
			house++;
			//cout << "curr : " << curr << " cnt : " << cnt << " house << : " << house << endl;
			if (house == k)
			{
				cout << v;
				return 0;
			}
			q.pop();
			if (curr + 1 < 200100001)
				if (!visited[curr + 1])
				{
					q.push(curr + 1);
					visited[curr + 1] = true;
				}

			if (curr - 1 >= 0)
				if (!visited[curr - 1])
				{
					q.push(curr - 1);
					visited[curr - 1] = true;
				}
		}
		cnt++;
	}
	return 0;
}
