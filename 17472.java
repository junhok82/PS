package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int from;
	int to;
	int diff;
	
	public Edge(int from, int to, int diff) {
		super();
		this.from = from;
		this.to = to;
		this.diff = diff;
	}

	@Override
	public int compareTo(Edge o) {
		if(this.diff < o.diff) return -1;
		else if(this.diff == o.diff) return 0;
		else return 1;
	}
}

public class BOJ_17472 {

	private static int N;
	private static int M;
	private static String str;
	private static int[][] m;
	private static int[] temp;
	private static int[] dy = {0,1,0,-1};
	private static int[] dx = {1,0,-1,0};
	private static int compo;
	private static List<int[]>[] list;
	private static List<Edge> edge;
	private static int[] tempList;
	private static int[] uf;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		m = new int[N][M];
		uf = new int[7];
		list = new List[7];
		edge = new ArrayList<>();
		visited = new boolean[N][M];
		
		for(int i = 0; i < 7; ++i) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; ++i) {
			str = br.readLine();
			for(int j = 0; j < M; ++j) {
				m[i][j] = str.charAt(j*2) - '0';
			}
		}
		
		/* 각 컴포넌트 별 색깔 지정 */
		compo = 1;
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < M; ++j) {
				if(!visited[i][j] && m[i][j] != 0) {
					setColorByBfs(i,j);
				}
			}
		}
				
		/* 각 컴포넌트 간 거리 구하기 */
		compo = 1;
		getDistance();		
		
		
		/* union-find setting */
		Collections.sort(edge);
		Arrays.fill(uf, -1);
		compo -= 2;
		
		int cnt = 0;
		int result = 0;
		for(int i = 0; ;++i) {			
			if(edge.size() == i) {
				System.out.println(-1);
				return;
			}
			Edge e = edge.get(i);
			if(merge(e.from,e.to)) {
				result += e.diff;
				if(++cnt == compo) break;
			}
		}
		System.out.println(result);
	}

	private static boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) return false;
		uf[b] = a;
		return true;
	}

	private static int find(int a) {
		if(uf[a] < 0) return a;
		return uf[a] = find(uf[a]);
	}

	private static void getDistance() {
		for(int n = 1; n < 7; ++n) {
			if(list[n].size() > 0) {
				for(int k = 0; k < list[n].size(); ++k) {
					tempList = list[n].get(k);
					int by = tempList[0];
					int bx = tempList[1];
					
					int[] right = goRight(by,bx);
					int[] left = goLeft(by,bx);
					int[] up = goUp(by,bx);
					int[] down = goDown(by,bx);
			
					if(up[0] > 0 && up[1] >= 2) {
						edge.add(new Edge(compo,up[0],up[1]));
					}
					if(down[0] > 0 && down[1] >= 2) {
						edge.add(new Edge(compo,down[0],down[1]));
					}
					if(left[0] > 0 && left[1] >= 2) {
						edge.add(new Edge(compo,left[0],left[1]));
					}
					if(right[0] > 0 && right[1] >= 2) {
						edge.add(new Edge(compo,right[0],right[1]));
					}
				}
				compo++;
			}
		}
	}

	private static int[] goDown(int by, int bx) {
		int d = 0;
		int ny = by + 1;
		int nx = bx;
		while(ny >= 0 && ny < N && nx >= 0 && nx < M) {
			if(m[ny][nx] == compo) return new int[] {0,0};
			else if(m[ny][nx] != 0) return new int[] {m[ny][nx],d};
			ny++;
			d++;
		}
		return new int[] {0,0};
	}

	private static int[] goUp(int by, int bx) {
		int d = 0;
		int ny = by - 1;
		int nx = bx;
		while(ny >= 0 && ny < N && nx >= 0 && nx < M) {
			if(m[ny][nx] == compo) return new int[] {0,0};
			else if(m[ny][nx] != 0) return new int[] {m[ny][nx],d};
			ny--;
			d++;
		}
		return new int[] {0,0};
	}

	private static int[] goLeft(int by, int bx) {
		int d = 0;
		int ny = by;
		int nx = bx - 1;
		while(ny >= 0 && ny < N && nx >= 0 && nx < M) {
			if(m[ny][nx] == compo) return new int[] {0,0};
			else if(m[ny][nx] != 0) return new int[] {m[ny][nx],d};
			nx--;
			d++;
		}
		return new int[] {0,0};
	}

	private static int[] goRight(int by, int bx) {
		int d = 0;
		int ny = by;
		int nx = bx + 1;
		while(ny >= 0 && ny < N && nx >= 0 && nx < M) {
			if(m[ny][nx] == compo) return new int[] {0,0};
			else if(m[ny][nx] != 0) return new int[] {m[ny][nx],d};
			nx++;
			d++;
		}
		return new int[] {0,0};		
	}

	private static void setColorByBfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r,c});
		m[r][c] = compo;
		visited[r][c] = true;
		
		while(q.size() != 0) {
			temp = q.poll();
			int y = temp[0];
			int x = temp[1];		
			
			for(int i = 0; i < 4; ++i) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx]) continue;
				if(m[ny][nx] == 0) {
					list[compo].add(new int[] {y,x});
					continue;
				}
				visited[ny][nx] = true;
				m[ny][nx] = compo;
				q.offer(new int[] {ny,nx});
			}
		}
		compo++;
	}
}
