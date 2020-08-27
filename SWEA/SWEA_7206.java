package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_7206 {

	private static int T;
	private static String N;
	private static int result;
	private static int[] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); 
		T = Integer.parseInt(br.readLine());
		dp = new int[100000];
		
		for(int t = 1; t <= T; ++t) {
			N = br.readLine();
			Arrays.fill(dp, -1);

			result = dfs(N);
			sb.append("#" + t + " ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	private static int dfs(String curr) {
		int idxCurr = Integer.parseInt(curr);
		if(curr.length() == 1) {
			return 0;
		}
		if(dp[idxCurr] != -1) return dp[idxCurr];
		
		int idx = (curr.length() - 1);
		for(int flag = 1; flag < (1 << idx); ++flag) {
			String next = curr.substring(0,1);
			int sum = 1;
			
			for(int i = 0; i < idx; ++i) {
				if( ((1 << i) & flag) > 0) {
					sum *= Integer.parseInt(next);
					next = curr.substring(i+1,i+2);
				}
				else {
					next += curr.charAt(i+1);
				}
			}
			
			next = Integer.toString(sum * Integer.parseInt(next));
			dp[idxCurr] = Math.max(dfs(next) + 1, dp[idxCurr]);
		}
		return dp[idxCurr];
	}
}
