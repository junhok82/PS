import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int N;
	private static int[] m;
	private static int[] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		m = new int[N+1];
		for(int i = 1; i <= N; ++i)
			m[i] = Integer.parseInt(br.readLine());
		
		System.out.print(f(N));
	}

	private static int f(int n) {
		if(n <= 0) return 0;
		if(dp[n] != 0) return dp[n];
		dp[n] = Math.max(m[n] + m[n-1] + f(n-3), m[n] + f(n-2));
		return dp[n];
	}
}