package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2468 {
	private static int N;
	private static int[][] m;
	private static int maxx;
	private static boolean[][] visited;
	private static int compo;
	private static int[] dy = {0,1,0,-1};
	private static int[] dx = {1,0,-1,0};
	private static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		m = new int[N][N];
		result = 0;

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; ++j) {
				m[i][j] = Integer.parseInt(st.nextToken());
				maxx = Math.max(m[i][j], maxx);
			}
		}
		
		for(int i = 0; i < maxx; ++i) {
			visited = new boolean[N][N];
			compo = 0;
			for(int j = 0; j < N; ++j) {
				for(int k = 0; k < N; ++k) {
					if(!visited[j][k] && m[j][k] > i) {
						dfs(j,k,i);
						compo++;
					}
				}
			}
			result = Math.max(result, compo);
		}
		
		System.out.println(result);
	}

	private static void dfs(int y, int x, int h) {
		visited[y][x] = true;
		
		for(int i = 0; i < 4; ++i) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] || m[ny][nx] <= h) continue;
			dfs(ny,nx,h);
		}
	}
}
