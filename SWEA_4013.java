package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_4013 {

	private static int T;
	private static int K;
	private static String str;
	private static int[][] m;
	private static StringTokenizer st;
	private static int a;
	private static int b;
	private static List<int[]> order;
	private static int result;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; ++t) {
			K = Integer.parseInt(br.readLine());
			
			m = new int[4][9];
			order = new ArrayList<>();
			
			for(int i = 0; i < 4; ++i) {
				str = br.readLine();
				for(int j = 0; j < 8; ++j) {
					m[i][j] = str.charAt(j+j) - '0';
				}
			}
			
			for(int i = 0; i < K; ++i) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				order.add(new int[] {a,b});	 
			}
			
			solution();
			
			result = 0;
			for(int i = 0; i < 4; ++i) {
				if(m[i][0] == 1) result += (1 << i);
			}
			sb.append("#" + t + " ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	private static void solution() {
		
		for(int i = 0; i < K; ++i) {
			int[] temp = order.get(i);
			int num = temp[0] - 1;
			int dir = temp[1];
			
			dfs(num,dir,1 << num);
		}
		
	}

	private static void dfs(int num, int dir, int check) {	
		switch(num) {
		case 0 :
			if((check & (1 << 1)) == 0 && m[0][2] != m[1][6]) dfs(1,-dir, check | (1 << 1));
			go(0,dir);
			break;
		case 1 :
			if((check & (1 << 2)) == 0 && m[1][2] != m[2][6]) dfs(2,-dir, check | (1 << 2));
			if((check & (1 << 0)) == 0 && m[0][2] != m[1][6]) dfs(0,-dir, check | (1 << 0));
			go(1,dir);
			break;
		case 2 :
			if((check & (1 << 3)) == 0 && m[2][2] != m[3][6]) dfs(3,-dir, check | (1 << 3));
			if((check & (1 << 1)) == 0 && m[1][2] != m[2][6]) dfs(1,-dir, check | (1 << 1));
			go(2,dir);
			break;
		case 3 :
			if((check & (1 << 2)) == 0 && m[2][2] != m[3][6]) dfs(2,-dir, check | (1 << 2));
			go(3,dir);
			break;
		}
	}

	private static void go(int num, int dir) {
		switch(dir) {
		case -1 :
			m[num][8] = m[num][0];
			for(int i = 0; i < 8; ++i) {
				m[num][i] = m[num][i+1];
			}
			break;
		case 1 :
			for(int i = 8; i > 0; --i) {
				m[num][i] = m[num][i-1]; 
			}
			m[num][0] = m[num][8];
			break;
		}
	}

}
