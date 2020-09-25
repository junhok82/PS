import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[] list;
	private static boolean[] v;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		N = Integer.parseInt(br.readLine());
		list = new int[N]; 
		v = new boolean[2000001];
				
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0,0);
		for(int i = 1; i < 2000001; ++i) {
			if(!v[i]) {
				System.out.print(i);
				return;
			}
		}
	}

	private static void dfs(int idx, int sum) {
		if(idx == list.length) {
			if(sum == 0) return;
			v[sum] = true;
			return;
		}
				
		dfs(idx + 1, sum + list[idx]);
		dfs(idx + 1, sum);
	}

}
