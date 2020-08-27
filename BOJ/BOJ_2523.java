package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2523 {
	private static int A;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = Integer.parseInt(br.readLine());
		for(int i = 0; i < A+A; ++i) {
			if(i%2 == 0) {
				for(int j = 0; j < A; j += 2) {
					System.out.print("* ");
				}
			} 
			else {
				for(int j = 0; j < A-1; j += 2) {
					System.out.print(" *");
				}
			}
			System.out.print("\n");
		}
	}
}
