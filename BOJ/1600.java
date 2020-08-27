package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1600 {
    private static int K;
    private static int R;
    private static int C;
    private static String str;
    private static boolean[][] m;
    private static int[] dy = {1,0,-1,0};
    private static int[] dx = {0,1,0,-1};
    private static int[] my = {-1,-2,-2,-1,1,2,2,1};
    private static int[] mx = {-2,-1,1,2,2,1,-1,-2};
    private static int[] temp;
    private static int[][][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        m = new boolean[R][C];
        dist = new int[R][C][K+1];

        for (int i = 0; i < R; i++) {
            str = br.readLine();
            for (int j = 0; j < C; j++) {
                m[i][j] =  (str.charAt(j+j) == '1') ? true : false;
                Arrays.fill(dist[i][j],Integer.MAX_VALUE);
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[] {0,0,0});  // y, x, K
        dist[0][0][0] = 0;
        int cnt = 0;

        while(q.size() != 0) {
            int siz = q.size();
            for (int c = 0; c < siz; c++) {
                temp = q.poll();
                int y = temp[0];
                int x = temp[1];
                int k = temp[2];

                if (dist[y][x][k] < cnt) continue;
                if (y == R - 1 && x == C - 1) return cnt;

                for (int i = 0; i < 4; ++i) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (ny < 0 || nx < 0 || ny >= R || nx >= C || m[ny][nx] || dist[ny][nx][k] <= cnt + 1) continue;
                    dist[ny][nx][k] = cnt + 1;
                    q.offer(new int[]{ny, nx, k});
                }

                if (k < K) {
                    for (int i = 0; i < 8; i++) {
                        int ny = y + my[i];
                        int nx = x + mx[i];

                        if (ny < 0 || nx < 0 || ny >= R || nx >= C || m[ny][nx] || dist[ny][nx][k + 1] <= cnt + 1)
                            continue;
                        dist[ny][nx][k + 1] = cnt + 1;
                        q.offer(new int[]{ny, nx, k + 1});
                    }
                }
            }
            cnt++;
        }
        return -1;
    }
}
