import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static String origin;
	private static String pattern;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
		//StringBuilder sb = new StringBuilder();
		origin = br.readLine();
		pattern = br.readLine();
		System.out.print(KMP(origin,pattern) ? "1" : "0");
	}
	
	static int[] getPi(String str) {
		int[] pi = new int[str.length()];
		
		for(int s = 0, e = 1; e < str.length(); ++e)
		{
			while(s > 0 && str.charAt(s) != str.charAt(e)) s = pi[s-1];
			if(str.charAt(s) == str.charAt(e)) pi[e] = ++s;
		}
		return pi;
	}
	
	static boolean KMP(String og, String pat)
	{
		int[] pi = getPi(pat);
		for(int s = 0, e = 0; e < og.length(); ++e)
		{
			while(s > 0 && og.charAt(e) != pat.charAt(s)) s = pi[s-1];
			if(og.charAt(e) == pat.charAt(s))
			{
				if(s == pat.length() - 1) return true;
				else s++;
			}
		}
		return false;
	}
}
