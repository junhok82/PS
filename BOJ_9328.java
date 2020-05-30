package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_9328 {
	private static int T;
	private static int N;
	private static int M;
	private static String str;
	private static char[] m;
	private static List<int[]> start;
	private static int key;
	private static int[] dy = { 1, 0, -1, 0 };
	private static int[] dx = { 0, 1, 0, -1 };
	private static int ret;
	private static int target;
	private static boolean[] v;
	private static int[] temp;
	private static int data;
	private static char ch;
	private static int y;
	private static int x;
	private static int ny;
	private static int nx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int t = 0; t < T; ++t) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			m = new char[N * M];
			v = new boolean[N * M];
			start = new ArrayList<>();
			key = 0;
			ret = 0;
			target = 0;

			for (int i = 0; i < N; ++i) {
				str = br.readLine();
				for (int j = 0; j < M; ++j) {
					int data = i * M + j;
					m[data] = str.charAt(j);
					if ((i == 0 || j == 0 || i == N - 1 || j == M - 1) && m[data] != '*') {
						start.add(new int[] { i, j });
					}
					if (m[data] == '$')
						target++;
				}
			}
			str = br.readLine();
			for (int i = 0; i < str.length(); ++i) {
				char ch = str.charAt(i);
				if (ch == '0')
					break;
				key |= (1 << (ch - 'a'));
			}
			bfs();
			sb.append(ret).append("\n");
		}
		System.out.print(sb);
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		for (int[] next : start) {
			y = next[0];
			x = next[1];
			data = y * M + x;
			ch = m[data];
			if (ch <= 'Z' && ch >= 'A' && (key & (1 << (ch - 'A'))) == 0) continue;

			q.offer(new int[] { y, x });
			v[data] = true;
		}

		while (q.size() != 0) {
			temp = q.poll();
			y = temp[0];
			x = temp[1];
			data = y * M + x;

			if (m[data] == '$') {
				m[data] = '.';
				ret++;
				if (target == ret)
					return;
			}

			for (int k = 0; k < 4; ++k) {
				ny = y + dy[k];
				nx = x + dx[k];
				data = ny * M + nx;

				if (ny < 0 || ny >= N || nx < 0 || nx >= M || m[data] == '*' || v[data]) continue;
				v[data] = true;
				ch = m[data];
				
				if (ch <= 'Z' && ch >= 'A' && (key & (1 << (ch - 'A'))) == 0) continue;
				else if (ch <= 'z' && ch >= 'a' && (key & (1 << (ch - 'a'))) == 0) {
					key |= (1 << (ch - 'a'));
					Arrays.fill(v, false);
					q.clear();

					for (int[] next : start) {
						y = next[0];
						x = next[1];
						data = y * M + x;
						ch = m[data];
						if (ch <= 'Z' && ch >= 'A' && (key & (1 << (ch - 'A'))) == 0) continue;

						q.offer(new int[] { y, x });
						v[data] = true;
					}
					break;
				}
				q.offer(new int[] { ny, nx });
			}
		}
	}
}