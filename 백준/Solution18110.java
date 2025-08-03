import java.io.*;
import java.util.*;

// 18110 solved.ac (O(NlogN))
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int removeCnt = (int) Math.round(n*0.15);
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int cnt=0;
        int sum=0;
        for(int i=removeCnt;i<n-removeCnt;i++){
            sum+=arr[i];
            cnt++;
        }
        bw.write(Math.round((double)sum/cnt)+"");
        bw.flush();
        bw.close();
        br.close();
    }
}