package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2294 {
	final static int INF = 987654321;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int coinNum = Integer.parseInt(st.nextToken());
		int coinSum = Integer.parseInt(st.nextToken());

		int[] coinValue = new int[coinNum];
		for (int i = 0; i < coinNum; i++) {
			coinValue[i] = Integer.parseInt(br.readLine());
		}

		int[] accumulatedCoinValue = new int[coinSum + 1];
		Arrays.fill(accumulatedCoinValue, INF);
		accumulatedCoinValue[0] = 0;

		for (int currValue : coinValue) {
			for (int i = currValue; i <= coinSum; i++) {
				accumulatedCoinValue[i] = Math.min(accumulatedCoinValue[i], accumulatedCoinValue[i - currValue] + 1);
			}
		}

		System.out.println(accumulatedCoinValue[coinSum] == INF ? -1 : accumulatedCoinValue[coinSum]);
	}
}
