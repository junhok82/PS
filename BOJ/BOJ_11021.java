package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11021 {
	private static int A;
	private static int cnt = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = Integer.parseInt(br.readLine());
		int left = 0;
		int right = 0;
		int next = A;
		int temp = 0;
		
		do {
			if(next < 10) {
				left = 0;
				right = next;
			}
			else {
				left = next / 10;
				right = next % 10;			
			}
			
			temp = left+right; 
			next = (right * 10) + (temp % 10);
			cnt++;
		}while(next != A);
		System.out.println(cnt);
	}
}
