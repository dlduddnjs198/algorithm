import java.io.*;
import java.util.*;

//직각삼각형
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            int[] len = new int[3];
            len[0] = Integer.parseInt(st.nextToken());
            len[1] = Integer.parseInt(st.nextToken());
            len[2] = Integer.parseInt(st.nextToken());
            if(len[0]==0 && len[1]==0 && len[2]==0) break;
            Arrays.sort(len);
            if(len[0]*len[0] + len[1]*len[1] == len[2]*len[2]) bw.write("right\n");
            else bw.write("wrong\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}