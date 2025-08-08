import java.io.*;
import java.util.*;

// 27866 문자와 문자열
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        int N = Integer.parseInt(br.readLine());
        bw.write(str.charAt(N-1));
        bw.flush();
        bw.close();
        br.close();
    }
}