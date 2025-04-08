import java.io.*;
import java.util.*;

//숫자의 개수
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());

        String num = String.valueOf(A*B*C);
        int[] cnt = new int[10];

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            cnt[c - '0']++;
        }

        for (int i = 0; i < cnt.length; i++) {
            bw.write(cnt[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}