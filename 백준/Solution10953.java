import java.io.*;
import java.util.*;

//A+B - 6
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken(","));
            int B = Integer.parseInt(st.nextToken(","));

            bw.write(A+B+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}