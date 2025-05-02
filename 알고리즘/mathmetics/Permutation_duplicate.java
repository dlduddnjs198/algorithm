import java.io.*;
import java.util.*;

// 중복순열
// 시간복잡도 = O(n^r)
public class Main {

    static int r;
    static int[] arr = new int[]{1, 3, 5, 7, 9};
    static int[] sel;
    //    static boolean[] visited; 중복순열은 이거 불필요
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        sel = new int[r];

        permutation(0);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void permutation(int depth) throws Exception {
        if(depth == r){
            bw.write(Arrays.toString(sel) + "\n");
            return;
        }

        for(int i=0;i<arr.length;i++){
            sel[depth] = arr[i];
            permutation(depth+1);
        }

    }
}