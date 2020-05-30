package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1520 {
	private static int N;
	private static int M;
	private static int[][] m;
	private static int[] dy = {0,1,0,-1};
	private static int[] dx = {1,0,-1,0};
	private static int[][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		m = new int[N][M];
		dp = new int[N][M];
		for(int i = 0; i < N; ++i) {
			Arrays.fill(dp[i], -1);
		}
		 
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; ++j) {
				m[i][j] = Integer.parseInt(st.nextToken());
			}	
		}
		System.out.print(dfs(0,0));
	}

	private static int dfs(int y, int x) {
		if(y == N-1 && x == M-1) return 1;
		if(dp[y][x] != -1) return dp[y][x];
		int origin = m[y][x];
		int ret = 0;
		
		for(int k = 0; k < 4; ++k) {
			int ny = y + dy[k];
			int nx = x + dx[k];
		
			if(ny < 0 || ny >= N || nx < 0 || nx >= M || m[ny][nx] >= origin) continue;
			ret += dfs(ny,nx);
		}
		return dp[y][x] = ret;
	}
}
