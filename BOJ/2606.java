package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2606 {
	private static int E;
	private static int V;
	private static List<Integer>[] adj;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		visited = new boolean[V+1];
		adj = new List[V+1];
		for (int i = 0; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
			adj[v].add(u);
		}
		System.out.println(dfs(1));
	}

	private static int dfs(int curr) {
		visited[curr] = true;
		int temp = 0;
		for (int next : adj[curr]) if(!visited[next]) temp += (dfs(next) + 1);
		return temp;
	}
}
