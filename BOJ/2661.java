package BOJ;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2661 {
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		dfs("1",1);
	}

	private static void dfs(String curr, int idx) {
		if(idx == N) {
			System.out.println(curr);
			exit(0);
		}
		for (int i = 1; i <= 3; i++) {
			String next = curr + i;
			if(isDuplicated(next)) continue;
			dfs(next, idx + 1);
		}
	}

	private static boolean isDuplicated(String str) {
		for (int i = 1, s = str.length() - 1, e = str.length(); i <= str.length() / 2; ++i, --s) {
			if(str.substring(s - i, e - i).equals(str.substring(s,e))) return true;
		}
		return false;
	}
}
