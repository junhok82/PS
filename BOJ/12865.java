package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.*;

public class BOJ_12865 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int stuffCount = Integer.parseInt(st.nextToken());
		int canHasWeight = Integer.parseInt(st.nextToken());

		int[] stuffWeight = new int[stuffCount];
		int[] stuffValue = new int[stuffCount];
		for (int i = 0; i < stuffCount; i++) {
			st = new StringTokenizer(br.readLine());
			stuffWeight[i] = Integer.parseInt(st.nextToken());
			stuffValue[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		int[] dp = new int[canHasWeight + 1];

		for (int i = 0; i < stuffCount; i++) {
			for (int j = canHasWeight; j >= stuffWeight[i]; j--) {
				dp[j] = Math.max(dp[j - stuffWeight[i]] + stuffValue[i], dp[j]);
				answer = Math.max(answer,dp[j]);
			}
		}

		System.out.println(answer);
	}
}
