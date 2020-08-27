package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_5653 {
	private static int T;
	private static int N;
	private static int M;
	private static int K;
	private static int[][] m;
	private static List<int[]> list[];
	private static int[] temp;
	private static int y;
	private static int x;
	private static int totalTime;
	private static int currTime;
	private static int ny;
	private static int nx;
	private static int dy[] = { 0, 1, 0, -1 };
	private static int dx[] = { 1, 0, -1, 0 };
	private static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			m = new int[N + K + K][M + K + K];
			list = new ArrayList[301];
			for (int i = 0; i <= 300; ++i) {
				list[i] = new ArrayList<int[]>();
			}
			result = 0;

			for (int i = K; i < N + K; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = K; j < M + K; ++j) {
					m[i][j] = Integer.parseInt(st.nextToken());
					int life = m[i][j];
					if (life != 0) {
						list[life].add(new int[] { i, j, life, life });
					}
				}
			}
			bfs();
			sb.append("#").append(t).append(" ").append(result + list[K].size()).append('\n');
		}
		System.out.println(sb);
	}

	private static void bfs() {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[2] > o2[2])
					return -1;
				else if (o1[2] < o2[2])
					return 1;
				return -1;
			}
		});

		for (int n = 1; n < K; ++n) {
			for (int i = 0; i < list[n].size(); ++i) {
				temp = list[n].get(i);
				pq.offer(new int[] { temp[0], temp[1], temp[2], temp[3] }); // y, x, totalTime, currTime
			}
			
			while (pq.size() != 0) {
				temp = pq.poll();
				y = temp[0];
				x = temp[1];
				totalTime = temp[2];
				currTime = temp[3];

				if (currTime < totalTime) {
					list[n + 1].add(new int[] { y, x, totalTime, currTime + 1 });
					continue;
				}
				for (int k = 0; k < 4; ++k) {
					ny = y + dy[k];
					nx = x + dx[k];
					if (m[ny][nx] != 0) continue;
				
					list[n + 1].add(new int[] { ny, nx, totalTime, 0 });
					m[ny][nx] = totalTime + n+1;	
				}
			}
		}

		// 활성 상태
		for (int i = 0; i < N + K + K; ++i) {
			for (int j = 0; j < M + K + K; ++j) {
				if (m[i][j] != 0 && m[i][j] > K)
					result++;
			}
		}
	}
}
