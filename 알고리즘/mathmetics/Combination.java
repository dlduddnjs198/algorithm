import java.io.*;
import java.util.*;

// 조합(조합구하기)
// 시간복잡도 = O(nCr) (출력에 O(r))
public class Main {

    static int[] arr = {1, 3, 5, 7, 9};
    static int n = arr.length;
    static int[] sel;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        sel = new int[r];

        combination(0, 0);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void combination(int depth, int start) throws Exception{
        if(depth == sel.length){
            bw.write(Arrays.toString(sel)+"\n");
            return;
        }
        for(int i=start;i<arr.length;i++){
            sel[depth]=arr[i];
            combination(depth+1, i+1);
        }
    }
}