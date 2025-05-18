import java.io.*;
import java.util.*;

//이항 계수 1(조합)
public class Main{

    static int[] facts;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        facts = new int[11];
        facts[0]=1;
        facts[1] = 1;
        for(int i=2;i<11;i++){
            facts[i] = facts[i-1]*i;
        }

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        bw.write(combination(n,k)+"");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int combination(int n, int k){
        return facts[n]/(facts[n-k]*facts[k]);
    }
}
