import java.io.*;
import java.util.*;

//아스키 코드
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char c = br.readLine().charAt(0);
        bw.write(String.valueOf((int)c));
        bw.flush();
        bw.close();
        br.close();
    }
}