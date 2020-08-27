package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1949 {
	private static int T;
	private static int N;
	private static int K;
	private static int[][] m;
	private static int maxx;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			m = new int[N][N];
			visited = new boolean[N][N];
			maxx = Integer.MIN_VALUE;
			
			for(int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; ++j) {
					m[i][j] = Integer.parseInt(st.nextToken());
					maxx = Math.max(maxx, m[i][j]);
				}
			}
			
			result = 1;
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					if(m[i][j] == maxx) {
						visited[i][j] = true;
						DFS(i,j,1,false);
						visited[i][j] = false;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	private static int[] dy = {0,1,0,-1};
	private static int[] dx = {1,0,-1,0};
	private static int result;
	

	private static void DFS(int y, int x, int cnt, boolean pos) {
		int curr = m[y][x];
		if(cnt > result) result = cnt;
		
		for(int i = 0; i < 4; ++i) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) continue;
			int next = m[ny][nx];	
			if(next >= curr) {
				if(next - curr + 1 > K || pos) continue;				
				visited[ny][nx] = true;
				m[ny][nx] = (curr - 1);
				DFS(ny,nx,cnt+1,true);
				m[ny][nx] = next;
				visited[ny][nx] = false;
			} else {
				visited[ny][nx] = true;				
				DFS(ny,nx,cnt+1,pos);
				visited[ny][nx] = false;
			}
		}
	}
}
