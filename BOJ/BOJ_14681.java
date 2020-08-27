package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14681 {
	private static int A;
	private static int B;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = Integer.parseInt(br.readLine());
		B = Integer.parseInt(br.readLine());
		
		if(A < 0) {
			if(B < 0) System.out.println(3);
			else System.out.println(2);
		} else {
			if(B < 0) System.out.println(4);
			else System.out.println(1);
		}
	}
}
