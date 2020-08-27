package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1110 {
	private static int A;
	private static int B;
	private static int T;
	private static String str;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; ++t) {
			str = br.readLine();
			A = str.charAt(0) - '0';
			B = str.charAt(2) - '0';
			sb.append("Case #" + t + ": ").append(A+B).append("\n");
		}
		System.out.println(sb);
	}
}
