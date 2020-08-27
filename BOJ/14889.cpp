#include <bits/stdc++.h>
using namespace std;

const int MAX_N = 21;
const int INF = 0x07fffffff;
typedef long long ll;

int N,cnt,temp,result;
ll check;
bool pass;
int info[MAX_N][MAX_N] = { false, };
bool visited[MAX_N] = { false, };
int arr[MAX_N] = { 0, };
int arr2[MAX_N] = { 0, };
int team1[2] = { 0, };

void comb2(int idx, int before, int data[])
{
	if (idx == 2)
	{
		temp += info[team1[0]][team1[1]] + info[team1[1]][team1[0]];
		return;
	}
	for (int i = before; i < N / 2; ++i)
	{
		team1[idx] = data[i];
		comb2(idx + 1,++before,data);
	}
}

void comb(int idx, int before)
{
	if (idx == N/2)
	{
		cnt++;
		int idx2 = 0;
		for (int i = 0; i < N; ++i)
			if (!visited[i])
				arr2[idx2++] = i;
		int t1, t2;
		temp = 0;
		comb2(0,0, arr);
		t1 = temp;
		temp = 0;
		comb2(0,0, arr2);
		t2 = temp;
		temp = abs(t1 - t2);
		if (result > temp)
			result = temp;
		if (cnt == check || result == 0)
			pass = true;
		
		return;
	}

	for (int i = before; i < N; ++i)
	{

		if (visited[i])	continue;
		visited[i] = true;
		arr[idx] = (i);
		comb(idx + 1, ++before);
		if (pass)	return;
		visited[i] = false;
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N;
	for (int i = 0; i < N; ++i)
		for(int j = 0; j < N; ++j)
			cin >> info[i][j];

	check = 1;
	for (int i = N; i > N / 2; --i)
		check *= i;
	for (int i = N / 2; i > 0; --i)
		check /= i;
	check /= 2;
	cnt = 0;
	result = INF;
	comb(0,0);
	cout << result;
	return 0;
}
