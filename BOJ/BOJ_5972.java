package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_5972 {

	private static int N;
	private static int M;
	private static int d;
	private static int u;
	private static int v;
	private static List<int[]>[] adj;
	private static int[] dist;	
	private static boolean[] visited;
	private static int[] temp;
	private static int curr;
	private static int next;
	private static int nd;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		dist = new int[N+1];
		adj = new List[N+1];
		
		Arrays.fill(dist, 98765432);
		
		for(int i = 0; i < N+1; ++i) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());	
			adj[u].add(new int[] {v,d});
			adj[v].add(new int[] {u,d});
		}
		bfs();
		System.out.print(dist[N]);
	}

	private static void bfs() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] > o2[1] || o1[1] == o2[1]) return 1;
				return -1;
			}
		});
		
		pq.offer(new int[] {1,0});
		dist[1] = 0;
		
		while(pq.size() != 0) {
			do {
				temp = pq.poll();
				curr = temp[0];
			} while(visited[curr] && pq.size() > 0);
			if(visited[curr]) break;
			visited[curr] = true;
			if(curr == N) break;
			
			for(int[] nextNode : adj[curr]) {
				next = nextNode[0];
				nd = nextNode[1];
				if(dist[next] > dist[curr] + nd) {
					dist[next] = dist[curr] + nd;
					pq.offer(new int[] {next,dist[next]});
				}
			}
		}
	}
}
