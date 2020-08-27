package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1337 {
    private static int N;
    private static int[] arr;
    private static int result = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            int idx = i+1;
            int s = arr[i];
            int cnt = 1;
            while(idx < N) {
                if(arr[idx] >= s + 5) break;
                cnt++;
                idx++;
            }
            result = Math.min(result,5-cnt);
        }
        System.out.println(result);
    }
}
