package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1012 {
	private static int T;
	private static int col;
	private static int row;
	private static int K;
	private static int y;
	private static int x;
	private static boolean[][] m;
	private static int[] dr = {0,1,0,-1};
	private static int[] dc = {1,0,-1,0};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t = 0; t < T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			m = new boolean[row][col];
			
			for(int i = 0; i < K; ++i) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				m[y][x] = true;
			}
			
			int result = 0;
			for(int i = 0; i < row; ++i) {
				for(int j = 0; j < col; ++j) {
					if(m[i][j]) {
						dfs(i,j);
						result++;
					}
				}
			}
			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}

	private static void dfs(int r, int c) {
		m[r][c] = false;
		
		for(int i = 0; i < 4; ++i) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr < 0 || nc < 0 || nr >= row || nc >= col || !m[nr][nc]) continue;
			dfs(nr,nc);
		}
	}
}
