import java.io.*;
import java.util.*;
import java.awt.Point;

//수 찾기
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        HashMap<Integer, Boolean> map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            map.put(Integer.parseInt(st.nextToken()), true);
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            if(map.containsKey(Integer.parseInt(st.nextToken()))) bw.write(1+"\n");
            else bw.write(0+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}