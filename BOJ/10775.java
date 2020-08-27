package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class BOJ_10775 {
	private static int gates;
	private static int plane;
	private static int[] uf;
	private static int result;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gates = Integer.parseInt(br.readLine());
		plane = Integer.parseInt(br.readLine());
		uf = new int[gates + 1];
		result = 0;
		Arrays.fill(uf, -1);
		for(int i = 0; i < plane; ++i) {
			int gate = Integer.parseInt(br.readLine());
			gate = find(gate);
			if(gate == 0) break;
			result++;
			uf[gate] = gate-1;
		}
		System.out.println(result);
	}

	private static int find(int a) {
		if(uf[a] < 0) return a;
		return uf[a] = find(uf[a]);
	}
}
