import java.io.*;
import java.util.*;

// 달팽이는 올라가고 싶다
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        int answer = (V-A)/(A-B) + 1;
        if((V-A)%(A-B) > 0) answer++;
        // (V - A) 거리를 하루 이동량 (A - B) 로 나누고, 올림하여 +1
//        int days = (int) Math.ceil((double) (V - A) / (A - B)) + 1;

        bw.write(answer+"");
        bw.flush();
        bw.close();
        br.close();
    }
}