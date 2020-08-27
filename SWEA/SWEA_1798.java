package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1798_완전탐색 {

	private static int T;
	private static int N;
	private static int M;
	private static List<int[]>[] adj;
	private static List<int[]>[] loc;
	private static List<Integer> path;
	private static List<Integer> resultPath;
	private static int time;
	private static int satisfy;
	private static int airport;
	private static boolean[] visited;
	private static int result;


	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// N개의 지점
			M = Integer.parseInt(st.nextToken());	// 휴가기간
			
			adj = new List[N+1];
			loc = new List[N+1];
			path = new ArrayList<>();
			resultPath = new ArrayList<>();
			visited = new boolean[N+1];
			result = 0;
			
			for(int i = 0; i <= N; ++i) {
				adj[i] = new ArrayList<>();
				loc[i] = new ArrayList<>();				
			}
			
			// 각 지점간의 거리를 인접리스트로
			for(int i = 0; i < N-1; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = i+1; j < N; ++j) {
					int distance = Integer.parseInt(st.nextToken());
					adj[i].add(new int[] {j,distance});
					adj[j].add(new int[] {i,distance});
				}
			}
			
			// 각 지점의 종류가 주어짐
			for(int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				char location = st.nextToken().charAt(0);
				
				// 1 : A, 2 : P, 3 : H
				switch(location) {
				case 'A' :
					loc[i].add(new int[] {1});
					airport = i;
					break;
				case 'P' :
					time = Integer.parseInt(st.nextToken());
					satisfy = Integer.parseInt(st.nextToken());
					loc[i].add(new int[] {2,time,satisfy});
					break;
				case 'H' :
					loc[i].add(new int[] {3});
					break;
				}
			}
			
			dfs(airport,0,0,1);
			
			sb.append("#" + t);
			for(int i = 0; i < resultPath.size(); ++i) {
				sb.append(" " + resultPath.get(i));
			}
			if(resultPath.size() == 0) sb.append(" " + 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int curr, int playTime, int satisfied, int day) {
		path.add(curr+1);
		
		/* 기저 조건 */
		if(day > M) return;
		if(curr == airport && playTime != 0) {
			if(satisfied > result) {
				result = satisfied;
				resultPath.clear();
				resultPath.add(result);
				for(int i = 1; i < path.size(); ++i) {
					resultPath.add(path.get(i));
				}
			}
			return;
		}
		
		/* 완전 탐색 */
		for(int[] temp : adj[curr]) {
			int next = temp[0];
			int d = temp[1];
			int s = 0;

			int[] which = loc[next].get(0);
			if(which[0] == 2) {
				d += which[1];
				s += which[2];
			} else if (which[0] == 1 && day != M) {
				continue;
			}
			
			if(playTime + d > 540 || visited[next]) continue;
			
			if(which[0] == 3) {
				dfs(next,0,satisfied,day+1);
			} else if(which[0] == 2) {
				visited[next] = true;
				dfs(next,playTime + d,satisfied + s,day);
				visited[next] = false;
			} else {
				dfs(next,playTime + d,satisfied + s,day);
			}
			path.remove(path.size()-1);
 		}
	}
}
