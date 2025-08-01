import java.io.*;
import java.util.*;

//Hashing(누적곱)
public class Main{

    static final int M = 1234567891;
    static final int r = 31;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int L = Integer.parseInt(br.readLine());
        char[] c = br.readLine().toCharArray();
        long answer = 0;
        long power = 1;  // r^0 = 1

        for (int i = 0; i < L; i++) {
            answer = (answer + (c[i] - 'a' + 1) * power % M) % M;  // (a_i * r^i) % M
            power = (power * r) % M;  // 다음 r^i 값 업데이트
        }

        bw.write(answer%M + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
