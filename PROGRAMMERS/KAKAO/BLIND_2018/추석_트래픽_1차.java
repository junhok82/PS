package KAKAO.BLIND2018;

import java.io.*;
import java.util.*;

public class 추석_트래픽_1차 {

    public static void main(String[] args) throws IOException {
        System.out.println( solution(new String[]{
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"
        }));

        System.out.println(solution(new String[] {
                "2016-09-15 01:00:04.001 2.0s",
                "2016-09-15 01:00:07.000 2s"
        }));
    }
    public static int solution(String[] lines) {
        int answer = 0;
        int len = lines.length;
        List<int[]> completeTime = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            String[] splitLines = lines[i].split(" ");
            String[] splitTimes = splitLines[1].split(":");

            int hour = Integer.parseInt(splitTimes[0]);
            int minute = Integer.parseInt(splitTimes[1]);
            int time = Integer.parseInt(splitTimes[2].replaceAll("\\.",""));
            int diff = (int) (Double.parseDouble(splitLines[2].substring(0,splitLines[2].length() - 1)) * 1000);

            int endTime = 10000000 * hour + 100000 * minute + time;

            int startTime = time+1 - diff;
            if(startTime < 0) {
                if(minute > 0) {
                    --minute;
                }
                else {
                    --hour;
                    minute = 59;
                }
                startTime = 60000 + startTime;
            }

            int beginTime = 10000000 * hour + 100000 * minute + startTime;
            completeTime.add(new int[] {beginTime,endTime});
        }

        for (int i = 0; i < len; i++) {
            int tempAnswer = 1;
            int sPointer = completeTime.get(i)[1];
            for (int j = i+1; j < len; j++) {
                int comp = completeTime.get(j)[0];
                if(comp <= sPointer + 999) tempAnswer++;
            }
            answer = Math.max(answer,tempAnswer);
        }
        return answer;
    }
}
