package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1719_2 {
	private static int V;
	private static int E;
	private static StringBuilder sb;
	private static int INF = 987654321;
	private static int[][] dist;
	private static int[][] path;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		dist = new int[V + 1][V + 1];
		path = new int[V + 1][V + 1];
		for (int i = 0; i <= V; i++) {
			Arrays.fill(dist[i],INF);
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			dist[u][v] = Math.min(dist[u][v],w);
			dist[v][u] = Math.min(dist[v][u],w);
			path[u][v] = v;
			path[v][u] = u;
		}

		sb = new StringBuilder();

		for (int temp = 1; temp <= V; temp++) {
			for (int from = 1; from <= V; from++) {
				if(temp==from) continue;
				for (int to = 1; to <= V; to++) {
					if(dist[from][to] > dist[from][temp] + dist[temp][to]) {
						dist[from][to] = dist[from][temp] + dist[temp][to];
						path[from][to] = path[from][temp];
					}
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i == j) sb.append("-");
				else sb.append(path[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
