import java.io.*;
import java.util.*;

//최대공약수와 최소공배수
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int gcd = gcd(A, B);
        int lcm = A * B / gcd;

        bw.write(gcd+"\n" + lcm);
        bw.flush();
        bw.close();
        br.close();
    }

    private static int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b, a % b);
    }
}
