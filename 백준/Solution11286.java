import java.io.*;
import java.util.*;

// 11286 절댓값 힙
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if(Math.abs(a) == Math.abs(b)) return Integer.compare(a, b);
            else return Integer.compare(Math.abs(a), Math.abs(b));
        });
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num==0){
                if(pq.isEmpty()) sb.append(num).append("\n");
                else sb.append(pq.poll()).append("\n");
            }else{
                pq.offer(num);
            }
        }

        System.out.println(sb.toString());
    }
}
// PQ