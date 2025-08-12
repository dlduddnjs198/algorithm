import java.io.*;
import java.util.*;

A + B - C
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());

        bw.write(A+B-C+"\n");
        bw.write(Integer.parseInt(""+A+B)-C+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}