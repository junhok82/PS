package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14502 {
	private static int R;
	private static int C;
	private static String str;
	private static int[][] m;
	private static int[] dy = {0,1,0,-1};
	private static int[] dx = {1,0,-1,0};
	private static List<int[]> build;
	private static List<int[]> virus;
	private static int cnt = 0;
	private static int ret = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		m = new int[R][C];
		build = new ArrayList<>();
		virus = new ArrayList<>();
		
		for(int i = 0; i < R; ++i) {
			str = br.readLine();
			for(int j = 0; j < C; ++j) {
				m[i][j] = str.charAt(j+j) - '0';
				if(m[i][j] == 0) {
					build.add(new int[] {i,j});
					cnt++;
				}
				else if(m[i][j] == 2) virus.add(new int[] {i,j});
			}
		}
		
		dfs(0,0);
		System.out.println(ret - 3);
	}

	private static void dfs(int idx,int curr) {
		if(idx == 3) {
			ret = Math.max(bfs(),ret);
			return;
		}
		
		for(int k = curr; k < build.size(); ++k) {
			int y = build.get(k)[0];
			int x = build.get(k)[1];
			
			m[y][x] = 1;
			dfs(idx + 1, k+1);
			m[y][x] = 0;
		}
	}

	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		for(int[] v : virus) q.offer(new int[] {v[0],v[1]});
		boolean[][] visited = new boolean[R][C];
		int tempCnt = 0;
		
		while(q.size() != 0) {
			int[] temp = q.poll();
			int y = temp[0];
			int x = temp[1];
			
			for(int i = 0; i < 4; ++i) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if(ny < 0 || nx < 0 || ny >= R || nx >= C || visited[ny][nx] || m[ny][nx] != 0) continue;
				visited[ny][nx] = true;
				tempCnt++;
				q.offer(new int[] {ny,nx});
			}
		}
		return cnt - tempCnt;
	}
}
