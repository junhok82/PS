package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2588 {

	private static String A;
	private static String B;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		A = br.readLine();
		B = br.readLine();
		int total = 0;
		for(int i = B.length() - 1, d = 1; i >= 0; --i, d *= 10) {
			int a = B.charAt(i) - '0';
			int temp = 0;
			for(int j = A.length() - 1, digit = 1; j >= 0; --j, digit *= 10) {
				temp += ((a * (A.charAt(j) - '0')) * digit);
			}
			total += (temp * d);
			sb.append(temp).append("\n");
		}
		sb.append(total);
		System.out.println(sb);
	}
}
