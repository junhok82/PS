package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_3234 {
	private static int T;
	private static int N;
	private static int[] W;
	private static int result;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());
			W = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; ++i) {
				W[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(W);
			
			result = 0;
			
			do {
				dfs(0,0,0);
			}while(nextPermutaion());
			
			sb.append("#" + t + " ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	private static boolean nextPermutaion() {
		int i = N-1;
		int j = N-1;
		
		while(i > 0 && W[i-1] >= W[i]) --i;
		if(i <= 0) return false;
		
		while(W[i-1] >= W[j]) --j;
		W[i-1] ^= W[j];
		W[j] ^= W[i-1];
		W[i-1] ^= W[j];
		
		j = N-1;
		for(;i < j; ++i,--j) {
			W[i] ^= W[j];
			W[j] ^= W[i];
			W[i] ^= W[j];
		}
		return true;
	}

	private static void dfs(int L, int R, int idx) {
		if(R > L) return;
		if(idx == N) {
			result++;
			return;
		}
		
		dfs(L+W[idx],R,idx+1);
		dfs(L,R+W[idx],idx+1);
	}

}
