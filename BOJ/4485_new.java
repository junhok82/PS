import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] dy = {0,1,0,-1};
	private static int[] dx = {1,0,-1,0};
	private static int T;
	private static String str;
	private static int[][] m;
	private static int[][] dist;


	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for(int t = 1;;++t) {
			T = Integer.parseInt(br.readLine());
			if(T == 0) break;
			m = new int[T][T];
			dist = new int[T][T];
			for(int i = 0; i < T; ++i) {
				str = br.readLine();
				Arrays.fill(dist[i], Integer.MAX_VALUE);
				for(int j = 0; j < T; ++j) {
					m[i][j] = str.charAt(j+j) - '0';
				}
			}
			sb.append("Problem " + t + ": " + bfs()).append('\n');
		}
		System.out.println(sb);
	}


	private static int bfs() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[2] <= o2[2]) return -1;
				else return 1;
			}
		});
		pq.offer(new int[] {0,0,m[0][0]});
		dist[0][0] = m[0][0];
		
		while(pq.size() != 0) {
			int[] curr = pq.poll();
			int y = curr[0];
			int x = curr[1];
			int d = curr[2];
			
			if(d != dist[y][x]) continue;
			if(y == T - 1 && x == T - 1) return dist[y][x];
			
			for(int i = 0; i < 4; ++i) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if(ny < 0 || nx < 0 || ny >= T || nx >= T) continue;
				int nd = m[ny][nx];
				
				if(dist[ny][nx] > dist[y][x] + nd) {
					dist[ny][nx] = dist[y][x] + nd;
					pq.offer(new int[] {ny,nx,dist[ny][nx]});
				}
				
			}
		}
		return dist[T-1][T-1];
	}
}
