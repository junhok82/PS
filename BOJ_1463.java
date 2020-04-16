package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1463 {
	private static int[] dp;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[1000001];
		Arrays.fill(dp, Integer.MAX_VALUE);
		System.out.print(f(N));
	}

	private static int f(int n) {
		if(n == 1)
			return 0;
		if(dp[n] != Integer.MAX_VALUE) return dp[n];
		if(n % 3 == 0) dp[n] = Math.min(dp[n],f(n/3) + 1);
		if(n % 2 == 0) dp[n] = Math.min(dp[n],f(n/2) + 1);
		dp[n] = Math.min(dp[n],f(n-1) + 1);
		return dp[n];
	}
}
