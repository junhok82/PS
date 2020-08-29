package BOJ;

import java.io.*;
public class BOJ_17825 {
    private static int[] jump;
    private static int result;
    private static Runner[] runner;

    static class Runner {
        int score;
        int state;

        public Runner(int score, int state) {
            this.score = score;
            this.state = state;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        jump = new int[10];
        runner = new Runner[4];
        for (int i = 0; i < 4; i++) {
            runner[i] = new Runner(0,0);
        }
        
        for (int i = 0; i < 10; i++) {
            jump[i] = input.charAt(i+i) - '0';
        }
        result = 0;
        comb(0,0);
        System.out.println(result);
    }

    private static void comb(int idx, int sum) {
        if(idx == 10) {
            result = Math.max(result,sum);
            return;
        }

        for (int j = 0; j < 4; j++) {
            int score = runner[j].score;
            int state = runner[j].state;
            if (score > 40) continue;

            int temp = go(j, jump[idx],score,state);
            if (temp == -1) continue;

            comb(idx + 1, sum + temp);

            runner[j].score = score;
            runner[j].state = state;
        }

    }

    private static int go(int idx, int cnt, int score, int state) {
        if(state == 3 && score == 30) score--;

        for (int i = 0; i < cnt; i++) {
            score += getScore(state);
            if(score > 40) break;
            if(checkChangeDirection(score,state)) { state = 4; score = 25; }
        }
        state = getState(score,state);

        for (int j = 0; j < 4; j++) {
            if(j == idx || score > 40) continue;
            if(runner[j].score == score && runner[j].state == state) return -1;
        }

        runner[idx].score = score;
        runner[idx].state = state;
        if(score > 40)  return 0;
        return score;
    }

    private static boolean checkChangeDirection(int score, int state) {
        return (score == 22 && state == 1) || (score == 26 && state == 2) || (score == 25 && state == 3);
    }

    private static int getState(int score, int state) {
        if(score == 10 && state == 0) return 1;
        else if(score == 20 && state == 0) return 2;
        else if(score == 30 && state == 0) return 3;
        else if(score == 40) return 4;
        return state;
    }

    private static int getScore(int state) {
        switch (state) {
            case 0 :
            case 2 :
                return 2;
            case 1 :
                return 3;
            case 3 :
                return -1;
            case 4 :
                return 5;
            default :
                return Integer.MAX_VALUE;
        }
    }
}
