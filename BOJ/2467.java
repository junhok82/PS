package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2467 {
    private static int N;
    private static long[] list;
    private static int sPoint;
    private static int ePoint;
    private static long sum;
    private static long result;
    private static int out1;
    private static int out2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new long[N];

        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        sPoint = 0;
        ePoint = N-1;
        result = Integer.MAX_VALUE;

        if(list[sPoint] > 0 && list[ePoint] > 0) {
            System.out.println(list[sPoint] + " " + list[sPoint+1]);
        }
        else if(list[sPoint] < 0 && list[ePoint] < 0) {
            System.out.println(list[ePoint - 1] + " " + list[ePoint]);
        }
        else {
            while (sPoint < ePoint) {
                sum = list[sPoint] + list[ePoint];
                if (Math.abs(sum) < result) {
                    result = Math.abs(sum);
                    out1 = sPoint;
                    out2 = ePoint;
                }

                if (sum > 0) ePoint--;
                else if (sum == 0) break;
                else sPoint++;

                if ((list[sPoint] > 0 && list[ePoint] > 0) || (list[sPoint] < 0 && list[ePoint] < 0)) break;
            }
            System.out.println(list[out1] + " " + list[out2]);
        }
    }
}
