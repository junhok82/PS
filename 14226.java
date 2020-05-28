import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	private static int S;
	private static Queue<int[]> Q = new LinkedList<>();
	private static int[] pop;
	private static int curr;
	private static int time;
	private static int container;
	private static int m;
	private static int[][] memo;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		memo = new int[2001][2001];
		for(int i = 0; i < memo.length; ++i)
			for(int j = 0; j < memo[i].length; ++j)
			memo[i][j] = Integer.MAX_VALUE;
		Q.offer(new int[] {2,2,1});	// 현재 개수, 시간, 클립보드
		BFS();
		System.out.println(result);
	}

	private static void BFS() {
		while(Q.size() != 0)
		{
			pop = Q.poll();
			curr = pop[0];
			time = pop[1];
			container = pop[2];
			m = memo[curr][container];

			if(time > result) continue;
			if(curr == S)
				result = result > time ? time : result;
				
			if(m > time && container > 0)
				memo[curr][container] = time;
			else
				continue;
				if(curr < S)	
					Q.offer(new int[] {curr+container,time+1,container});
				if(curr > container)
					Q.offer(new int[] {curr,time+1,curr});
				if(curr > 1)
				Q.offer(new int[] {curr-1,time+1,container});
		}
	}
}
