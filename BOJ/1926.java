import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int row;
	private static int col;
	private static boolean[][] map;
	private static int[][] dir = { {1,0}, {0,1}, {-1,0}, {0,-1} };
	private static int temp;
	private static int result = 0;
	private static int component = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new boolean[row][col];
		
		for(int i = 0; i < row; i++)
		{
			String str = br.readLine();
			for(int j = 0, k = 0; j < col; j++, k+=2)
				map[i][j] = str.charAt(k) == '1' ? false : true; 
		}
		
		for(int i = 0; i < row; i++)
			for(int j = 0; j < col; j++)
				if(!map[i][j])
				{
					component++;
					temp = DFS(i,j) + 1;
					result = result > temp ? result : temp;
				}
		System.out.print(component + "\n" + result);
	}

	private static int DFS(int y, int x) {
		map[y][x] = true;
		int size = 0;
		
		for(int i = 0; i < 4; i++)
		{
			final int ny = y + dir[i][0];
			final int nx = x + dir[i][1];
			
			if(ny >= 0 && ny < row && nx >= 0 && nx < col && !map[ny][nx])
				size += DFS(ny,nx) + 1;
		}
		return size;
	}
}
