package BOJ;

import java.io.*;

public class BOJ_17069 {

	private static int N;
	private static String str;
	private static boolean[][] m;
	private static long[][][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		m = new boolean[N][N];
		dp = new long[N][N][N][N];
		
		for(int i = 0; i < N; ++i) {
			str = br.readLine();
			for(int j = 0; j < N; ++j) {
				m[i][j] = ((str.charAt(j+j) == '1') ? false : true);
			}
		}
		System.out.print(dfs(0,0,0,1));
	}

	private static long dfs(int er, int ec, int r, int c) {
		if((r == N-1 && c == N - 1)) return 1;
		if(dp[er][ec][r][c] != 0) return dp[er][ec][r][c];
		if(er != r && r + 1 < N && m[r+1][c]) dp[er][ec][r][c] += dfs(r,c,r+1,c);
		if(ec != c && c + 1 < N && m[r][c+1]) dp[er][ec][r][c] += dfs(r,c,r,c+1);
		if(r+1 < N && c+1 < N && m[r+1][c+1] && m[r+1][c] && m[r][c+1]) dp[er][ec][r][c] += dfs(r,c,r+1,c+1);
		return dp[er][ec][r][c];
	}
}
