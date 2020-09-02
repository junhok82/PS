package KAKAO.INTERNSHIP2020;

import java.io.*;
import java.util.*;

public class 수식최대화 {
    private static char[] order = new char[3];
    private static char[] operator = {'+', '-', '*'};
    private static List<Character> op = new ArrayList<>();
    private static List<Long> data = new ArrayList<>();
    private static long result = 0;

    public static void main(String[] args) {
        solution("100-200*300-500+20");
    }

    public static long solution(String expression) {
        long val = 0;
        int idx = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            Character ch = expression.charAt(i);
            if(isOperator(ch)) {
                data.add(Long.parseLong(sb.toString()));
                op.add(ch);
                sb = new StringBuilder();
            }
            else {
                sb.append(ch);
            }
        }
        data.add(Long.parseLong(sb.toString()));
        comb(0, 0);

        return result;
    }

    private static void comb(int idx, int check) {
        if (idx == 3) {
            List<Character> opTemp = new ArrayList<>(op);
            List<Long> dataTemp = new ArrayList<>(data);

            for (int i = 0; i < order.length; i++) {
                char opCurr = order[i];
                for (int j = 0; j < opTemp.size(); j++) {
                    char opComp = opTemp.get(j);
                    if(opCurr == opComp) {
                        dataTemp.add(j,calculate(dataTemp.remove(j),dataTemp.remove(j),opComp));
                        opTemp.remove(j--);
                    }
                }
            }
            result = Math.max(result,Math.abs(dataTemp.get(0)));
            return;
        }
        for (int i = 0; i < 3; i++) {
            if ((check & (1 << i)) != 0) continue;
            order[idx] = operator[i];
            comb(idx + 1, check | (1 << i));
        }
    }

    private static long calculate(long firstVal, long secondVal, char compOp) {
        switch (compOp) {
            case '+':
                return firstVal + secondVal;
            case '-':
                return firstVal - secondVal;
            case '*':
                return firstVal * secondVal;
        }
        return 0;
    }

    private static boolean isOperator(Character ch) {
        return ch == '+' || ch == '-' || ch == '*';
    }
}
