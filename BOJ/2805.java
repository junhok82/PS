import java.io.*;
import java.util.*;

public class Main {
	
	private static int N;
	private static long M;
	private static long[] tree;
	private static long e;
	private static long s;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		tree = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			tree[i] = Long.parseLong(st.nextToken());
			e = Long.max(e, tree[i] + 1);
		}	
		
		s = 1; 
		
		while(s < e) {
			long sum = 0;
			long mid = (s + e) / 2;
			
			for(int i = 0; i < N; ++i) {
				if(tree[i] > mid) {
					sum += (tree[i] - mid);
				}
			}
			
			if(sum >= M) {
				s = mid + 1;
			}
			else {
				e = mid;
			}
		}
		System.out.println(s-1);
	}
}
