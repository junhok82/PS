import java.io.*;
import java.util.*;

import static java.lang.System.exit;

public class Main {
    private static int N;
    private static int M;
    private static List<Integer>[] adj;
    private static int[] inDegree;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new List[N+1];
        inDegree = new int[N+1];

        for (int i = 0; i < N+1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            int temp = -1;
            for (int j = 0; j < n; j++) {
                int singer = Integer.parseInt(st.nextToken());
                if(temp != -1) {
                    adj[temp].add(singer);
                    inDegree[singer]++;
                }
                temp = singer;
            }
        }
        
        topologicalSort();
        System.out.print(sb);
    }

    private static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < N+1; i++) {
            if (inDegree[i] == 0) q.offer(i);
        }

        for (int i = 0; i < N; i++) {
            if(q.isEmpty()) {
                System.out.print(0);
                exit(0);
            }

            int curr = q.poll();
            for (int next : adj[curr]) {
                if(--inDegree[next] == 0) q.offer(next);
            }
            sb.append(curr).append("\n");
        }
    }
}
