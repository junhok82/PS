package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1062 {

	private static boolean[] alpha;
	private static int N;
	private static int K;
	private static String[] str;
	private static int result;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		alpha = new boolean[26];
		alpha['a' - 97] = true;
		alpha['n' - 97] = true;
		alpha['t' - 97] = true;
		alpha['i' - 97] = true;
		alpha['c' - 97] = true;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		str = new String[N];
		K -= 5;
		
		for(int i = 0; i < N; ++i) {
			str[i] = br.readLine();
			str[i] = str[i].substring(3, str[i].length()-4);
		}
		result = 0;
		if(K >= 0) comb(0,0);
		System.out.print(result);
	}

	private static void comb(int idx, int curr) {
		if(idx == K) {
			int temp = 0;
			for(int i = 0; i < str.length; ++i) {
				for(int j = 0; j < str[i].length(); ++j) {
					if(!alpha[str[i].charAt(j) - 97]) break;
					else if(j == str[i].length()-1) temp++;
				}
			}
			result = Math.max(temp, result);
			return;
		}
		for(int i = curr; i < 26; ++i) {
			if(alpha[i]) continue;
			alpha[i] = true;
			comb(idx+1, i+1);
			alpha[i] = false;
		}
	}

}
