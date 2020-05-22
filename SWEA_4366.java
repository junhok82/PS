package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_4366 {
	private static int T;
	private static String binary;
	private static String ternary;
	private static int result;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; ++t) {
			binary = br.readLine();
			ternary = br.readLine();
			
			String tempBinary = binary;
			String tempTernary = ternary;
			boolean flag = false;
			
			for(int i = 0; i < binary.length(); ++i) {		
				String temp = binary.substring(0, i);
				char ch = binary.charAt(i) == '1' ? '0' : '1';
				tempBinary = temp + ch + binary.substring(i+1);
				int bin = BtoD(tempBinary);
				
				for(int j = 0; j < ternary.length(); ++j) {
					temp = ternary.substring(0,j);
					ch = ternary.charAt(j);
					for(int k = 0; k < 2; ++k) {
						ch = ch == '0' ? '1' : ch == '1' ? '2' : '0';
						tempTernary = temp + ch + ternary.substring(j+1);
						int ter = TtoD(tempTernary);
						
						if(bin == ter) {
							result = bin;
							flag = true;
							break;
						}
						if(flag) break;
					}
				}
				if(flag) break;
			}
			sb.append("#").append(t).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	public static int BtoD(String str) {
		int temp = 0;
		for(int i = str.length()-1, idx = 0; i >= 0; --i, ++idx) {
			int digit = str.charAt(i) -'0';
			temp += (digit * Math.pow(2, idx));
		}
		return temp;
	}
	
	public static int TtoD(String str) {
		int temp = 0;
		for(int i = str.length()-1, idx = 0; i >= 0; --i, ++idx) {
			int digit = str.charAt(i) -'0';
			temp += (digit * Math.pow(3, idx));
		}
		return temp;
	}
}
