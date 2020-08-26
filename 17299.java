import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] memo = new int[1000001];
        Stack<int[]> stack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            memo[arr[i]]++;
        }

        for (int i = 0; i < n - 1; i++) {
            if(memo[arr[i + 1]] > memo[arr[i]]) {
                while(!stack.empty() && memo[stack.peek()[1]] < memo[arr[i+1]]) {
                    arr[stack.pop()[0]] = arr[i+1];
                }
                arr[i] = arr[i+1];
            }
            else {
                stack.push(new int[] {i, arr[i]});
            }
        }

        while(!stack.empty()) {
            arr[stack.pop()[0]] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            sb.append(arr[i]).append(" ");
        }
        sb.append(-1);
        System.out.print(sb);
    }
}
