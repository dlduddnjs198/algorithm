import java.io.*;
import java.util.*;

// 프린터 큐
// T * N * log N
public class Main {

    static class Number{
        int idx;
        int prior;
        Number(int idx, int prior){
            this.idx = idx;
            this.prior = prior;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] arr = new int[N];
            PriorityQueue<Number> pq = new PriorityQueue<>((a, b) -> {
                return b.prior - a.prior;
            });
            Deque<Number> dq = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                int num = Integer.parseInt(st.nextToken());
                pq.offer(new Number(i, num));
                dq.offerLast(new Number(i, num));
            }
            int order=1;
            while(!dq.isEmpty()){
                Number num = dq.pollFirst();
                if(num.prior==pq.peek().prior){
                    pq.poll();
                    if(num.idx==M){
                        sb.append(order).append("\n");
                        break;
                    }
                    order++;
                }else{
                    dq.offerLast(num);
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}