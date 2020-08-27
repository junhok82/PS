#include <bits/stdc++.h>
#define all(x) x.begin(),x.end()
#define f(i,a,n) for(int i = a; i < n; ++i)
using namespace std;

typedef pair<int, int> pii;
typedef pair<int, pii> pi3;

char ch;
int row, col, result;
int m[4][4];
bool visited[4][4];

int check(int y, int x, int k, int cnt)
{
	int sum = 0;
	if (k)
	{
		for (int i = cnt, digit = 1; i >= 0; --i, digit *= 10)
		{
			if (y + i >= row || visited[y + i][x]) return -1;
			sum += (m[y + i][x] * digit);
		}
	}
	else
	{
		for (int i = cnt, digit = 1; i >= 0; --i, digit *= 10)
		{
			if (x + i >= col || visited[y][x + i]) return -1;
			sum += (m[y][x + i] * digit);
		}
	}
	return sum;
}

void visit(int y, int x, int k, int cnt, bool flag)
{
	for (int i = 0; i <= cnt; ++i)
	{
		if (k)
			visited[y + i][x] = flag;
		else
			visited[y][x + i] = flag;
	}
}

void dfs(int num, int sum)	 // cnt 가로나 세로로 가는 횟수
{
	if (num == row * col)
	{
		if (sum > result)
			result = sum;
		return;
	}
	f(i, 0, row) f(j, 0, col)
	{
		if (visited[i][j]) continue;
		for (int cnt = 1; ; ++cnt)
		{
			bool pos = true;
			f(k, 0, 2)
			{
				int temp = check(i, j, k, cnt);
				//cout << " sum : " << sum << " temp : " << temp << endl;
				if (temp != -1)
				{
					visit(i, j, k, cnt, true);
					dfs(num + cnt + 1, sum + temp); // k : 0 (가로) 1 (세로)
					visit(i, j, k, cnt, false);
					pos = false;
				}
			}
			if (pos) break;
		}
		visited[i][j] = true;
		dfs(num + 1, sum + m[i][j]);	// 한칸
		visited[i][j] = false;
		return;
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> row >> col;
	result = 0;
	f(i, 0, row) f(j, 0, col)
	{
		cin >> ch;
		m[i][j] = ch - '0';
	}
	dfs(0, 0);
	cout << result;
}