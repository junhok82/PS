package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1253 {
    private static int[] list;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        list = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(list);

        for (int i = 0; i < n; i++) {
            int curr = list[i];

            int s = 0;
            int e = n -1;
            boolean flag = false;

            while(s < e) {
                if(s == i) { s++; continue; }
                if(e == i) { e--; continue; }
                int sum = list[s] + list[e];

                if(sum > curr) {
                    e = lowerBound(list[e],s,e) - 1;
                }
                else if(sum < curr) {
                    s = upperBound(list[s],s,e);
                }
                else {
                    flag = true;
                    break;
                }

            }
            if(flag) result++;

        }
        System.out.print(result);
    }

    private static int upperBound(int target, int s, int e) {
        while(s < e) {
            int mid = (s + e) / 2;

            if(list[mid] <= target) {
                s = mid + 1;
            }
            else {
                e = mid;
            }
        }
        return e;
    }

    private static int lowerBound(int target, int s, int e) {
        while(s < e) {
            int mid = (s + e) / 2;

            if(list[mid] >= target) {
                e = mid;
            }
            else {
                s = mid + 1;
            }
        }
        return e;
    }
}
