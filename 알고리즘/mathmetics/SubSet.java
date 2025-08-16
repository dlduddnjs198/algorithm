import java.io.*;
import java.util.*;

// 부분집합(재귀)
// 시간복잡도 = O(2^n * n) (출력에 O(r))
public class Main {

    static int[] arr = {1, 3, 5, 7, 9};
    static int n = arr.length;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] sel;

        // 부분집합(재귀) -> O(2^n * n)이지만 반복이라 더 빠른듯?
        for(int i=0;i<arr.length;i++){
            sel = new int[i];
            subset(sel, 0, 0);
        }

        // 부분집합(비트마스킹) -> O(2^n * n)이지만 반복이라 더 빠른듯?
        for(int i=0;i<(1 << arr.length);i++){
            for(int j=0;j<arr.length;j++){
                if(((1 << j) & i) >= 1) bw.write(arr[j] + " ");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void subset(int[] sel, int depth, int start) throws Exception {
        if(depth==sel.length){
            bw.write(Arrays.toString(sel) + "\n");
            return;
        }
        for(int i=start;i<arr.length;i++){
            sel[depth] = arr[i];
            subset(sel, depth+1, i+1);
        }
    }
}