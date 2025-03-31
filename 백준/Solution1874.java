import java.io.*;
import java.util.*;

// 스택 수열
// O(N)
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        Deque<Integer> dq = new ArrayDeque<>();
        int num=1;
        for(int i=0;i<n;i++){
            int target = Integer.parseInt(br.readLine());
            if(num>target){
                if(!dq.isEmpty() && dq.pollFirst()==target){
                    sb.append("-\n");
                }else{
                    sb = new StringBuilder("NO");
                    break;
                }
            }else {
                for(int j=num;j<=target;j++){
                    dq.offerFirst(j);
                    sb.append("+\n");
                }
                dq.pollFirst();
                sb.append("-\n");
                num=target+1;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}