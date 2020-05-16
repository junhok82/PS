package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_17140 {
	private static int R;
	private static int C;
	private static int K;
	private static int[][] m;
	private static int[] temp;
	private static Set<Integer> idx;
	private static int[] maxIdx;
	private static int maxx;
	private static int result;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int rowCnt = 3;
		int colCnt = 3;
		result = -1;
		m = new int[101][101];
		
		for(int i = 1; i <= 3; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= 3; ++j) {
				m[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int n = 0; n <= 100; ++n) {
			if(m[R][C] == K) {
				result = n;
				break;
			}
			maxx = 0;
			maxIdx = new int[101];
			
			if(rowCnt >= colCnt) {
				for(int i = 1; i <= rowCnt && i <= 100; ++i) {
					
					temp = new int[101];
					idx = new HashSet<>();
					
					for(int j = 1; j <= colCnt && j <= 100; ++j) {
						if(m[i][j] == 0) continue;
						temp[m[i][j]]++;
						idx.add(m[i][j]);
					}
					
					Iterator<Integer> it = idx.iterator();
					List<int[]> list = new ArrayList<>();
					while(it.hasNext()) {
						int next = it.next();
						list.add(new int[] {next, temp[next]});
					}
					
					Collections.sort(list, new Comparator<int[]>() {

						@Override
						public int compare(int[] o1, int[] o2) {
							if(o1[1] < o2[1]) return -1;
							else if(o1[1] == o2[1]) {
								if(o1[0] <= o2[0]) return -1;
								return 1;
							}
							return 1;
						}
					});
					
					for(int j = 0, k = 1; j < list.size() && k <= 99; ++j, k += 2) {
						int[] curr = list.get(j);
						m[i][k] = curr[0];
						m[i][k + 1] = curr[1];
					}
					
					maxIdx[i] = list.size() * 2;
					maxx = Math.max(maxx, maxIdx[i]);
				}
				
				for(int i = 1; i <= rowCnt && i <= 100; ++i) {
					for(int j = maxIdx[i] + 1; j <= maxx && j <= 100; ++j) {
						m[i][j] = 0;
					}
				}
				colCnt = maxx > 100 ? 100 : maxx;
			}
			else {
				for(int j = 1; j <= colCnt && j <= 100; ++j) {
					
					temp = new int[101];
					idx = new HashSet<>();
					
					for(int i = 1; i <= rowCnt && i <= 100; ++i) {
						if(m[i][j] == 0) continue;
						temp[m[i][j]]++;
						idx.add(m[i][j]);
					}
					
					Iterator<Integer> it = idx.iterator();
					List<int[]> list = new ArrayList<>();
					while(it.hasNext()) {
						int next = it.next();
						list.add(new int[] {next, temp[next]});
					}
					
					Collections.sort(list, new Comparator<int[]>() {

						@Override
						public int compare(int[] o1, int[] o2) {
							if(o1[1] < o2[1]) return -1;
							else if(o1[1] == o2[1]) {
								if(o1[0] <= o2[0]) return -1;
								return 1;
							}
							return 1;
						}
					});
					
					for(int s = 0, k = 1; s < list.size() && k <= 99; ++s, k += 2) {
						int[] curr = list.get(s);
						m[k][j] = curr[0];
						m[k+1][j] = curr[1];
					}
					
					maxIdx[j] = list.size() * 2;
					maxx = Math.max(maxx, maxIdx[j]);
				}
				
				for(int j = 1; j <= colCnt && j <= 100; ++j) {
					for(int i = maxIdx[j] + 1; i <= maxx && i <= 100; ++i) {
						m[i][j] = 0;
					}
				}
				rowCnt = maxx > 100 ? 100 : maxx;
			}
		}
		
		System.out.println(result);
	}
}
