import java.io.*;
import java.util.*;

//체스판 다시 칠하기
public class Main{

    static char[][] map;
    static int M, N, answer;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 세로
        N = Integer.parseInt(st.nextToken()); // 가로
        map = new char[M][N];
        answer = Integer.MAX_VALUE;

        for(int i=0;i<M;i++){
            String line = br.readLine();
            for(int j=0;j<N;j++){
                map[i][j] = line.charAt(j);
            }
        }

        for(int i=0;i<M-7;i++){
            for(int j=0;j<N-7;j++){
                color(i, j, 'B');
                color(i, j, 'W');
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void color(int x, int y, char color){
        char newColor = color;
        int cnt=0;
        for(int i=x;i<x+8;i++){
            for(int j=y;j<y+8;j++){
                if(map[i][j]!=newColor) cnt++;
                newColor = newColor == 'W' ? 'B' : 'W';
            }
            newColor = newColor == 'W' ? 'B' : 'W';
        }
        answer = Math.min(answer, cnt);
    }

    private static void print(){
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
