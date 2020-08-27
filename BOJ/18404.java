package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18404 {
    private static int N;
    private static int M;
    private static int knightY;
    private static int knightX;
    private static List<int[]> horse;
    private static int[] currLoc;
    private static int y;
    private static int x;
    private static int ny;
    private static int nx;
    private static int[] dy = {-1,-2,-2,-1,1,2,2,1};
    private static int[] dx = {-2,-1,1,2,2,1,-1,-2};
    private static StringBuilder sb;
    private static int[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        horse = new ArrayList<>();
        v = new int[N][N];

        st = new StringTokenizer(br.readLine());
        knightY = Integer.parseInt(st.nextToken()) - 1;
        knightX = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            horse.add(new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1});
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(v[i],Integer.MAX_VALUE);
        }

        bfs();
        for(int[] curr : horse) {
            sb.append(v[curr[0]][curr[1]] + " ");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {knightY,knightX});
        v[knightY][knightX] = 0;
        int cnt = 1;
        int idx = 0;

        while(q.size() != 0) {
            int siz = q.size();
            for (int k = 0; k < siz; k++) {
                currLoc = q.poll();
                y = currLoc[0];
                x = currLoc[1];

                for (int i = 0; i < 8; ++i) {
                    ny = y + dy[i];
                    nx = x + dx[i];

                    if (ny < 0 || nx < 0 || ny >= N || nx >= N || v[ny][nx] <= cnt) continue;
                    v[ny][nx] = cnt;
                    q.offer(new int[] {ny,nx});
                }
            }
            cnt++;
        }
    }
}
