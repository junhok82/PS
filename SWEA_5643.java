package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5643 {
	private static int T;
	private static int N;
	private static int M;
	private static int u;
	private static int v;
	private static List<Integer>[] adj;
	private static int[] indegree;
	private static int curr;
	private static int result;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			indegree = new int[N+1];
			adj = new List[N+1];
			for(int i = 0; i <= N; ++i) {
				adj[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < M; ++i) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				adj[u].add(v);
				indegree[v]++;
			}
			
			result = 0;
			Sort();
			sb.append("#" + t + " ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	private static void Sort() {
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i < indegree.length; ++i) {
			if(indegree[i] == 0) q.offer(i);
		}
		
		for(int i = 0; i < N; ++i) {
			if(q.size() == 1) result++;
			curr = q.poll();
			System.out.print(curr + " ");
			
			for(int next : adj[curr]) {
				if(--indegree[next] == 0) q.offer(next);
			}
		}
		System.out.println();
		System.out.println("q.size() : " +q.size());
	}
}
