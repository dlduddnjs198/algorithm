import java.io.*;
import java.util.*;

// 제로
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int K = Integer.parseInt(br.readLine());
        Deque<Integer> dq = new ArrayDeque<>();
        int answer = 0;

        for(int i=0;i<K;i++){
            int num = Integer.parseInt(br.readLine());
            if(num!=0){
                answer+=num;
                dq.offerFirst(num);
            }else{
                answer-=dq.pollFirst();
            }
        }

        bw.write(answer+"");
        bw.flush();
        bw.close();
        br.close();
    }
}