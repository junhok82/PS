import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
  
public class Solution {
    private static int T;
    private static int N;
    private static long[] X;
    private static long[] Y;
    private static double E;
    private static boolean[] visited;
    private static long[] dist;
    private static long result;
  
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= T; ++t)
        {
            N = Integer.parseInt(br.readLine());
            X = new long[N];
            Y = new long[N];
            visited = new boolean[N];
            dist = new long[N];
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < N; ++i)
                X[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < N; ++i)
                Y[i] = Integer.parseInt(st.nextToken());
            E = Double.parseDouble(br.readLine());
              
            bfs();
            sb.append("#").append(t).append(" ").append(Math.round(result * E)).append('\n');
        }
        System.out.print(sb);
    }
  
    private static void bfs() {
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        result = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {
  
            @Override
            public int compare(long[] a, long[] b) {
                return a[0] > b[0] ? 1 : -1;
            }
        });
        pq.offer(new long[] {0,0});
        while(pq.size() != 0)
        {
            int curr; long d;
            do {
                long[] temp = pq.poll();
                curr = (int)temp[1];
                d = temp[0];
            }while(pq.size() != 0 && visited[curr]);
            if(visited[curr])   return;
            result += dist[curr];
            visited[curr] = true;
            for(int next = 0; next < N; ++next)
            {
                long dd = (X[curr] - X[next]) * (X[curr] - X[next]) + (Y[curr] - Y[next]) * (Y[curr] - Y[next]);
                if(dist[next] > dd)
                {
                    dist[next] = dd;
                    pq.offer(new long[] {dd,next});
                }
            }
        }
    }
}
