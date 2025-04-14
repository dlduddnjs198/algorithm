import java.io.*;
import java.util.*;

//윤년
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        bw.write(String.valueOf(((n%4==0) && (n%100!=0 || n%400==0))?1:0));

        bw.flush();
        bw.close();
        br.close();
    }
}
