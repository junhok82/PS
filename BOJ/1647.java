package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1647 {
    private static final int MAX_VERTEX = 100_001;

    private static int V;
    private static int E;
    private static int result = 0;
    private static int[] uf = new int[MAX_VERTEX];
    private static List<Edge> edge = new ArrayList<>();

    static class Edge implements Comparable<Edge> {
        int u,v,w;

        public Edge() {}
        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edge.add(new Edge(u,v,w));
        }

        Collections.sort(edge);
        Arrays.fill(uf,-1);

        for (int i = 0,k = 0; k < V-2; i++) {
            if(union(edge.get(i).u,edge.get(i).v)) {
                result += edge.get(i).w;
                k++;
            }
        }
        System.out.print(result);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return false;
        uf[a] = b;
        return true;
    }

    private static int find(int a) {
        if(uf[a] < 0) return a;
        return uf[a] = find(uf[a]);
    }
}
