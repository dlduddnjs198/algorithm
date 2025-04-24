import java.io.*;
import java.util.*;

//덩치
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] list = new int[N][2];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            list[i][0] = Integer.parseInt(st.nextToken());
            list[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++){
            int cnt=0;
            for(int j=0;j<N;j++){
                if(i==j) continue;
                if(comp(list[j][0], list[j][1], list[i][0], list[i][1])) cnt++;
            }
            bw.write(cnt+1+" ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean comp(int x1, int y1, int x2, int y2){
        if(x1>x2 && y1>y2) return true;
        return false;
    }
}
