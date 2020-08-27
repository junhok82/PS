package BOJ;

import javafx.event.EventDispatchChain;

import java.io.*;
import java.util.*;

public class BOJ_1766 {
    private static int N;
    private static int M;
    private static List<Integer>[] adj;
    private static int[] inDegree;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sb = new StringBuilder();
        inDegree = new int[N+1];
        adj = new List[N+1];
        for (int i = 0; i < N+1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            inDegree[v]++;
        }

        topologicalSort();
        System.out.println(sb);
    }

    private static void topologicalSort() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        for (int i = 1; i <= N; i++)  if(inDegree[i] == 0) pq.add(i);

        for (int i = 0; i < N; i++) {
            int curr = pq.poll();
            for (int next : adj[curr]) {
                if(--inDegree[next] == 0) pq.add(next);
            }
            sb.append(curr).append(" ");
        }
    }
}
