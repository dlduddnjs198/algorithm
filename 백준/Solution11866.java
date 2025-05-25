import java.io.*;
import java.util.*;

// 요세푸스 문제 0(O(N^2))
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<Integer> list = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("<");

        for(int i=1;i<=N;i++){ // 리스트 초기화(O(N))
            list.add(i);
        }

        int start=0;
        for(int i=0;i<N;i++){
            start = (start+K-1)%list.size();
            sb.append(list.remove(start)); // 리스트 제거(O(N))
            if(i<N-1) sb.append(", ");
        }

        sb.append(">");

        System.out.println(sb);

    }
}