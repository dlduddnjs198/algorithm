import java.io.*;
import java.util.*;

//소수 찾기
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        boolean[] sosu = new boolean[1001];
        sosu[0]=true;
        sosu[1]=true;
        int answer = 0;

        for(int i=2;i*i<=1000;i++){
            for(int j=i*2;j<=1000;j+=i){
                sosu[j]=true;
            }
        }

        for(int i=0;i<N;i++){
            if(!sosu[Integer.parseInt(st.nextToken())]) answer++;
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
