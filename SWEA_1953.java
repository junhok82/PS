package SWEA;

import java.io.*;
import java.util.*;

public class SWEA_1953 {
	private static int T;
	private static int N;
	private static int M;
	private static int R;
	private static int C;
	private static int L;
	private static int[][] m;
	private static boolean[][] v;
	private static int[] dy = {0,1,0,-1};
	private static int[] dx = {1,0,-1,0};
	private static int[][] dir = {
			{1,1,1,1},
			{0,1,0,1},
			{1,0,1,0},
			{1,0,0,1},
			{1,1,0,0},
			{0,1,1,0},
			{0,0,1,1}
	};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t = 1; t <= T; ++t) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			m = new int[N][M];
			v = new boolean[N][M];
		
			for(int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; ++j) {
					m[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append("#" + t + " ").append(bfs()).append("\n");
		}
		System.out.print(sb);
	}

	private static int bfs() {
		int cnt = 1;
		int ret = 0;
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {R,C});
		v[R][C] = true;
		
		while(q.size() != 0) {
			int sz = q.size();
			for(int i = 0; i < sz; ++i) {
				int[] temp = q.poll();
				int y = temp[0];
				int x = temp[1];
				int[] d = dir[m[y][x]-1];
				ret++;
			
				for(int k = 0; k < 4; ++k) {
					if(d[k] == 0) continue;
					int ny = y + dy[k];
					int nx = x + dx[k];
					
					if(ny < 0 || ny >= N || nx < 0 || nx >= M || v[ny][nx] || m[ny][nx] == 0) continue;
					if(dir[m[ny][nx]-1][(k + 2) % 4] != 1) continue;
					v[ny][nx] = true;
					q.offer(new int[] {ny,nx});
				}
			}
			if(cnt == L) return ret;
			cnt++;
		}
		return ret;
	}
}
