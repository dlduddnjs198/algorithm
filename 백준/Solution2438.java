import java.io.*;
import java.util.*;

// 별 찍기(1)
public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        StringBuilder sb;

        for(int i=0;i<N;i++){
            sb = new StringBuilder();
            for(int j=0;j<=i;j++) sb.append("*");
            sb.append("\n");
            bw.write(sb.toString());
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
