package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10039 {
	private static int[] A;
	private static int sum = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = new int[5];
		A[0] = Integer.parseInt(br.readLine());
		A[1] = Integer.parseInt(br.readLine());
		A[2] = Integer.parseInt(br.readLine());
		A[3] = Integer.parseInt(br.readLine());
		A[4] = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < 5; ++i) {
			if(A[i] < 40) sum   += 40;
			else sum += A[i];
		}

		System.out.println(sum/5);
	}
}
