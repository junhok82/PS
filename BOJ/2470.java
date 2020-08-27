package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2470 {
    private static int N;
    private static int[] list;
    private static int e;
    private static int s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new int[N];

        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);
        int sPointer = 0;
        int ePointer = list.length - 1;
        int sum = Integer.MAX_VALUE;

        while(sPointer < ePointer) {
            int tempSum = Math.abs(list[sPointer] + list[ePointer]);

            if(tempSum < sum) {
                s = sPointer;
                e = ePointer;
                sum = tempSum;
                if(sum == 0) break;
            }

            if(Math.abs(list[sPointer + 1] + list [ePointer]) >= Math.abs(list[sPointer] + list[ePointer - 1]) ) ePointer--;
            else sPointer++;

        }
        System.out.print(list[s] + " " + list[e]);
    }
}
