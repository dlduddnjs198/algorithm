import java.io.*;
import java.util.*;

//카드2
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            dq.offer(i);
        }

        while (dq.size() > 1) {
            dq.poll();  // 맨 위 카드 버리기
            dq.offer(dq.poll());  // 다음 카드 맨 아래로 이동
        }

        bw.write(dq.peek() + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
