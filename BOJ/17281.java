import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	private static int N;
	private static String str;
	private static int[][] arr;
	private static int[] ans;
	private static int result;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][9];
		ans = new int[9];
		visited = new boolean[9];
		
		for(int i = 0; i < N; ++i)
		{
			str = br.readLine();
			for(int j = 0; j < 9; ++j)
				arr[i][j] = str.charAt(j*2) - '0';
		}
		result = 0;
		dfs(0);
		System.out.print(result);
	}

	private static void dfs(int idx)
	{
		if(idx == 9)
		{
			int temp = check();
			result = temp > result ? temp : result;
			return;
		}
		for(int k = 1; k < 9; ++k)
		{
			if(visited[k]) continue;
			ans[idx] = k;
			visited[k] = true;
			if(idx == 2)
				dfs(idx+2);
			else
				dfs(idx+1);
			visited[k] = false;
		}
		
	}

	private static int check()
	{
		int j = 0;
		int out = 0;
		int score = 0;
		
		for(int k = 0; k < N; ++k)
		{
			out = 0;
			boolean juru[] = new boolean[3];
			while(true)
			{
				int data = arr[k][ans[j]];
				if(data == 4)
				{
					score++;
					for(int i = 0; i < 3; ++i)
						if(juru[i])
						{
							score++;
							juru[i] = false;
						}
				}
				else if(data == 3)
				{
					for(int i = 0; i < 3; ++i)
						if(juru[i])
						{
							score++;
						 	juru[i] = false;
						}
					juru[2] = true;
				}
				else if(data == 2)
				{
					for(int i = 1; i < 3; ++i)
						if(juru[i])
						{
							score++;
							juru[i] = false;
						}
					if(juru[0])
					{
						juru[0] = false;
						juru[2] = true;
					}
					juru[1] = true;
				}
				else if(data == 1)
				{
					if(juru[2])
					{
						score++;
						juru[2] = false;
					}
					for(int i = 1; i >= 0; --i)
						if(juru[i])
						{
							juru[i+1] = true;
							juru[i] = false;
						}
					juru[0] = true;
				}
				else if(data == 0)
					out++;
				
				j++;
				if(j == 9)
					j = 0;
				if(out >= 3)
					break;
			}
				
		}
		return score;
	}
	
}
