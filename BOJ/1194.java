package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194 {
	private static int N;
	private static int M;
	private static String str;
	private static char[][] m;
	private static int[] start;
	private static int[] dy = {1,0,-1,0};
	private static int[] dx = {0,1,0,-1};
	private static boolean[][][] checked;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		m = new char[N][M];
		checked = new boolean[N][M][(1 << 6)];
		
		for(int i = 0; i < N; ++i) {
			str = br.readLine();
			for(int j = 0; j < M; ++j) {
				m[i][j] = str.charAt(j);
				if(m[i][j] == '0') {
					start = new int[] {i,j};
				}
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {start[0],start[1],0});
		checked[start[0]][start[1]][0] = true;
		int cnt = 0;
		
		while(q.size() != 0) {
			int sz = q.size();
			for(int i = 0; i < sz; ++i) {
				int[] temp = q.poll();
				int y = temp[0];
				int x = temp[1];
				int visit = temp[2];
				if(m[y][x] == '1') return cnt;
				
				for(int k = 0; k < 4; ++k) {
					int ny = y + dy[k];
					int nx = x + dx[k];
		
					if(ny < 0 || ny >= N || nx < 0 || nx >= M || m[ny][nx] == '#' || checked[ny][nx][visit]) continue;
					
					int key = visit;
					char ch = m[ny][nx];
					
					if(ch >= 'A' && ch <= 'F') {
						if((key & (1 << (ch - 'A'))) == 0) {
							continue;
						}
					} 
					else if(ch >= 'a' && ch <= 'f') {
						key |= (1 << (ch - 'a'));
					}
					checked[ny][nx][key] = true;
					q.offer(new int[] {ny,nx,key});
				}
			}
			cnt++;
		}
		return -1;
	} 
}


  