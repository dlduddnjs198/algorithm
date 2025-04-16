import java.io.*;
import java.util.*;

//설탕 배달(난 DP로 풀었지만 그리디도 가능)
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int answer = -1;
        int[] arr= new int[5001];
        Arrays.fill(arr, 1000000);
        arr[3] = 1;
        arr[5] = 1;
        for(int i=6;i<=N;i++){
            arr[i] = Math.min(arr[i-3] + 1, arr[i-5] + 1);
        }

        if(arr[N] >= 1000000) answer = -1;
        else answer = arr[N];

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
