package BOJ;

import java.io.*;

public class BOJ_17070 {

	private static int N;
	private static String str;
	private static boolean[][] m;
	private static int ret = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		m = new boolean[N][N];
		
		for(int i = 0; i < N; ++i) {
			str = br.readLine();
			for(int j = 0; j < N; ++j) {
				m[i][j] = ((str.charAt(j+j) == '1') ? false : true);
			}
		}
		dfs(0,0,0,1);
		System.out.print(ret);
	}

	private static void dfs(int er, int ec, int r, int c) {
		if(r == N-1 && c == N - 1) { ret++;return; }
		if(er != r && r + 1 < N && m[r+1][c]) dfs(r,c,r+1,c);
		if(ec != c && c + 1 < N && m[r][c+1]) dfs(r,c,r,c+1);
		if(r+1 < N && c+1 < N && m[r+1][c+1] && m[r+1][c] && m[r][c+1]) dfs(r,c,r+1,c+1);
	}
}
