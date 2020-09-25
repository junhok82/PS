package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_2667 {
	private static int N;
	private static List<Integer> cnt;
	private static char[][] map;
	private static int component = 0;
	private static int dr[] = {1,0,-1,0};
	private static int dc[] = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		cnt = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < input.length(); j++) {
				map[i][j] = input.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == '0') continue;
				component++;
				cnt.add(dfs(i,j) + 1);
			}
		}
		Collections.sort(cnt);

		StringBuilder sb = new StringBuilder();
		sb.append(component).append('\n');
		for (int out : cnt)
			sb.append(out).append('\n');
		System.out.print(sb);
	}

	private static int dfs(int r, int c) {
		map[r][c] = '0';
		int size = 0;

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '0') continue;
			size += (dfs(nr,nc) + 1);
		}
		return size;
	}
}
