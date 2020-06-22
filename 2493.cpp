#include<iostream>
#include<stack>
#include<vector>
#include <utility>
using namespace std;

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	int num;
	cin >> num;
	vector<int> v;
	stack<pair<int,int>> S;
	vector<int> result(num,0);
	for (int i = 0; i < num; ++i)
	{
		int input;
		cin >> input;
		v.push_back(input);
	}

	for (int i = v.size() - 1; i >= 0; --i)
	{
		if (S.empty())
			S.push(make_pair(v[i],i));
		else
		{
			while (v[i] >= S.top().first)
			{
				result[S.top().second] = i+1;
				S.pop();	
				if (S.empty())
					break;
			}
			S.push(make_pair(v[i],i));
		}
	}
	for (int i = 0; i < result.size(); ++i)
		cout << result[i] << " ";
	return 0;
}
