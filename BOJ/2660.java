package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2660 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int minScore = Integer.MAX_VALUE;

        int[] friends = new int[n + 1];
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dist[i][j] = i == j ? 0 : 123456789;
            }
        }

        while(true) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(u == -1 && v == -1) break;
            dist[u][v] = 1;
            dist[v][u] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int score = 0;
            for (int j = 1; j <= n; j++) {
                if(i == j) continue;
                score = Math.max(score,dist[i][j]);
            }
            list.add(score);
            minScore = Math.min(score,minScore);
        }

        for (int i = 0; i < n; i++) {
            if(list.get(i) == minScore) {
                cnt++;
                sb.append(i + 1).append(" ");
            }
        }
        System.out.println(minScore + " " + cnt);
        System.out.print(sb);
    }
}
