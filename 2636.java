package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636 {
	private static int N;
	private static int M;
	private static String str;
	private static int[][] m;
	private static int[] dy = {1,0,-1,0};
	private static int[] dx = {0,1,0,-1};
	private static boolean[][] v;
	private static int total;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		m = new int[N][M];
		v = new boolean[N][M];
		total = N*M;
		
		for(int i = 0; i < N; ++i) {
			str = br.readLine();
			for(int j = 0; j < M; ++j) {
				m[i][j] = str.charAt(2*j) - '0';
			}
		}
		
		bfs();
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> nextQ = new LinkedList<>();
		q.offer(new int[] {0,0});
		v[0][0] = true;
		
		int cnt = 0;
		int size = 0;
		
		int flag = 0;
		
		while(flag < total) {
		
			while(q.size() != 0) {
				int[] temp = q.poll();
				int y = temp[0];
				int x = temp[1];
				flag++;
				
				for(int k = 0; k < 4; ++k) {
					int ny = y + dy[k];
					int nx = x + dx[k];
					
					if(ny < 0 || ny >= N || nx < 0 || nx >= M || v[ny][nx]) continue;
					v[ny][nx] = true;
					if(m[ny][nx] == 0) q.offer(new int[] {ny,nx});
					else nextQ.offer(new int[] {ny,nx});
				}
			}
			
			while(nextQ.size() != 0) {				
				int sz = nextQ.size();
				int tempSize = 0;
				for(int i = 0; i < sz; ++i) {
					int[] temp = nextQ.poll();
					int y = temp[0];
					int x = temp[1];
					flag++;
					tempSize++;
					m[y][x] = 0;
					
					for(int k = 0; k < 4; ++k) {
						int ny = y + dy[k];
						int nx = x + dx[k];
						
						if(ny < 0 || ny >= N || nx < 0 || nx >= M || v[ny][nx]) continue;
						v[ny][nx] = true;
						if(m[ny][nx] == 0) q.offer(new int[] {ny,nx});
						else nextQ.offer(new int[] {ny,nx});
					}
				}
				size = tempSize;
				++cnt;
				break;
			}
		}
		
		System.out.println(cnt);
		System.out.println(size);
	}
}
