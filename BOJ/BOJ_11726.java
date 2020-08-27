package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_11726 {
	private static int N;
	private static int[] dp;
	private static int mod = 10007;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[1001];
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3; i <= N; ++i)
			dp[i] = (dp[i-1] % mod + dp[i-2] % mod) % mod;
		System.out.println(dp[N]);
	}
}
