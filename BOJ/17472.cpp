#include <bits/stdc++.h>
using namespace std;

#define all(x) x.begin(),x.end()
typedef pair<int, int> pii;
const int MAX_N = 11;
const int MAX_V = 7;

int V,row,col;
int uf[MAX_V];
int dy[] = { 0,1,0,-1 };
int dx[] = { 1,0,-1,0 }; 
int m[MAX_N][MAX_N];
bool visited[MAX_N][MAX_N] = { false, };

struct Edge {
	int u, v, w;
	Edge() : Edge(-1, -1, 0) {}
	Edge(int u, int v, int w) : u(u), v(v), w(w) {}
	bool operator <(const Edge& O) const {	return w < O.w; }
};

vector<Edge> edge;

int find(int a)
{
	if (uf[a] < 0)	return a;
	return uf[a] = find(uf[a]);
}

bool merge(int a, int b)
{
	a = find(a);
	b = find(b);
	if (a == b)	return false;
	uf[b] = a;
	return true;
}

void init(int a,int b,int num)
{
	queue<pii> q;
	q.push(pii(a, b));
	visited[a][b] = true;
	m[a][b] = num;

	while (q.size())
	{
		pii& p = q.front();
		int y = p.first;
		int x = p.second;
		q.pop();

		for (int i = 0; i < 4; ++i)
		{
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || ny >= row || nx < 0 || nx >= col || visited[ny][nx] || !m[ny][nx])	continue;
			m[ny][nx] = num;
			visited[ny][nx] = true;
			q.push(pii(ny, nx));
		}
	}
}

int main()
{
	ios::sync_with_stdio(0), cin.tie(0);
	cin >> row >> col;

	for (int i = 0; i < row; ++i)
		for (int j = 0; j < col; ++j)
			cin >> m[i][j];

	int compo = 1;
	for (int i = 0; i < row; ++i)
		for (int j = 0; j < col; ++j)
			if (m[i][j] && !visited[i][j])
				init(i,j,compo++);

	bool start = false;
	bool ing = false;
	int s, e,cnt = 0;
	// 가로 길이
	for (int i = 0; i < row; ++i)
	{
		start = false;
		ing = false;
		cnt = 0;
		for (int j = 0; j < col; ++j)
		{
			if (!ing && m[i][j])
			{
				s = m[i][j];
				start = true;
			}
			else if (start)
				ing = true;

			if (ing && m[i][j])
			{
				ing = false;
				e = m[i][j];
				if (cnt > 1)
					edge.push_back(Edge(s,e,cnt));
				cnt = 0;
				s = m[i][j];
			}

			if (ing)
				cnt++;
		}
	}

	// 세로 길이
	for (int j = 0; j < col; ++j)
	{
		start = false;
		ing = false;
		cnt = 0;
		for (int i = 0; i < row; ++i)
		{
			if (!ing && m[i][j])
			{
				s = m[i][j];
				start = true;
			}
			else if (start)
				ing = true;

			if (ing && m[i][j])
			{
				ing = false;
				e = m[i][j];
				if (cnt > 1)
					edge.push_back(Edge(s, e, cnt));
				cnt = 0;
				s = m[i][j];
			}

			if (ing)
				cnt++;
		}
	}

	sort(all(edge));
	fill(uf, uf + MAX_V,-1);

	int result = 0;
	cnt = 0;
	for (int i = 0; ; ++i)
	{
		if (i == edge.size())
		{
			cout << -1;
			return 0;
		}
		if (merge(edge[i].u, edge[i].v))
		{
			result += edge[i].w;
			if (++cnt == compo-2) break;
		}
	}
	cout << result;
}
