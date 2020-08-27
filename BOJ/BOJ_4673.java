package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4673 {

	private static boolean[] v;

	public static void main(String[] args) throws IOException{
		int a;
		v = new boolean[10001];
		for(int i = 1; i <= 10000; ++i) {
			a = getNum(i);
			if(a == i || a > 10000) continue;
			v[a] = true;
		}
		for(int i = 1; i <= 10000; ++i) {
			if(!v[i]) System.out.println(i);
		}
	}

	private static int getNum(int n) {
		int ret = n;
		int temp = n;
		for(; temp / 10 > 0; temp /= 10) ret += (temp % 10);
		ret += (temp % 10);
		return ret;
	}
}
