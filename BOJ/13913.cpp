#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <utility>
using namespace std;
const int INF = 0x7FFFFFFF;
int diry[] = { 0,1,0,-1 };
int dirx[] = { 1,0,-1,0 };
bool visited[100001] = { false, };

struct BB {
	int curr;
	int v;
	vector<int> path;
	BB(int curr, int v,vector<int> path) : curr(curr), v(v),path(path) {}
};

queue<BB> Q;

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	int start, target;
	cin >> start >> target;
	if (start > target)
	{
		cout << start - target << endl;
		for (int i = start; i >= target; --i)
			cout << i << " ";
		return 0;
	}
	visited[start] = true;
	Q.push(BB(start, 0,vector<int>(0,0)));

	while (!Q.empty())
	{
		BB b = Q.front();
		int curr = b.curr;
		int v = b.v;
		vector<int> path = b.path;
		Q.pop();

		if (curr == target)
		{
			cout << v << endl;
			cout << start << " ";
			for (int next : path)
				cout << next << " ";
			return 0;
		}

		if (curr - 1 >= 0)
			if (!visited[curr - 1])
			{
				path.push_back(curr - 1);
				Q.push(BB(curr - 1, v + 1,path));
				visited[curr - 1] = true;
				path.pop_back();
			}
		if (curr + 1 <= target)
			if (!visited[curr + 1])
			{
				path.push_back(curr + 1);
				Q.push(BB(curr + 1, v + 1,path));
				visited[curr + 1] = true;
				path.pop_back();
			}
		if (curr < target && curr * 2 <= 100000)
			if (!visited[curr * 2])
			{
				path.push_back(curr * 2);
				Q.push(BB(curr * 2, v + 1,path));
				visited[curr * 2] = true;
				path.pop_back();
			}
	}
	return 0;
}
