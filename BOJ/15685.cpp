#include <bits/stdc++.h>
using namespace std;
typedef unsigned char uchar;

uchar dir[] = { (1<<1), (1<<2), (1<<3), (1<<4) };	// R,U,L,D
int N;
int r[21], c[21], d[21], g[21];
uchar m[101][101] = {0x00,};

int resultCheck()
{
	 int cnt = 0;
	 uchar flag = 0x00;
	 for(int i = 0; i < 100; ++i)
			for(int j = 0; j < 100; ++j)
				 if( (m[i][j] ^ flag) && (m[i][j+1] ^ flag) && (m[i+1][j] ^ flag) && (m[i+1][j+1] ^ flag) )
						cnt++;
	 return cnt;
}

void go(int nd, int& ny, int& nx)
{
	 m[ny][nx] |= dir[nd];
	 switch(nd)
	 {
			case 0 :
				 nx++;
				 break;
			case 1 :
				 ny--;
				 break;
			case 2 :
				 nx--;
				 break;
			case 3 :
				 ny++;
				 break;
	 }
	 m[ny][nx] |= dir[(nd+1) % 4];
}

void solve()
{
	 for(int i = 0; i < N; ++i)
	 {
			int y = r[i];
			int x = c[i];

			vector<int> next;
			next.push_back(d[i]);
			go(next[0],y,x);

			for(int j = 0; j < g[i]; ++j)
			{
				 int sz = next.size();
				 for(int n = sz-1; n >= 0; --n)
				 {
						go((next[n]+1) % 4,y,x);
						next.push_back((next[n]+1) % 4);
				 }
			}
	 }
}

int main()
{
	 ios::sync_with_stdio(0), cin.tie(0);
	 cin >> N;
	 for(int i = 0, i < N; ++i)
			cin >> c[i] >> r[i] >> d[i] >> g[i];
	 solve();
	 cout << resultCheck();
}
