package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5543 {
	private static int[] A;
	private static int[] B;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = new int[3];
		B = new int[2];
		A[0] = Integer.parseInt(br.readLine());
		A[1] = Integer.parseInt(br.readLine());
		A[2] = Integer.parseInt(br.readLine());
		B[0] = Integer.parseInt(br.readLine());
		B[1] = Integer.parseInt(br.readLine());
		
		Comb(0,0,0);
		System.out.println(result-50);
		
	}

	private static void Comb(int a, int b, int sum) {
		if(a+b == 2) {
			result  = Math.min(result, sum);
			return;
		}
		if(a < 1) for(int i = 0; i < 3; ++i) Comb(a+1,b, sum + A[i]);
		if(b < 1) for(int i = 0; i < 2; ++i) Comb(a,b+1, sum + B[i]);
	}
}
