package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5525 {
    private static int N;
    private static int M;
    private static String S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        S = br.readLine();

        StringBuilder pattern = new StringBuilder();
        pattern.append("I");
        for (int i = 0; i < N; i++) {
            pattern.append("OI");
        }
        System.out.print(KMP(S,pattern.toString()));
    }

    private static int KMP(String text, String pattern) {
        int[] pi = getPi(pattern);
        int cnt = 0;
        int textSize = text.length();
        int patternSize = pattern.length() - 1;
        int j = 0;
        for (int i = 0; i < textSize; i++) {
            while(j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = pi[j-1];
            }

            if(text.charAt(i) == pattern.charAt(j)) {
                if(j == patternSize) { cnt++; j = pi[j]; }
                else ++j;
            }
        }
        return cnt;
    }

    private static int[] getPi(String pattern) {
        int patternSize = pattern.length();
        int[] pi = new int[patternSize];
        int j = 0;
        for (int i = 1; i < patternSize; i++) {
            while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j-1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
}
