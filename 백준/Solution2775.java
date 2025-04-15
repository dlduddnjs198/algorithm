import java.io.*;
import java.util.*;

//부녀회장이 될테야
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int[][] map = new int[15][15];
        map[0][1]=1;
        for(int i=2;i<15;i++){
            map[0][i]=map[0][i-1]+1;
        }

        for(int i=1;i<15;i++){
            map[i][1] = map[i-1][1];
            for(int j=2;j<15;j++){
                map[i][j] = map[i-1][j] + map[i][j-1];
            }
        }

        for(int t=0;t<T;t++){
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            bw.write(map[k][n] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}