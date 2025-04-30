import java.io.*;
import java.util.*;

//ACM 호텔
public class Main{

    static int H, W, N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            int n = N%H==0 ? H : N%H;
            int hNum = N%H==0 ? N/H-1 : N/H;

            bw.write(n*100 + hNum+1 + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}