import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	private static int[][] map;
	private static int[][] dp;
	private static int size;
	private static int max = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = 1;
		while(true)
		{
			size = Integer.parseInt(br.readLine());
			if(size == 0)
				break;
			map = new int[size][size];
			dp = new int[size][size];
			for(int i = 0; i < size; i++)
				Arrays.fill(dp[i], max);
			
			for(int i = 0; i < size; i++)
			{
				String s = br.readLine();
				for(int j = 0, k = 0; j < size; j++, k += 2)
					map[i][j] = s.charAt(k) - '0';
			}
		
			dp[0][0] = map[0][0];
			BFS();
			
			System.out.println("Problem " + (t++) + ": " + dp[size-1][size-1]);
		}
	}

	static void BFS() {
		Queue<int[]> Q = new LinkedList<>();
		Q.offer(new int[] {0,0});
		
		while(!Q.isEmpty())
		{
			int y = Q.peek()[0];
			int x = Q.poll()[1];
			if(dp[size-1][size-1] <= dp[y][x])
				continue;
			
			if(y+1 < size)
				if(dp[y+1][x] > dp[y][x] + map[y+1][x])
				{
					dp[y+1][x] = dp[y][x] + map[y+1][x];
					Q.offer(new int[] {y+1,x});
				}
			if(x+1 < size)
				if(dp[y][x+1] > dp[y][x] + map[y][x+1])
				{
					dp[y][x+1] = dp[y][x] + map[y][x+1];
					Q.offer(new int[] {y,x+1});
				}
			if(y-1 >= 0)
				if(dp[y-1][x] > dp[y][x] + map[y-1][x])
				{
					dp[y-1][x] = dp[y][x] + map[y-1][x];
					Q.offer(new int[] {y-1,x});
				}
			if(x-1 >= 0)
				if(dp[y][x-1] > dp[y][x] + map[y][x-1])
				{
					dp[y][x-1] = dp[y][x] + map[y][x-1];
					Q.offer(new int[] {y,x-1});
				}
		}
	}
}
