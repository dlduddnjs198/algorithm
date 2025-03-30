import java.io.*;
import java.util.*;

// 랜선 자르기(이진탐색)
// O(K × log maxLength)
public class Main {

    static int N, K;
    static int[] lens;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lens = new int[K];
        int max=0;
        for(int i=0;i<K;i++){
            lens[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, lens[i]);
        }
        long left=1;
        long right=max;
        long answer=0;

        while(left<=right){
            long mid = (left+right)/2;
            int sum = getSum(mid);
            if(sum>=N){
                answer = Math.max(answer, mid);
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        bw.write(answer+"");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getSum(long num){
        int sum=0;
        for(int i=0;i<K;i++){
            sum+=lens[i]/num;
        }
        return sum;
    }
}