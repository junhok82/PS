package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9663 {
	private static int N;
	private static boolean[][] v;
	private static int[] dy = { -1, -1, -1 };
	private static int[] dx = { -1, 0, 1 };
	private static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		v = new boolean[N][N];
		result = 0;

		for (int i = 0; i < N; ++i) {
			v[0][i] = true;
			dfs(1, 1);
			v[0][i] = false;
		}
		System.out.println(result);
	}

	private static void dfs(int y, int cnt) {
		if(cnt == N) {
			result++;
			return;
		}
		
		for (int x = 0; x < N; ++x) {
			boolean flag = false;
			for (int k = 0; k < 3; ++k) {
				int ny = y + dy[k];
				int nx = x + dx[k];

				while (isIn(ny, nx)) {
					if (v[ny][nx]) {
						flag = true;
						break;
					}
					else {
						ny += dy[k];
						nx += dx[k];
					}
				}
				if(flag) break;
			}
			if(!flag) {
				v[y][x] = true;
				dfs(y+1, cnt + 1);
				v[y][x] = false;
			}
		}
	}

	private static boolean isIn(int ny, int nx) {
		return (ny < 0 || nx < 0 || ny >= N || nx >= N) ? false : true;
	}
}
