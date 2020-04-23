#include <bits/stdc++.h>
using namespace std;

int N;
int arr[201] = {0,};

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> N;
	for(int i = 0; i < N; ++i)
		cin >> arr[i];
	vector<int> list;

	list.push_back(arr[0]);
	for(int i = 1; i < N; ++i)
	{
		int s = 0, e = list.size();
		int data = arr[i];

		while(s <= e)
		{
			int mid = (s+e)/2;
			if(list[mid] < data)
				s = mid + 1;
			else if(list[mid] >= data)
				e = mid - 1;
		}

		if(s < list.size())
			list[s] = data;
		else
			list.push_back(data);
	}
	cout << N-list.size();

}
