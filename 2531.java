package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2531 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] list = new int[n];
        int[] check = new int[d + 1];

        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }

        int total = 0;
        int sPointer = 0;
        int ePointer = k - 1;
        boolean flag = false;

        for (int i = 0; i < k; i++) {
            int val = list[i];
            if(check[val]++ > 0) continue;
            total++;
        }

        if(check[c] == 0) {
            flag = true;
            total++;
        }
        int result = total;

        do {
            if(--check[list[sPointer == n ? sPointer = 0 : sPointer++]] == 0) total--;
            if(++check[list[ePointer + 1 == n ? ePointer = 0 : ++ePointer]] == 1) total++;

            if(flag && check[c] > 0) {
                total--;
                flag = false;
            }
            else if(!flag && check[c] == 0) {
                total++;
                flag = true;
            }
            result = Math.max(result, total);
        }while(sPointer != 0);

        System.out.print(result);
    }
}
