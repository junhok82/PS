import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test {

	private static String origin;
	private static String pattern;
	private static StringBuilder sb;
	private static int cnt = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		origin = br.readLine();
		pattern = br.readLine();
		KMP(origin,pattern);
		System.out.print(cnt + "\n");
		System.out.print(sb);
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
	
	static void KMP(String og, String pat)
	{
		int[] pi = getPi(pat);
		
		for(int s = 0, e = 0; e < og.length(); ++e)
		{
			while(s > 0 && og.charAt(e) != pat.charAt(s)) s = pi[s-1];
			if(og.charAt(e) == pat.charAt(s))
			{
				if(s == pat.length() - 1)
				{
					cnt++;
					sb.append(e-s+1).append('\n');
					s = pi[s];
				}
				else s++;
			}
		}
	}
}

