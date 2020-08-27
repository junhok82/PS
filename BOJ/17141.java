package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17141 {
	private static int N;
	private static int M;
	private static String str;
	private static int[][] m;
	private static List<int[]> virus;
	private static List<int[]> select;
	private static int[] dy = {0,1,0,-1};
	private static int[] dx = {1,0,-1,0};
	private static int totalCnt;
	private static int result;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		m = new int[N][N];
		virus = new ArrayList<>();
		select = new ArrayList<>();
		totalCnt = 0;
		result = -1;
		for(int i = 0; i < N; ++i) {
			str = br.readLine();
			for(int j = 0; j < N; ++j) {
				m[i][j] = str.charAt(j*2) - '0';
				if(m[i][j] == 2) {
					virus.add(new int[] {i,j});
					totalCnt++;
				} else if(m[i][j] == 0) {
					totalCnt++;
				}
			}
		}
		
		Comb(0,0);
		System.out.println(result);
	}

	private static void Comb(int idx, int curr) {
		if(idx == M) {
			int temp = bfs();
			if(temp == -1) return;
			if(result == -1) result = temp;
			else result = Math.min(result, temp);
			return;
		}
		
		for(int i = curr; i < virus.size(); ++i) { 
			int[] temp = virus.get(i);
			select.add(new int[] {temp[0],temp[1]});
			Comb(idx+1,i+1);
			select.remove(idx);
		}
	}

	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int moveCnt = 0;
		for(int[] s :select) {
			q.offer(new int[] {s[0],s[1]});
			visited[s[0]][s[1]] = true;
			moveCnt++;
		}
		int cnt = 0;

		while(q.size() != 0) {
			int sz = q.size();
			for(int i = 0; i < sz; ++i) {
				int temp[] = q.poll();
				int y = temp[0];
				int x = temp[1];
				
				for(int k = 0; k < 4; ++k) {
					int ny = y + dy[k];
					int nx = x + dx[k];
					if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] || m[ny][nx] == 1) continue;
					visited[ny][nx] = true;
					moveCnt++;
					q.offer(new int[] {ny,nx});
				}
			}
			cnt++;
		}
		if(moveCnt == totalCnt) return cnt-1;
		return -1;
	}
}
