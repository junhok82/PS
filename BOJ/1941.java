package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1941_DFS_BFS {

	private static String str;
	private static char[] m;
	private static int result;
	private static int[] d = {5,-5,1,-1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		m = new char[25];
		
		for(int i = 0; i < 5; ++i) {
			str = br.readLine();
			for(int j = 0; j < 5; ++j) {
				m[i*5 + j] = str.charAt(j);
			}
		}

		result = 0;
		dfs(0,0,0,0);
		System.out.println(result);
	}

	private static void dfs(int idx, int Y, int curr, int visit) {
		if(Y == 4) return;
		else if(idx == 7) {
			if(bfs(curr-1,visit) == 7) result++;
			return;
		}
		
		for(int i = curr; i < 25; ++i) {
			dfs(idx + 1, Y + (m[i] == 'Y' ? 1 : 0), i+1, visit | (1 << i));
		}
		
	}

	private static int bfs(int val, int visit) {
		int cnt = 0;
		int check = 0;
		Queue<Integer> q = new LinkedList<>();
		q.offer(val);
		check |= (1 << val);
		
		while(q.size() != 0) {
			int curr = q.poll();
			cnt++;
			for(int i = 0; i < 4; ++i) {
				if((curr % 5 == 4 && i == 2) || (curr % 5 == 0 && i == 3)) continue;
				int next = curr + d[i];
				if(next < 0 || next >= 25 || (check & (1 << next)) > 0 || (visit & (1 << next)) == 0) continue;
				check |= (1 << next);
				q.offer(next);
			}
		}
		return cnt;
	}
}
