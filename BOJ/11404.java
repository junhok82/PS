package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11404 {
    private static int N;
    private static int M;
    private static int u;
    private static int v;
    private static int w;
    private static int[][] dist;

    private static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[100][100];
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i],INF);
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken()) - 1;
            v = Integer.parseInt(st.nextToken()) - 1;
            w = Integer.parseInt(st.nextToken());
            dist[u][v] = Math.min(dist[u][v],w);
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if(k == i) continue;
                for (int j = 0; j < N; j++) {
                    if(k == j || i == j) continue;
                    dist[i][j] = Math.min(dist[i][j],dist[i][k] + dist[k][j]);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i == j || dist[i][j] == INF) sb.append(0 + " ");
                else sb.append(dist[i][j] + " ");
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
