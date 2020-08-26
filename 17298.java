import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] arr;
    private static Stack<int[]> stack;
    private static int[] ret;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        ret = new int[N];
        stack = new Stack<>();
        Arrays.fill(ret,-1);

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < N - 1; i++) {
            if(arr[i+1] > arr[i]) {
                while(!stack.empty() &&stack.peek()[1] < arr[i + 1]) {
                    ret[stack.pop()[0]] = arr[i + 1];
                }
                ret[i] = arr[i+1];
            }
            else {
                stack.push(new int[] {i,arr[i]});
            }
        }
        for (int i = 0; i < N; i++) {
            sb.append(ret[i] + " ");
        }
        System.out.print(sb);
    }
}
