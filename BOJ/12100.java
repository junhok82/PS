package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_12100 {
	private static int N;
	private static int[][] m;
	private static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		m = new int[N][N];

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				m[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = Integer.MIN_VALUE;
		Comb(0);
		System.out.println(result);
	}

	private static void Comb(int idx) {
		int[][] temp = new int[N][N];
		if(idx == 5) {
			return;
		}
		
		for(int i = 0; i < N; ++i) {	
			temp[i] = Arrays.copyOf(m[i], N);
		}
		
		for(int i = 0; i < 4; ++i) {
			result = Math.max(go(i), result);
			Comb(idx+1);
			for(int j = 0; j < N; ++j) {	
				m[j] = Arrays.copyOf(temp[j], N);
			}
		}
	}

	private static int go(int ord) {
		switch (ord) {
		case 0 :
			return Up();
		case 1 :
			return Down();
		case 2 :
			return Right();
		case 3 :
			return Left();
		}
		return 0;
	}

	private static int Left() {
		int maxx = Integer.MIN_VALUE;
		for (int y = 0; y < N; ++y) {
			int[] s = { -1, -1 }; // value, index
			int[] e = { -1, -1 };
			for (int x = 0; x < N; ++x) {
				if (m[y][x] != 0) {
					maxx = Math.max(maxx, m[y][x]);
					if (s[0] == -1) {
						s[0] = m[y][x];
						s[1] = x;
						if (x > 0 && m[y][x - 1] == 0) {
							int k = x - 1;
							while (k >= 0 && m[y][k] == 0)
								k--;
							m[y][k + 1] = s[0];
							m[y][x] = 0;
							s[1] = k + 1;
						}
					} else if (e[0] == -1) {
						e[0] = m[y][x];
						e[1] = x;

						// 두놈을 찾았을 때, 두 놈이 같다면?
						if (s[0] == e[0]) {
							m[y][s[1]] = s[0] + s[0];
							maxx = Math.max(s[0]+s[0], maxx);
							m[y][e[1]] = 0;
							s[0] = -1;
							e[0] = -1;
						} else if (Math.abs(s[1] - e[1]) == 1) {
							s[0] = e[0];
							e[0] = -1;
							s[1] = e[1];
						} else {
							m[y][s[1] + 1] = e[0];
							m[y][e[1]] = 0;
							s[0] = e[0];
							s[1] = s[1] + 1;
							e[0] = -1;
						}
					}
				}
			}
		}
		return maxx;
	}

	private static int Right() {
		int maxx = Integer.MIN_VALUE;
		for (int y = 0; y < N; ++y) {
			int[] s = { -1, -1 }; // value, index
			int[] e = { -1, -1 };
			for (int x = N - 1; x >= 0; --x) {
				if (m[y][x] != 0) {
					maxx = Math.max(maxx, m[y][x]);
					if (s[0] == -1) {
						s[0] = m[y][x];
						s[1] = x;
						if (x < N - 1 && m[y][x + 1] == 0) {
							int k = x + 1;
							while (k < N && m[y][k] == 0)
								k++;
							m[y][k - 1] = s[0];
							m[y][x] = 0;
							s[1] = k - 1;
						}
					} else if (e[0] == -1) {
						e[0] = m[y][x];
						e[1] = x;

						// 두놈을 찾았을 때, 두 놈이 같다면?
						if (s[0] == e[0]) {
							m[y][s[1]] = s[0] + s[0];
							maxx = Math.max(s[0]+s[0], maxx);
							m[y][e[1]] = 0;
							s[0] = -1;
							e[0] = -1;
						} else if (Math.abs(s[1] - e[1]) == 1) {
							s[0] = e[0];
							e[0] = -1;
							s[1] = e[1];
						} else {
							m[y][s[1] - 1] = e[0];
							m[y][e[1]] = 0;
							s[0] = e[0];
							s[1] = s[1] - 1;
							e[0] = -1;
						}
					}
				}
			}
		}
		return maxx;
	}

	private static int Down() {
		int maxx = Integer.MIN_VALUE;
		for (int x = 0; x < N; ++x) {
			int[] s = { -1, -1 }; // value, index
			int[] e = { -1, -1 };
			for (int y = N - 1; y >= 0; --y) {
				if (m[y][x] != 0) {
					maxx = Math.max(maxx, m[y][x]);
					if (s[0] == -1) {
						s[0] = m[y][x];
						s[1] = y;
						if (y < N - 1 && m[y + 1][x] == 0) {
							int k = y + 1;
							while (k < N && m[k][x] == 0)
								k++;
							m[k - 1][x] = s[0];
							m[y][x] = 0;
							s[1] = k - 1;
						}
					} else if (e[0] == -1) {
						e[0] = m[y][x];
						e[1] = y;

						// 두놈을 찾았을 때, 두 놈이 같다면?
						if (s[0] == e[0]) {
							m[s[1]][x] = s[0] + s[0];
							maxx = Math.max(s[0]+s[0], maxx);
							m[e[1]][x] = 0;
							s[0] = -1;
							e[0] = -1;
						} else if (Math.abs(s[1] - e[1]) == 1) {
							s[0] = e[0];
							e[0] = -1;
							s[1] = e[1];
						} else {
							m[s[1] - 1][x] = e[0];
							m[e[1]][x] = 0;
							s[0] = e[0];
							s[1] = s[1] - 1;
							e[0] = -1;
						}
					}
				}
			}
		}
		return maxx;
	}

	private static int Up() {
		int maxx = Integer.MIN_VALUE;
		for (int x = 0; x < N; ++x) {
			int[] s = { -1, -1 }; // value, index
			int[] e = { -1, -1 };
			for (int y = 0; y < N; ++y) {
				if (m[y][x] != 0) {
					maxx = Math.max(maxx, m[y][x]);
					if (s[0] == -1) {
						s[0] = m[y][x];
						s[1] = y;
						if (y > 0 && m[y - 1][x] == 0) {
							int k = y - 1;
							while (k >= 0 && m[k][x] == 0)
								k--;
							m[k + 1][x] = s[0];
							m[y][x] = 0;
							s[1] = k + 1;
						}
					} else if (e[0] == -1) {
						e[0] = m[y][x];
						e[1] = y;

						// 두놈을 찾았을 때, 두 놈이 같다면?
						if (s[0] == e[0]) {
							m[s[1]][x] = s[0] + s[0];
							maxx = Math.max(s[0]+s[0], maxx);
							m[e[1]][x] = 0;
							s[0] = -1;
							e[0] = -1;
						} else if (Math.abs(s[1] - e[1]) == 1) {
							s[0] = e[0];
							e[0] = -1;
							s[1] = e[1];
						} else {
							m[s[1] + 1][x] = e[0];
							m[e[1]][x] = 0;
							s[0] = e[0];
							s[1] = s[1] + 1;
							e[0] = -1;
						}
					}
				}
			}
		}
		return maxx;
	}
}
