import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class test {
	private static int V;
	private static int E;
	private static int u;
	private static int v;
	private static int w;
	private static List[] adj = new List[10001];
	private static int[] dist;
	private static int[] temp;
	private static int curr;
	private static boolean[] visited;
	private static int result;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dist = new int[10001];
		temp = new int[2];
		visited = new boolean[10001];
		for(int i = 0; i <= V; ++i)
			adj[i] = new ArrayList<int[]>();
		
		for(int i = 0; i < E; ++i)
		{
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			adj[u].add(new int[] {v,w});
			adj[v].add(new int[] {u,w});
		}
		result = 0;
		bfs();
		System.out.print(result);
	}

	private static void bfs() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] > o2[1]) return 1;
				else if(o1[1] < o2[1]) return -1;
				return 0;
			}
		}); 
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.offer(new int[] {1,0});
		dist[1] = 0;
		
		while(pq.size() != 0)
		{
			do {
				temp = pq.poll();
				curr = temp[0];
			}while(pq.size() != 0 && visited[curr]);
			if(visited[curr]) break;
			visited[curr] = true;
			result += dist[curr];
			
			for(int i = 0; i < adj[curr].size(); ++i)
			{
				int[] c = (int[]) adj[curr].get(i);
				int next = c[0];
				int nd = c[1];
				if(dist[next] > nd)
				{
					dist[next] = nd;
					pq.offer(new int[] {next,nd});
				}
			}
		}
	}
}
