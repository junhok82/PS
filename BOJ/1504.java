import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int E;
	private static int u;
	private static int v;
	private static int w;
	private static List<int[]>[] adj;
	private static int t1;
	private static int t2;
	private static int[] out;
	private static int curr;
	private static int d;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adj = new List[N+1];
		
		for(int i = 0; i <= N; ++i) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; ++i) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			adj[u].add(new int[] {v,w});
			adj[v].add(new int[] {u,w});
		}
		
		st = new StringTokenizer(br.readLine());
		
		t1 = Integer.parseInt(st.nextToken());
		t2 = Integer.parseInt(st.nextToken());
		
		int betw = go(t1,t2);
		int len1 = go(1,t1) + go(t2,N) + betw;
		int len2 = go(1,t2) + go(t1,N) + betw;
		System.out.println((len2 <= len1) ? len2 : len1);
		
	}

	private static int go(int s, int e) {
		
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] >= o2[1]) return 1;
				else return -1;
			}
		});
		
		pq.offer(new int[] {s,0});
		dist[s] = 0;
		
		while(pq.size() != 0) {
			out = pq.poll();
			curr = out[0];
			d = out[1];
			
			if(curr == e) return dist[e];
			if(dist[curr] != d) continue;
			
			for(int[] nextOut : adj[curr]) {
				int next = nextOut[0];
				int nd = nextOut[1];
				
				if(dist[next] > dist[curr] + nd) {
					dist[next] = dist[curr] + nd;
					pq.offer(new int[] {next,dist[next]});
				}
			}
		}
		
		System.out.println(-1);
		System.exit(0);
		return -1;
	}
}
