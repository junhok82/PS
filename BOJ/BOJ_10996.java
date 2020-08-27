package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10996 {
	private static int A;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = Integer.parseInt(br.readLine());
		for(int i = 1; i < A+A; ++i) {
			if(i <= A) for(int j = 0; j < i; ++j) System.out.print("*");
			else for(int j = 0; j < A+A-i; ++j) System.out.print("*");
			System.out.print("\n");
		}
	}
}
