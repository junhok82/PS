import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static char[] data;
	private static boolean[] visited;
	private static int L;
	private static int C;
	private static List<String> result = new ArrayList<>();
	private static boolean m;
	private static int nc;
	private static boolean n;
	private static int cdx = 0;
;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		data = new char[C];
		visited = new boolean[C];
		
		String str = br.readLine();
		for(int t = 0, k = 0; k < str.length(); ++t, k += 2)
			data[t] = str.charAt(k);
		
		Comb(0,0);
		
		Collections.sort(result);
		
		for(String next : result)
			System.out.println(next);
			
	}
	private static void Comb(int idx, int before) {
		if(idx == L)
		{			
			nc = 0;
			m = false;
			n = false;
			for(int i = 0; i < C; ++i)
				if(visited[i])
				{
					if(data[i] == 'a' || data[i] == 'e' || data[i] == 'o' || data[i] == 'i' || data[i] == 'u')
						m = true;
					else
					{
						nc++;
						if(nc >= 2)
							n = true;
					}
				}
			if(!m || !n)
				return;
			
			char[] ch = new char[L];
			for(int i = 0; i < C; ++i)
				if(visited[i])
					ch[cdx++] = data[i];
			
			Arrays.sort(ch);
			String str = "";
			for(int i = 0; i < ch.length; ++i)
				str += ch[i];
			
			result.add(str);
			cdx = 0;
			
			return;
		}
			
		for(int i = before; i < C; ++i)
		{
			visited[i] = true;
			Comb(idx+1,++before);
			visited[i] = false;
		}
		
	}
	
}
