package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_16235 {

	private static int N;
	private static String str;
	private static int[][] m;
	private static int[] dy = {0,1,0,-1};
	private static int[] dx = {1,0,-1,0};
	private static int shark_y;
	private static int shark_x;
	private static int[] temp;
	private static boolean[][] visited;
	private static int sharkSize;
	private static int currEat;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		m = new int[N][N];
		sharkSize = 2; 
		currEat = 0;
		visited = new boolean[N][N];
		for(int i = 0; i < N; ++i) {
			str = br.readLine();
			for(int j = 0; j < N; ++j) {
				m[i][j] = str.charAt(2*j) - '0'; 
				if(m[i][j] == 9) {
					shark_y = i;
					shark_x = j;
				}
			}
		}
		System.out.print(simulation());
	}
	
	private static int simulation() {
		Queue<int[]> q = new LinkedList<>();		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] > o2[0]) return 1;
				else if (o1[0] == o2[0]) {
					if(o1[1] < o2[1]) return -1;
					return 1;
				}
				return -1;
			}
		});
		
		int cnt = 0;
		
		while(true) {
			q.offer(new int[] {shark_y,shark_x});
			visited[shark_y][shark_x] = true;
			
			int count = 0;
			boolean pos = false;
			while(q.size() != 0) {			
				int sz = q.size();
				for(int k = 0; k < sz; ++k) {				
					temp = q.poll();
					int y = temp[0];
					int x = temp[1];
					for(int i = 0; i < 4; ++i) {
						int ny = y + dy[i];
						int nx = x + dx[i];
						if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] || m[ny][nx] > sharkSize) continue;
						visited[ny][nx] = true;
						if(m[ny][nx] != 0 && m[ny][nx] < sharkSize) {
							pq.offer(new int[] {ny,nx});			
							pos = true;
						}
						q.offer(new int[] {ny,nx});
					}
				}
				count++;
				if(pos) break;
			}
			if(!pos) break;
			
			temp = pq.poll();			
			currEat++;
			m[shark_y][shark_x] = 0;
			m[temp[0]][temp[1]] = 9;
			cnt += count;
			shark_y = temp[0];
			shark_x = temp[1];

			if(currEat == sharkSize) {
				currEat = 0;
				sharkSize++;
			}
			pq.clear();
			q.clear();
			for(int i = 0; i < N; ++i) {
				Arrays.fill(visited[i], false);
			}
		}
		return cnt;
	}
}
