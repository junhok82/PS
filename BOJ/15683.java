import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static int M;
	private static String str;
	private static int[][] m;
	
	private static int[] r = {0,1};
	private static int[] u = {-1,0};
	private static int[] l = {0,-1};
	private static int[] d = {1,0};
	
	private static char[] d1= {'l','u','r','d'}; 	
	private static char[][] d2 = { {'l','r'}, {'u','d'} };	
	private static char[][] d3 = { {'l','u'}, {'u','r'}, {'r','d'}, {'d','l'} };
	private static char[][] d4 = { {'l','u','r'}, {'u','r','d'}, {'r','d','l'}, {'d','l','u'} };
	private static int[] dy5 = {1,0,-1,0};
	private static int[] dx5 = {0,1,0,-1};
	
	private static List<int[]> cctv;
	private static boolean[][] visited;
	private static int result;	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		m = new int[N][M];
		cctv = new ArrayList<>();
		
		for(int i = 0; i < N; ++i) {
			str = br.readLine();
			for(int j = 0; j < M; ++j) {
				m[i][j] = str.charAt(2*j) - '0';
				if(m[i][j] != 0 && m[i][j] != 6) {
					cctv.add(new int[] {i,j});
				}
			}
		}

		result = Integer.MAX_VALUE;
		visited = new boolean[N][M];
		dfs(0);
		System.out.println(result);
	}

	private static void dfs(int curr) {
		if(curr == cctv.size()) {
			int cnt = 0;
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < M; ++j) {
					if(visited[i][j] || m[i][j] != 0) continue;
					cnt++;
				}
			}
			result = Math.min(result, cnt);
			return;
		}
		
		int temp[] = cctv.get(curr);
		int y = temp[0];
		int x = temp[1];
		int data = m[y][x];
			
		int dir = getCnt(data);
		boolean[][] visitTemp = new boolean[N][M];
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < M; ++j) {
				visitTemp[i][j] = visited[i][j];
			}
		}
		
		for(int k = 0; k < dir; ++k) {
			go(y,x,k);
			dfs(curr+1);
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < M; ++j) {
					visited[i][j] = visitTemp[i][j];
				}
			}
		}
	}
	

	private static void go(int y, int x, int dir) {
		int[] d = new int[] {y,x};
		int data = m[y][x];
		int ny,nx;
		
		switch(data) {
		case 1: 
			d = getDir(data,dir,-1);
			ny = y + d[0];
			nx = x + d[1];
			while(ny >= 0 && ny < N && nx >= 0 && nx < M) {
				if(m[ny][nx] == 6) break;
				visited[ny][nx] = true;
				ny += d[0];
				nx += d[1];
			}
			break;
		case 2:
			for(int i = 0; i < d2[dir].length; ++i) {
				d = getDir(data,dir,i);
				ny = y + d[0];
				nx = x + d[1];
				while(ny >= 0 && ny < N && nx >= 0 && nx < M) {
					if(m[ny][nx] == 6) break;
					visited[ny][nx] = true;
					ny += d[0];
					nx += d[1];
				}
			}
			break;
		case 3:
			for(int i = 0; i < d3[dir].length; ++i) {
				d = getDir(data,dir,i);
				ny = y + d[0];
				nx = x + d[1];
				while(ny >= 0 && ny < N && nx >= 0 && nx < M) {
					if(m[ny][nx] == 6) break;
					visited[ny][nx] = true;
					ny += d[0];
					nx += d[1];
				}
			}
			break;
		case 4:
			for(int i = 0; i < d4[dir].length; ++i) {
				d = getDir(data,dir,i);
				ny = y + d[0];
				nx = x + d[1];
				while(ny >= 0 && ny < N && nx >= 0 && nx < M) {
					if(m[ny][nx] == 6) break;
					visited[ny][nx] = true;
					ny += d[0];
					nx += d[1];
				}
			}
			break;
		case 5:
			for(int i = 0; i < 4; ++i) {
				ny = y + dy5[i];
				nx = x + dx5[i];
				while(ny >= 0 && ny < N && nx >= 0 && nx < M) {
					if(m[ny][nx] == 6) break;
					visited[ny][nx] = true;
					ny += dy5[i];
					nx += dx5[i];
				}
			}
			break;
		}
	}

	private static int[] getDir(int data, int dir, int k) {
		
		switch(data) {
		case 1: 
			return getCh2List(d1[dir]);
		case 2:
			return getCh2List(d2[dir][k]);
		case 3:
			return getCh2List(d3[dir][k]);
		case 4:
			return getCh2List(d4[dir][k]);
		}
		
		return null;
	}

	private static int[] getCh2List(char c) {
		switch(c) {
		case 'u': 
			return u;
		case 'd':
			return d;
		case 'l':
			return l;
		case 'r':
			return r;
		}
		return null;
	}

	private static int getCnt(int data) {
		switch(data) {
		case 1: 
			return 4;
		case 2:
			return 2;
		case 3:
			return 4;
		case 4:
			return 4;
		case 5:
			return 1;
		}
		return 0;
	}
}
