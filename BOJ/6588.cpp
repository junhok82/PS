#include<bits/stdc++.h>
using namespace std;

const int MAX_N = 1000001;

bool check[MAX_N] = { false, };
int n;
bool flag;
int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	check[1] = true;
	for (int i = 2; i * i <= 1000000; ++i)
	{
		if (check[i]) continue;
		for (int j = i + i; j <= 1000000; j += i)
			check[j] = true;
	}
	while (1)
	{
		cin >> n;
		if (!n)	return 0;

		flag = false;
		for (int i = 3; i <= n-3; i += 2)
		{
			if (!check[i])
				for (int j = n-3; j >= i; j -= 2)
				{
					if (i + j > n)	continue;
					else if (i + j < n)	break;
					else if (!check[j])
					{ 
						cout << n << " = " << i << " + " << j << '\n';
						flag = true;
						break;
					}
				}
			if (flag)
				break;
		}
		if (!flag)
			cout << "Goldbach's conjecture is wrong." << '\n';
	}
}
