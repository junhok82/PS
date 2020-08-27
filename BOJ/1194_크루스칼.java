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
	private static int result;
	private static int[] uf;
	private static PriorityQueue<Edge> pq = new PriorityQueue<>();
	private static int cnt;
	static class Edge implements Comparable<Edge>{
		int u, v, w;

		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		uf = new int[10001];
		
		for(int i = 0; i < E; ++i)
		{
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(u,v,w));
		}
		Arrays.fill(uf, -1);
		result = 0;
		cnt = 0;
		while(pq.size() != 0)
		{
			Edge e = pq.poll();
			if(merge(e.u,e.v))
			{
				result += e.w;
				if(++cnt == V-1) break;
			}
		}
		System.out.print(result);
	}
	static boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) return false;
		uf[a] = b;
		return true;
	}
	static int find(int a) {
		if(uf[a] < 0) return a;
		return uf[a] = find(uf[a]);
 	}
}
