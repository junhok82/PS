package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_6987 {
	private static String str;
	private static int[] group;
	private static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		group = new int[18];
		for (int i = 0; i < 4; ++i) {
			str = br.readLine();
			for (int j = 0; j < 18; ++j) {
					group[j] = str.charAt(j * 2) - '0';
			}
			
			result = 0;
			dfs(0,1);
			sb.append(result + " ");
		}
		System.out.print(sb);
	}

	private static void dfs(int team1, int team2) {
		if (team1 > 4) {
			for (int j = 0; j < 18; ++j) if (group[j] != 0)return;
			result = 1;
			return;
		}
		if (team2 > 5)  {
			dfs(team1+1,team1+2);
			return;
		}
		
		for(int i = 0, j = 2; i < 3; ++i, --j) {
			if(group[team1*3 + i] > 0 && group[team2*3 + j] > 0) {
				group[team1*3 + i]--;
				group[team2*3 + j]--;
				dfs(team1,team2 + 1);
				if (result == 1) return;
				group[team1*3 + i]++;
				group[team2*3 + j]++;
			}
		}	
	}
}
