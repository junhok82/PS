package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SWEA_1249 {
	private static int T;
	private static int N;
	private static int[][] map;
	private static int dr[] = {-1,0,1,0};
	private static int dc[] = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int j = 0; j < N; j++) {
				String input = br.readLine();
				for (int k = 0; k < input.length(); k++) {
					map[j][k] = input.charAt(k) - '0';
				}
			}
			sb.append("#").append(i).append(" ").append(bfs()).append('\n');
		}
		System.out.print(sb);
	}

	private static int bfs() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		int[][] dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i],Integer.MAX_VALUE);
		}
		pq.offer(new int[] {0,0,0});
		dist[0][0] = 0;

		while(!pq.isEmpty()) {
			int[] temp = pq.poll();
			int r = temp[0];
			int c = temp[1];
			int d = temp[2];

			if(d != dist[r][c]) continue;
			if(r == N-1 && c == N-1) return dist[r][c];

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				int nd = map[nr][nc];

				if(dist[nr][nc] > dist[r][c] + nd) {
					dist[nr][nc] = dist[r][c] + nd;
					pq.offer(new int[] {nr,nc,dist[nr][nc]});
				}
			}
		}
		return -1;
	}
}
