package KAKAO.BLIND2020;

import java.io.*;
import java.util.*;

public class 괄호변환 {
    public static void main(String[] args) throws IOException {
        System.out.println(solution("()))((()"));
    }

    public static String solution(String p) {
        return getSubBracket(p);
    }

    private static String getSubBracket(String expression) {
        if(expression.equals("")) return "";
        Stack<Character> openStack = new Stack<>();
        Stack<Character> closeStack = new Stack<>();
        int idx = 0;
        int expressionSize = expression.length();
        String u = "";
        String v = "";

        do {
            char data = expression.charAt(idx);
            if(data == '('){
                if(closeStack.isEmpty()) openStack.push(data);
                else {
                    closeStack.pop();

                    // 균형 잡힌 괄호
                    if(closeStack.isEmpty()) {
                        u = expression.substring(1,idx);
                        String temp = "";
                        for (int i = 0; i < u.length(); i++) {
                            temp += u.charAt(i) == ')' ? '(' : ')';
                        }
                        u = temp;
                        v = '(' + getSubBracket(expression.substring(idx+1, expressionSize)) + ')';
                    }
                }
            }
            else {
                if(openStack.isEmpty()) closeStack.push(data);
                else {
                    openStack.pop();

                    // 올바른 괄호
                    if(openStack.isEmpty()) {
                        u = expression.substring(0,idx + 1);
                        v = getSubBracket(expression.substring(idx+1, expressionSize));
                        return u + v;
                    }
                }
            }
            idx++;
        } while(!openStack.isEmpty() || !closeStack.isEmpty());
        return v + u;
    }
}
