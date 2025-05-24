import java.io.*;
import java.util.*;

//숫자의 합
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int sum = 0;
        String num = br.readLine();
        for(int i=0;i<N;i++){
            sum += num.charAt(i) - '0';
        }

        bw.write(sum+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}