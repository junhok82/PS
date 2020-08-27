package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14890 {
	private static int N;
	private static String str;
	private static int[][] m;
	private static int ret = 0;
	private static int X;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		m = new int[N][N];
			
		for(int i = 0; i < N; ++i) {
			str = br.readLine();
			for(int j = 0; j < N; ++j) {
				m[i][j] = str.charAt(j+j) - '0';
			}
		}
		solution();
		System.out.print(ret);
	}

	private static void solution() {
		
		for(int i = 0; i < N; ++i) {
			int cr = m[i][0];
			int cc = m[0][i];
			int inR = 1;
			int inC = 1;
			int deR = 1;
			int deC = 1;
			boolean flagR = false;
			boolean flagC = false;
			boolean beginR = false;
			boolean beginC = false;
			
			for(int j = 1; j < N; ++j) {
				int nr = m[i][j];
				int nc = m[j][i];
				
				if(!flagR) {
					if(cr == nr) {
						if(beginR) deR++; 
						else inR++;
					}
					else if(cr < nr) {
						if(beginR) { if(deR < X + X || nr - cr >= 2) flagR = true; }  
						else { if(inR < X || nr - cr >= 2) flagR = true; } 
						beginR = false;
						inR = 1;
					}
					else {
						if(cr - nr >= 2) flagR = true;
						if(beginR && deR < X) flagR = true;
						beginR = true;
						deR = 1;
					}
				}
				if(!flagC) {	
					if(cc == nc) {
						if(beginC) deC++;
						else inC++;
					}
					else if(cc < nc){
						if(beginC) { if(deC < X + X || nc-cc >= 2) flagC = true; }  
						else { if(inC < X || nc-cc >= 2) flagC = true; }
						beginC = false;
						inC = 1;
					}
					else {
						if(cc - nc >= 2) flagC = true;
						if(beginC && deC < X) flagC = true;
						beginC = true;
						deC = 1;
					}
				}
				
				cr = nr;
				cc = nc;
				
				if(flagR && flagC) break;
			}
			if(beginR && deR < X) flagR = true;
			if(beginC && deC < X) flagC = true;
			if(!flagR) ret++;
			if(!flagC) ret++;
		}	
	}
}
