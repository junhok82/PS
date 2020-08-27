package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15686 {
	private static int N;
	private static int M;
	private static int[][] m;
	private static List<int[]> house;
	private static List<int[]> store;
	private static int result;
	private static int[] check;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		m = new int[N][N];
		house = new ArrayList<>();
		store = new ArrayList<>();
		check = new int[M];

		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; ++j) {
				m[i][j] = Integer.parseInt(st.nextToken());
				if(m[i][j] == 1) house.add(new int[] {i,j});
				else if(m[i][j] == 2) store.add(new int[] {i,j});
			}
		}
		
		result = Integer.MAX_VALUE;		
		Comb(0,0);	
		System.out.println(result);
	}
	
	private static void Comb(int idx, int curr) {
		if(idx == M) {
			int temp = 0;
			for(int[] h : house) {
				int minn = Integer.MAX_VALUE;
				for(int i = 0; i < M; ++i) {
					int[] st = store.get(check[i]);
					minn = Math.min(minn, Math.abs(h[0]-st[0]) + Math.abs(h[1] - st[1]));
				}
				temp += minn;
				if(temp >= result) return;
			}
			if(temp < result) result = temp;
			return;
		}
		for(int i = curr; i < store.size(); ++i) {
			check[idx] = i;
			Comb(idx+1,i+1);
		}
	}
}
