package SWEA;

import java.io.*;
import java.util.*;

public class SWEA_2112 {
	private static int T;
	private static int D;
	private static int W;
	private static int K;
	private static String str;
	private static int[][] m;
	private static int[][] temp;
	private static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());


		for (int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			m = new int[D][W];
			temp = new int[D][W];
			
			for (int i = 0; i < D; ++i) {
				str = br.readLine();
				for (int j = 0; j < W; ++j) {
					m[i][j] = str.charAt(j+j) - '0'; // A:0 B:1
					temp[i][j] = m[i][j];
				}
			}

			result = -1;		
			for (int n = 0; n <= K && n < D - 1; ++n) {
				dfs(0, 0, n);
				if (result != -1)
					break;
			}
			if(result == -1) result = D;

			sb.append("#" + t + " ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	private static void dfs(int idx, int curr, int r) {
		if (idx == r) {
			if (check()) result = r;
			return;
		}

		for (int i = curr; i < D; ++i) {
			Arrays.fill(temp[i], 0);
			dfs(idx + 1,i+1,r);
			if (result != -1)
				return;
			Arrays.fill(temp[i], 1);
			dfs(idx + 1,i+1,r);
			if (result != -1)
				return;
			temp[i] = Arrays.copyOf(m[i], W);
		}
	}

	private static boolean check() {
		for (int i = 0; i < W; ++i) {
			boolean flag = false;
			int prev = temp[0][i];
			int cnt = 1;
			for (int j = 1; j < D; ++j) {
				int curr = temp[j][i];
				if (prev == curr) {
					cnt++;
				} else {
					cnt = 1;
				}
				prev = curr;
				if (cnt == K) {
					flag = true;
					break;
				}
			}
			if (flag)
				continue;
			else
				return false;
		}
		return true;
	}
}