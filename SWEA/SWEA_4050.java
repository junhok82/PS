package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_4050 {
	private static int N;
	private static int[] m;
	private static int T;
	private static int R;
	private static int total;
	private static int start;
	private static int num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());
			m = new int[N];
			st = new StringTokenizer(br.readLine());
			total = 0;
			R = N/3;
			for (int i = 0; i < N; ++i) {
				m[i] = Integer.parseInt(st.nextToken());
				total += m[i];
			}
			
			Arrays.sort(m);
			start = N % 3;
			num = 0;
			for(int i = start; i < N; i += 3) {
				total -= m[i];
				if(++num == R) break;
			}
			
			sb.append("#").append(t).append(" ").append(total).append('\n');
		}
		System.out.println(sb);
	}
}


