package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4991 {
	private static int C;
	private static int R;
	private static String str;
	private static int sx;
	private static int sy;
	private static int[] dy = { 1, 0, -1, 0 };
	private static int[] dx = { 0, 1, 0, -1 };
	private static int result;
	private static List<int[]> dty;
	private static List<int[]> arr;
	private static int[] index;
	private static char[][] m;
	private static int[][] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		dty = new ArrayList<>();
		arr = new ArrayList<>();
	
		while (true) {
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken()); 
			if (R == 0 && C == 0)
				return;
			m = new char[R][C];
		
			int id = 1;
			for (int i = 0; i < R; ++i) {
				str = br.readLine();
				for (int j = 0; j < C; ++j) {
					m[i][j] = str.charAt(j);
					if (m[i][j] == 'o') {
						sy = i;
						sx = j;
						m[sy][sx] = '0';
					} else if (m[i][j] == '*') {
						dty.add(new int[] {i,j});
						m[i][j] = Character.forDigit(id++, 10);
					}
				}
			}
			
			
			index = new int[dty.size()];
			for(int i = 0; i < index.length; ++i) {
				index[i] = i+1;
			}
			result = Integer.MAX_VALUE;
			
			adj = new int[dty.size()+1][dty.size()+1];
			for(int i = 0; i < dty.size() + 1; ++i) {
				Arrays.fill(adj[i], -1); 
			}
			
			int idx = 1;
			// 거리 구하기
			for(int[] dt : dty) {
				bfs(dt[0],dt[1],idx++);
			}

			do {
				int resultTemp = 0;
				boolean flag = false;

				int u = 0;
				for(int i = 0; i < index.length; ++i) {
					int v = index[i];
					int d = adj[u][v];
					resultTemp += adj[u][v];
					if(resultTemp >= result || d == -1) {
						flag = true;
						break;
					}
					u = v;
				}
				if(flag) continue;
				else if(result > resultTemp) result = resultTemp;
				
			}while(nextP(index));
			
			System.out.println(result == Integer.MAX_VALUE ? -1 : result);
			dty.clear();
			arr.clear();
		}
	}

	private static void bfs(int r, int c, int idx) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		q.offer(new int[] {r,c});
		visited[r][c] = true;
		char ch = Character.forDigit(idx, 10);
		
		int cnt = 0;
		while(q.size() != 0) {
			int sz = q.size();
			for(int i = 0; i < sz; ++i) {
				int[] temp = q.poll();
				int y = temp[0];
				int x = temp[1];
				
				if( m[y][x] != ch && m[y][x] != '.') {
					adj[idx][m[y][x] - '0'] = cnt;
					adj[m[y][x] - '0'][idx] = cnt;
				}
				
				for(int k = 0; k < 4; ++k) {
					int ny = y + dy[k];
					int nx = x + dx[k];
					if(ny < 0 || ny >= R || nx < 0 || nx >= C || visited[ny][nx] || m[ny][nx] == 'x') continue;
					visited[ny][nx] = true;
					q.offer(new int[] {ny,nx});
				}
			}
			++cnt;
		}
	}


	private static boolean nextP(int[] data) {
		int i = data.length - 1;
		int j = data.length - 1;
		
		while(i > 0 && data[i-1] > data[i]) --i;
		if(i <= 0) return false;
	 
		while(data[i-1] >= data[j]) --j;
		data[i - 1] ^= data[j];
		data[j] ^= data[i - 1];
		data[i - 1] ^= data[j];
	 
		j = data.length - 1;
		for (; i < j; ++i, --j) {
			data[i] ^= data[j];
			data[j] ^= data[i];
			data[i] ^= data[j];
		}
		return true;
	}
}
