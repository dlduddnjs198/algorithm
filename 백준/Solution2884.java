import java.io.*;
import java.util.*;

//ACM 호텔
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int H = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        // 전체 시간을 "분 단위"로 변환 후 45분 빼기
        int totalMinutes = (H * 60 + M - 45 + 1440) % 1440;

        // 새로운 시(H)와 분(M) 계산
        H = totalMinutes / 60;
        M = totalMinutes % 60;

        bw.write(H + " " + M + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}