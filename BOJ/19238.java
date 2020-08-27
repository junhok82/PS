import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int M;
    private static int T;
    private static boolean[][] m;
    private static int beginY;
    private static int beginX;
    private static Pair[] start;
    private static Pair[] end;
    private static boolean[] finished;
    private static int[] dy = {1, 0, -1, 0};
    private static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        m = new boolean[N][N];
        start = new Pair[M];
        end = new Pair[M];
        finished = new boolean[M];

        /* Input */

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                m[i][j] = s.charAt(j + j) == '0';
            }
        }

        st = new StringTokenizer(br.readLine());
        beginY = Integer.parseInt(st.nextToken()) - 1;
        beginX = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            start[i] = new Pair(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            end[i] = new Pair(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }

        /* Input  */
        
        boolean pos = true;

        for (int i = 0; i < M; i++) {
            /* 태우기 */
            int[] temp = bfs(beginY, beginX, -1, -1, false);
            int distance = temp[0];
            int targetY = temp[1];
            int targetX = temp[2];
            int targetIdx = temp[3];

            // 태울 수 없다면
            if (distance == -1) {
                pos = false;
                break;
            } else T -= distance;

            /* 내려주기 */
            int endTargetY = end[targetIdx].y;
            int endTargetX = end[targetIdx].x;
            int tempDistance = bfs(targetY, targetX, endTargetY, endTargetX, true)[0];

            // 내릴 수 없다면
            if (tempDistance == -1) {
                pos = false;
                break;
            } else {
                finished[targetIdx] = true;
                T += tempDistance;
                beginY = endTargetY;
                beginX = endTargetX;
            }
        }
        System.out.print(pos ? T : -1);
    }

    private static int[] bfs(int beginY, int beginX, int endY, int endX, boolean flag) {
        if (flag && beginY == endY && beginX == endX) return new int[]{0};

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[2] < o2[2]) return -1;
                else if (o1[2] == o2[2]) {
                    if (o1[0] < o2[0]) return -1;
                    else if (o1[0] == o2[0]) {
                        if (o1[1] < o2[1]) return -1;
                        else return 1;
                    }
                }
                return 1;
            }
        });
        boolean[][] visited = new boolean[N][N];
        pq.offer(new int[]{beginY, beginX, 0});
        visited[beginY][beginX] = true;

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int y = temp[0];
            int x = temp[1];
            int cnt = temp[2];

            if (!flag) {
                for (int j = 0; j < M; j++) {
                    if (finished[j]) continue;
                    if (y == start[j].y && x == start[j].x) {
                        return new int[]{cnt, y, x, j};
                    }
                }
            }

            if (cnt >= T) return new int[]{-1, -1, -1, -1};

            for (int j = 0; j < 4; j++) {
                int ny = y + dy[j];
                int nx = x + dx[j];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || !m[ny][nx] || visited[ny][nx]) continue;
                if (flag && ny == endY && nx == endX) return new int[]{cnt+1};
                visited[ny][nx] = true;
                pq.offer(new int[]{ny, nx, cnt + 1});
            }
        }
        return new int[]{-1, -1, -1, -1};
    }

    static class Pair {
        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
