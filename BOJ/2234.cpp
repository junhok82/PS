#include <bits/stdc++.h>
#define Y first
#define X second
using namespace std;
typedef pair<int, int> pii;

int C, R, width, num, plus_width;
int m[51][51];
int dy[] = { 0,-1,0,1 };
int dx[] = { -1,0,1,0 };
int dir[] = { (1<<0), (1<<1), (1<<2), (1<<3) };
int visited[51][51];
int siz[51];

int bfs(int r, int c, int color)
{
  queue<pii> q;
  q.push(pii(r, c));
  visited[r][c] = color;

  int cnt = 0;
  while (q.size())
  {
      const pii& p = q.front();
      int y = p.Y;
      int x = p.X;
      int d = m[y][x];
      cnt++;
      q.pop();
  
      for (int k = 0; k < 4; ++k)
      {
            if (dir[k] & d) continue;
            int ny = y + dy[k];
            int nx = x + dx[k];
            if (ny < 0 || ny >= R || nx < 0 || nx >= C || visited[ny][nx] == color) continue;
            visited[ny][nx] = color;
            q.push(pii(ny, nx));
          }
    }
  siz[color] = cnt;
  return cnt;
}

int check(int y, int x)
{
  int color = visited[y][x];
  int out = siz[color];
  int temp = out;
  for (int k = 0; k < 4; ++k)
  {
      int ny = y + dy[k];
      int nx = x + dx[k];
      if (ny < 0 || ny >= R || nx < 0 || nx >= C || visited[ny][nx] == color) continue;
      out = max(out, temp + siz[visited[ny][nx]]);
    }
  return out;
}

int main()
{
  ios::sync_with_stdio(0), cin.tie(0);
  cin >> C >> R;
  for (int i = 0; i < R; ++i)
    for (int j = 0; j < C; ++j)
      cin >> m[i][j];

  width = 0, num = 0, plus_width = 0;
  for (int i = 0; i < R; ++i)
    for (int j = 0; j < C; ++j)
      if (!visited[i][j])
        width = max(width, bfs(i, j, ++num));

  for (int i = 0; i < R; ++i)
    for (int j = 0; j < C; ++j)
      plus_width = max(plus_width, check(i, j));

  cout << num << '\n' << width << '\n' << plus_width;

}
