import java.io.*;
import java.util.*;

public class Main {
    private static String expression;
    private static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        expression = br.readLine();

        for (int i = 0; i < expression.length(); i++) {
            char data = expression.charAt(i);

            switch (data) {
                case '+' :
                case '-' :
                    while(!stack.isEmpty() && stack.peek() != '(') sb.append(stack.pop());
                    stack.push(data);
                    break;
                case '*' :
                case '/' :
                    while(!stack.isEmpty() && isPostOperator(stack.peek())) sb.append(stack.pop());
                    stack.push(data);
                    break;
                case '(' :
                    stack.push(data);
                    break;
                case ')' :
                    while(!stack.isEmpty() && stack.peek() != '(') sb.append(stack.pop());
                    stack.pop();
                    break;
                default:
                    sb.append(data);
                    break;
            }
        }
        while(!stack.isEmpty()) sb.append(stack.pop());
        System.out.print(sb);
    }

    private static boolean isPostOperator(char data) {return  data == '*' || data == '/';}
}
