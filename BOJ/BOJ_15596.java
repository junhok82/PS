package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15596 {
	private static int A;
	private static int T;
	private static int sum;
	private static int[] S;
	private static double avg;
	private static int cnt;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			sum = 0;
			S = new int[A];
			for(int i = 0; i < A; ++i) {
				S[i] = Integer.parseInt(st.nextToken());
				sum += S[i];
			}
			avg = sum/A;
			cnt = 0;
			for(int i = 0; i < A; ++i) {
				if(S[i] > avg) cnt++;
			}
 			
			sb.append(String.format("%.3f", ((double) (cnt * 100) / A))).append("%\n");
		}
		System.out.print(sb);
	}
}
