package SWEA;

import java.io.*;
import java.util.*;

public class SWEA_1953_dfs {
	private static int T;
	private static int N;
	private static int M;
	private static int R;
	private static int C;
	private static int L;
	private static int[][] m;
	private static int[][] v;
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
	private static int ret;

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
			v = new int[N][M];
			for(int i = 0; i < N; ++i) {
				Arrays.fill(v[i], Integer.MAX_VALUE);
			}
			ret = 0;
		
			for(int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; ++j) {
					m[i][j] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
			dfs(R,C,1);
			for(int i = 0; i < N; ++i) for(int j = 0; j < M; ++j) if(v[i][j] != Integer.MAX_VALUE && v[i][j] > 0) ret++; 
			
			sb.append("#" + t + " ").append(ret).append("\n");
		}
		System.out.print(sb);
	}

	private static void dfs(int y, int x, int time) {
		v[y][x] = time;
		if(time == L) return;

		int[] d = dir[m[y][x]];
		for(int i = 0; i < 4; ++i) {
			if(d[i] == 0) continue;
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || ny >= N || nx < 0 || nx >= M || v[ny][nx] <= time || m[ny][nx] < 0 || dir[m[ny][nx]][(i+2) % 4] == 0) continue;
			dfs(ny,nx,time+1);
		}
	}
}
