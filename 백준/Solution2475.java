import java.io.*;
import java.util.*;

// 검증수
public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int result=0;

        for(int i=0;i<5;i++){
            int n = Integer.parseInt(st.nextToken());
            result+=n*n;
        }

        bw.write(String.valueOf(result%10));

        bw.flush();
        br.close();
        bw.close();
    }
}
