package SWEA;

import java.io.*;
import java.util.*;

public class SWEA_5644 {

	private static int T;
	private static int M;
	private static int A;
	private static int[] dirA;
	private static int[] dirB;
	
	private static int dy[] = {0,-1,0,1,0};
	private static int dx[] = {0,0,1,0,-1};
	
	private static int [][][] battery;
	private static int[] ddx = {0,1,0,-1};
	private static int[] ddy = {1,0,-1,0};
	private static int[] storage;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());	// 이동시간
			A = Integer.parseInt(st.nextToken());	// 베터리 개수
			
			dirA = new int[M];
			dirB = new int[M];
			storage = new int[A];
			battery = new int[A][10][10];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; ++i) dirA[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; ++i) dirB[i] = Integer.parseInt(st.nextToken()); 
			
			
			for(int i = 0; i < A; ++i) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());	// 충전 범위
				int p = Integer.parseInt(st.nextToken());	// 처리량
					
				storage[i] = p;
				battery[i][y][x] = p;
				charge(i,y,x,c,p);	
			}
			
			sb.append("#" + t + " ").append(Solve()).append("\n");
		}
		System.out.println(sb);
	}

	private static int Solve() {
		int ay = 0;
		int ax = 0;
		int by = 9;
		int bx = 9;
		int i = 0;
		int ret = 0;
		
		do {			
			ret += batteryCheck(ay,ax,by,bx);
			
			if(i < M) {
				ay += dy[dirA[i]];
				ax += dx[dirA[i]];
				by += dy[dirB[i]];
				bx += dx[dirB[i]];
			}
			
			++i;
			
		}while(i <= M);
		
		
		return ret;
	}


	private static int batteryCheck(int ay, int ax, int by, int bx) {
		List<int[]> tempList = new ArrayList<>();	//  (0,1,2) : (both,A,B) , power
		
		for(int i = 0; i < A; ++i) {
			if(battery[i][ay][ax] > 0 && battery[i][by][bx] > 0) {
				tempList.add(new int [] { 0, storage[i] } );	// both
				continue;
			}
			if(battery[i][ay][ax] > 0) {
				tempList.add(new int [] { 1, storage[i]} );		// A
				continue;
			}
			if(battery[i][by][bx] > 0) {
				tempList.add(new int [] { 2, storage[i]} );		// B
				continue;
			}
		}
		
		int ret = 0;
		int sz = tempList.size();
		if(sz > 1) {
			
			Collections.sort(tempList, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[1] > o2[1]) {
						return 1;
					} 
					else if(o1[1] == o2[1]) {
						if(o1[0] < o2[0]) return -1;
						return 1;
					}
					return -1;
				}
			});
			
			
			boolean flagA = false;
			boolean flagB = false;
			int cnt = 0;
			
			for(int i = sz-1; i >= 0; --i) {
				int[] temp = tempList.get(i);
				int data = temp[1];
				int what = temp[0];
				
				if(what == 0) {
					ret += data;
					cnt++;
				}
				else if (what == 1) {
					if(flagA) continue;
					ret += data;
					flagA = true;
					cnt++;
				}
				else if (what == 2) {
					if(flagB) continue;
					ret += data;
					flagB = true;
					cnt++;
				}
				
				if(cnt == 2) break;
			}

		} else if(sz == 1) {
			ret += tempList.get(0)[1];
		}
		
		return ret;
	}

	private static void charge(int idx, int r, int c, int range, int power) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		
		int cnt = 1;
		while(q.size() != 0) {
			int sz = q.size();
			for(int i = 0; i < sz; ++i) {
				int temp[] = q.poll();
				int y = temp[0];
				int x = temp[1];

				for(int k = 0; k < 4; ++k) {
					int ny = y + ddy[k];
					int nx = x + ddx[k];
					
					if(ny < 0 || ny >= 10 || nx < 0 || nx >= 10 || battery[idx][ny][nx] != 0) continue;
					battery[idx][ny][nx] = power;
					q.offer(new int[] {ny,nx});
				}
			}
			++cnt;
			if(cnt > range) return; 
		}
	}
}
