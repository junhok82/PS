package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1916 {
	final static int INF = 987654321;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int[][] dist = new int[N+1][N+1];
		for (int i = 0; i <= N; i++) Arrays.fill(dist[i],INF);

		StringTokenizer st;
		for (int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			dist[u][v] = Math.min(dist[u][v],d);
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if(i == k) continue;
				for (int j = 1; j <= N; j++) {
					if(dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		System.out.println(dist[start][target]);
	}
}
