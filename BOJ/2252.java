package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2252 {
	private static int N;
	private static int M;
	private static List<Integer>[] adj;
	private static Stack<Integer> result;
	private static int[] indegree;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new List[N+1];
		visited = new boolean[N+1];
		indegree = new int[N+1];
		result = new Stack<>();
		
		for(int i = 0; i <= N; ++i) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
			indegree[v]++;
		}	

		for(int i = 1; i <= N; ++i) {
			if(visited[i] || indegree[i] != 0) continue;
			dfs(i);	
		}
		
		while(result.size() != 0) {
			sb.append(result.pop() + " ");
		}
		System.out.print(sb);
	}

	private static void dfs(int curr) {
		visited[curr] = true;
		for(int next : adj[curr]) {
			if(!visited[next]) {
				dfs(next);
			}
		}
		result.push(curr);
	}
}
