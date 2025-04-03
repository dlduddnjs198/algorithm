import java.io.*;
import java.util.*;

//벌집
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int cnt=1;

        for(int i=1;cnt<N;i++){
            cnt += 6*i;
            if(cnt >= N) {
                bw.write(i+1+"");
                break;
            }
        }

        if(N==1) bw.write(1+"");

        bw.flush();
        bw.close();
        br.close();
    }
}
