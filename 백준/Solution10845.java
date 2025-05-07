import java.io.*;
import java.util.*;

// 큐
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Deque<Integer> dq = new ArrayDeque<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            switch(cmd){
                case "push":
                    dq.offerLast(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    if(dq.isEmpty()) sb.append("-1\n");
                    else sb.append(dq.pollFirst()).append("\n");
                    break;
                case "size":
                    sb.append(dq.size()).append("\n");
                    break;
                case "empty":
                    if(dq.isEmpty()) sb.append("1\n");
                    else sb.append("0\n");
                    break;
                case "front":
                    if(dq.isEmpty()) sb.append("-1\n");
                    else sb.append(dq.peekFirst()).append("\n");
                    break;
                case "back":
                    if(dq.isEmpty()) sb.append("-1\n");
                    else sb.append(dq.peekLast()).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}