package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_12851 {

	private static int B;
	private static int S;
	private static int[] dp;
	private static boolean flag = false;
	private static int ret = 0;
	private static int time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		dp = new int[140000];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		bfs();
		System.out.println(time);
		System.out.println(ret);
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(S);
		time = 0;
		
		while(q.size() != 0) {
			int sz = q.size();
			for(int i = 0; i < sz; ++i) {
				int curr = q.poll();
				if(curr == B) {
					flag = true;
					ret++;
				}
				if(flag) continue;
				
				if(curr + 1 <= 100000 && dp[curr+1] >= time) {
					dp[curr+1] = time;
					q.offer(curr+1);
				}
				if(curr - 1 >= 0 && dp[curr-1] >= time) {
					dp[curr-1] = time;
					q.offer(curr-1);
				}
				if(curr * 2 < 140000 && dp[curr*2] >= time) {
					dp[curr*2] = time;
					q.offer(curr*2);
				}
			}
			if(!flag) time++;
		}
	}

}
