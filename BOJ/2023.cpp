#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>
using namespace std;

const int maxx = 100000;

bool memo[100000] = { false, };

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	int num;
	cin >> num;
	int s = 1, e = 1;
	for (int i = 1; i <= num; ++i)
	{
		if(i != num)
			s *= 10;
		e *= 10;
	}
	bool pos = true;

	for (int i = s; i < e; ++i)
	{
		if (i == 1)	continue;
		pos = true;
		for (int k = e/10; k > 0; k /= 10)
		{
			int v = i / k;
			if (v == 1) { pos = false; break; }
			else if(v < maxx)	if(memo[v]) { pos = false; break; }
			for(int p = 2; p*p <= v; ++p)
				if (v % p == 0) { if (v < maxx) memo[v] = true;  pos = false; break; }
		}
		if (pos)
			cout << i << '\n';
	}
}
