package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6109 {
	private static int T;
	private static int N;
	private static String order;
	private static int[][] m;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			order = st.nextToken();
			m = new int[N][N];
			
			for(int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; ++j) {
					m[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			go(order);
			sb.append("#").append(t).append('\n');
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					sb.append(m[i][j]).append(" ");
				}
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}

	private static void go(String ord) {
		switch (ord) {
		case "up":
			Up();
			break;
		case "down":
			Down();
			break;
		case "right":
			Right();
			break;
		case "left":
			Left();
			break;
		}

	}
	
	private static void Left() {
		for (int y = 0; y < N; ++y) {
			int[] s = {-1,-1};	// value, index
			int[] e = {-1,-1};
			for (int x = 0; x < N; ++x) {
				if (m[y][x] != 0) {
					if (s[0] == -1) {
						s[0] = m[y][x];
						s[1] = x;
						if(x > 0 && m[y][x-1] == 0) {
							int k = x-1;
							while(k >= 0 && m[y][k] == 0)
								k--;
							m[y][k+1] = s[0];
							m[y][x] = 0;
							s[1] = k+1;
						}
					} else if (e[0] == -1) {
						e[0] = m[y][x];
						e[1] = x;
						
						// 두놈을 찾았을 때, 두 놈이 같다면? 
						if(s[0] == e[0]) {
							m[y][s[1]] = s[0] + s[0];
							m[y][e[1]] = 0;
							s[0] = -1;
							e[0] = -1;
						} else if(Math.abs(s[1] - e[1]) == 1) {
							s[0] = e[0];
							e[0] = -1;
							s[1] = e[1];
						} else {
							m[y][s[1]+1] = e[0];
							m[y][e[1]] = 0;
							s[0] = e[0];
							s[1] = s[1] + 1;
							e[0] = -1;
						}
					}
				}
			}
		}
	}
	
	private static void Right() {		
		for (int y = 0; y < N; ++y) {
			int[] s = {-1,-1};	// value, index
			int[] e = {-1,-1};
			for (int x = N-1; x >= 0; --x) {
				if (m[y][x] != 0) {
					if (s[0] == -1) {
						s[0] = m[y][x];
						s[1] = x;
						if(x < N-1 && m[y][x+1] == 0) {
							int k = x+1;
							while(k < N && m[y][k] == 0)
								k++;
							m[y][k-1] = s[0];
							m[y][x] = 0;
							s[1] = k-1;
						}
					} else if (e[0] == -1) {
						e[0] = m[y][x];
						e[1] = x;
						
						// 두놈을 찾았을 때, 두 놈이 같다면?
						if(s[0] == e[0]) {
							m[y][s[1]] = s[0] + s[0];
							m[y][e[1]] = 0;
							s[0] = -1;
							e[0] = -1;
						} else if(Math.abs(s[1] - e[1]) == 1) {
							s[0] = e[0];
							e[0] = -1;
							s[1] = e[1];
						} else {
							m[y][s[1]-1] = e[0];
							m[y][e[1]] = 0;
							s[0] = e[0];
							s[1] = s[1] - 1;
							e[0] = -1;
						}
					}
				}
			}
		}
	}

	private static void Down() {
		for (int x = 0; x < N; ++x) {
			int[] s = {-1,-1};	// value, index
			int[] e = {-1,-1};
			for (int y = N-1; y >= 0; --y) {
				if (m[y][x] != 0) {
					if (s[0] == -1) {
						s[0] = m[y][x];
						s[1] = y;
						if(y < N - 1 && m[y+1][x] == 0) {
							int k = y+1;
							while(k < N && m[k][x] == 0)
								k++;
							m[k-1][x] = s[0];
							m[y][x] = 0;
							s[1] = k-1;
						}
					} else if (e[0] == -1) {
						e[0] = m[y][x];
						e[1] = y;
						
						// 두놈을 찾았을 때, 두 놈이 같다면?
						if(s[0] == e[0]) {
							m[s[1]][x] = s[0] + s[0];
							m[e[1]][x] = 0;
							s[0] = -1;
							e[0] = -1;
						} else if(Math.abs(s[1] - e[1]) == 1) {
							s[0] = e[0];
							e[0] = -1;
							s[1] = e[1];
						} else {
							m[s[1]-1][x] = e[0];
							m[e[1]][x] = 0;
							s[0] = e[0];
							s[1] = s[1] - 1;
							e[0] = -1;
						}
					}
				}
			}
		}		
	}
	private static void Up() {
		for (int x = 0; x < N; ++x) {
			int[] s = {-1,-1};	// value, index
			int[] e = {-1,-1};
			for (int y = 0; y < N; ++y) {
				if (m[y][x] != 0) {
					if (s[0] == -1) {
						s[0] = m[y][x];
						s[1] = y;
						if(y > 0 && m[y-1][x] == 0) {
							int k = y-1;
							while(k >= 0 && m[k][x] == 0)
								k--;
							m[k+1][x] = s[0];
							m[y][x] = 0;
							s[1] = k+1;
						}
					} else if (e[0] == -1) {
						e[0] = m[y][x];
						e[1] = y;
						
						// 두놈을 찾았을 때, 두 놈이 같다면?
						if(s[0] == e[0]) {
							m[s[1]][x] = s[0] + s[0];
							m[e[1]][x] = 0;
							s[0] = -1;
							e[0] = -1;
						} else if(Math.abs(s[1] - e[1]) == 1) {
							s[0] = e[0];
							e[0] = -1;
							s[1] = e[1];
						} else {
							m[s[1]+1][x] = e[0];
							m[e[1]][x] = 0;
							s[0] = e[0];
							s[1] = s[1] + 1;
							e[0] = -1;
						}
					}
				}
			}
		}
	}
}
