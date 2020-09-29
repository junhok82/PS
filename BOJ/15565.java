package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15565 {
	private static int N;
	private static int K;
	private static int[] arr;
	private static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N + 2];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int sPointer = 0;
		int ePointer = 0;
		int cnt = arr[0] == 1 ? 1 : 0;

		while(ePointer <= N) {
			if (cnt >= K) {
				if(arr[sPointer++] == 1) cnt--;
				answer = Math.min(ePointer - sPointer + 2, answer);
			}
			else if(arr[++ePointer] == 1) cnt++;
		}
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
}
