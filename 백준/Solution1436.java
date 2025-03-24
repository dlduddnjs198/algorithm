import java.io.*;
import java.util.*;

//영화감독 숌(브루트포스)
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int cnt = 0;
        int num = 666;

        for(;cnt<N;){
            if(has666(num)) cnt++;
            num++;
        }

        bw.write(String.valueOf(num-1));
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean has666(int num){
        while(num>0){
            if(num%1000==666) return true;
            num/=10;
        }
        return false;
    }
}
