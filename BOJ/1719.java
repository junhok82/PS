package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1719 {
	private static int V;
	private static int E;
	private static List<int[]>[] adj;
	private static StringBuilder sb;
	private static int INF = 987654321;
	private static int[] dist;
	private static int[] prev;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		prev = new int[V+1];
		dist = new int[V+1];
		adj = new List[V+1];
		for (int i = 0; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[u].add(new int[]{v,w});
			adj[v].add(new int[]{u,w});
		}

		sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			bfs(i);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int from) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		pq.offer(new int[] {from,0});;
		Arrays.fill(dist,INF);
		prev[from] = -1;
		dist[from] = 0;

		while(!pq.isEmpty()) {
			int[] temp = pq.poll();
			int curr = temp[0];
			int d = temp[1];

			if(dist[curr] != d) continue;
			for(int[] nextVertex : adj[curr]) {
				int next = nextVertex[0];
				int nd = nextVertex[1];
				if(dist[next] > dist[curr] + nd) {
					dist[next] = dist[curr] + nd;
					prev[next] = curr;
					pq.offer(new int[] {next, dist[next]});
				}
			}
		}

		for (int to = 1; to <= V; to++) {
			if(from == to) sb.append("-");
			else sb.append(find(to,from));
			sb.append(" ");
		}
	}

	private static int find(int to, int from) {
		if(prev[to] == from) return to;
		return find(prev[to],from);
	}
}
