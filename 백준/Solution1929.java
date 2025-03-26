import java.io.*;
import java.util.*;

//소수 구하기(에라스토테네스의 체, 소수)
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int answer = 0;

        boolean[] sosu = new boolean[N+1];
        sosu[0] = true;
        sosu[1] = true;

        for(int i=2;i<=N;i++){
            for(int j=i*2;j<=N;j+=i) sosu[j]=true;
        }

        for(int i=M;i<=N;i++) if(!sosu[i]) bw.write(i + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
