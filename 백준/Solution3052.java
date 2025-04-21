import java.io.*;
import java.util.*;

//나머지
public class Main{

    static final int MOD = 42;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] mods = new int[MOD];
        int answer = 0;

        for(int i=0;i<10;i++){
            int n = Integer.parseInt(br.readLine());
            if(mods[n%MOD]==0) answer++;
            mods[n%MOD]++;
        }

        bw.write(answer+"");
        bw.flush();
        bw.close();
        br.close();
    }
}