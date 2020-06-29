import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static boolean[][] map;
	private static PriorityQueue<int[]> Q;
	private static int[] pop;
	private static int y;
	private static int x;
	private static int[] dirY = {1,0,-1,0};
	private static int[] dirX = {0,1,0,-1};
	private static int ny;
	private static int nx;
	private static int col;
	private static int row;
	private static int v;
	private static int[][] dp;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		col = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		map = new boolean[row][col];
		dp = new int[row][col];
		for(int i = 0; i< row; ++i)
			for(int j = 0; j < col; ++j)
				dp[i][j] = Integer.MAX_VALUE;
		
		for(int i = 0; i < row; ++i)
		{
			String str = br.readLine();
			for(int j = 0; j < col; ++j)
				map[i][j] = str.charAt(j) == '1' ? true : false;
		}
		BFS();
		System.out.println(result);
	}

	private static void BFS() {
		Q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] < o2[2] ? -11 : 1;
			}
		});
		Q.offer(new int[] {0,0,0});
		while(Q.size() != 0)
		{
			pop = Q.poll();
			y = pop[0];
			x = pop[1];
			v = pop[2];
			
			if(y == row - 1 && x == col - 1)
			{
				result = v;
				return;
			}
			
			if(dp[y][x] > v)
				dp[y][x] = v;
			else
				continue;
			
			for(int i = 0; i < 4; ++i)
			{
				ny = y + dirY[i];
				nx = x + dirX[i];
				if(ny < 0 || nx < 0 || ny >= row || nx >= col) continue;
				
				if(map[ny][nx])
					Q.offer(new int[] {ny,nx,v+1});	
				else
					Q.offer(new int[] {ny,nx,v});		
			}
		}	
	}
}
